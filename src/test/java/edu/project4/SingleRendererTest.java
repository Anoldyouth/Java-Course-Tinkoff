package edu.project4;

import edu.project4.records.Color;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageSize;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleRenderer;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.DiamondTransformation;
import edu.project4.transformations.LinearTransformation;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class SingleRendererTest {
    @Test
    void success() {
        Renderer renderer = new SingleRenderer();

        var map = renderer.render(
                new ImageSize(20, 20),
                new GenerationBorders(-1, 1, -1, 1),
                List.of(new ColoredTransformation(
                        LinearTransformation.randomTransformation(),
                        Color.generate()
                )),
                List.of(new DiamondTransformation()),
                20,
                20
        );

        assertThat(map).isNotNull();
    }
}
