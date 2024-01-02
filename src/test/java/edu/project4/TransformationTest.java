package edu.project4;

import edu.project4.records.Point;
import edu.project4.transformations.TransformationsEnum;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class TransformationTest {
    @ParameterizedTest
    @EnumSource(TransformationsEnum.class)
    void success(TransformationsEnum value) {
        Point point = new Point(1, 1);

        assertThat(value.getTransformation().apply(point)).isNotNull();
    }
}
