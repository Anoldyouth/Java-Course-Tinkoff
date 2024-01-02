package edu.project4.renderers;

import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageSize;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    GeneratedPixelMap render(
            ImageSize imageSize,
            GenerationBorders borders,
            List<ColoredTransformation> affine,
            List<Transformation> variations,
            int samples,
            int iterations
    );
}
