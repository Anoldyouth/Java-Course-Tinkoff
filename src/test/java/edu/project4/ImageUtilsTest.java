package edu.project4;

import edu.helpers.FileStructureHelper;
import edu.project4.records.Color;
import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ImageUtilsTest {
    private final static String DIRECTORY = "image_utils_test";

    @BeforeEach
    @AfterEach
    void reset() throws Exception {
        FileStructureHelper.deleteStructure(DIRECTORY);
    }

    @Test
    void correction() {
        Point point = new Point(1, 1);
        var map = new GeneratedPixelMap(
                Map.of(point, new Pixel(new Color(5, 5, 5), 100)),
                1,
                1
        );

        var newMap = ImageUtils.correction(map, 2.2);

        assertThat(newMap.pixelMap().get(point).color()).isEqualTo(new Color(6, 6, 6));
    }

    @ParameterizedTest
    @EnumSource(ImageFormat.class)
    void save(ImageFormat format) {
        Point point = new Point(1, 1);
        var map = new GeneratedPixelMap(
                Map.of(point, new Pixel(new Color(5, 5, 5), 100)),
                1,
                1
        );

        ImageUtils.save(map, Path.of(DIRECTORY, "test"), format);

        assertThat(Path.of(DIRECTORY, "test." + format.toString().toLowerCase()).toFile().exists()).isTrue();
    }
}
