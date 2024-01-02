package edu.project4.renderers;

import edu.project4.records.Color;
import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageSize;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleRenderer implements Renderer {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int PRE_STEPS = 20;

    /**
     * Однопоточная генерация изображения
     *
     * @param imageSize  размеры генерируемого изображения
     * @param borders    границы генерации чисел
     * @param affine     аффинные преобразования
     * @param variations нелинейные преобразования
     * @param samples    количество повторных генераций
     * @param iterations количество итераций внутри генерации
     * @return сгенерированная карта пикселей
     */
    @Override
    public GeneratedPixelMap render(
            ImageSize imageSize,
            GenerationBorders borders,
            List<ColoredTransformation> affine,
            List<Transformation> variations,
            int samples,
            int iterations
    ) {
        LOGGER.info(this.getClass().getSimpleName() + " started");
        Random rand = ThreadLocalRandom.current();
        double newX;
        double newY;
        Map<Point, Pixel> pixelMap = new HashMap<>();
        long tran = 0;

        for (int i = 0; i < samples; i++) {
            newX = rand.nextDouble(borders.xMin(), borders.xMax());
            newY = rand.nextDouble(borders.yMin(), borders.xMax());

            for (int step = -PRE_STEPS; step < iterations; step++) {
                ColoredTransformation chosenAffine = affine.get(rand.nextInt(affine.size()));
                Transformation transformation = variations.get((int) (tran % variations.size()));
                tran++;

                Point newPoint = transformation.apply(
                        chosenAffine.transformation().apply(new Point(newX, newY))
                );

                newX = newPoint.x();
                newY = newPoint.y();

                if (addPixel(step, newPoint, borders)) {
                    int x = extension(imageSize.width(), borders.xMin(), borders.xMax(), newPoint.x());
                    int y = extension(imageSize.height(), borders.yMin(), borders.yMax(), newPoint.y());

                    if (x < imageSize.width() && y < imageSize.height()) {
                        if (!pixelMap.containsKey(new Point(x, y))) {
                            pixelMap.put(new Point(x, y), new Pixel(chosenAffine.color(), 1));
                        } else {
                            Pixel old = pixelMap.get(new Point(x, y));
                            pixelMap.put(
                                    new Point(x, y),
                                    new Pixel(mixColor(old.color(), chosenAffine.color()), old.hitCount() + 1)
                            );
                        }
                    }
                }
            }
        }

        LOGGER.info(this.getClass().getSimpleName() + " ended");

        return new GeneratedPixelMap(
                pixelMap,
                imageSize.width(),
                imageSize.height()
        );
    }

    private boolean addPixel(int step, Point newPoint, GenerationBorders borders) {
        return step >= 0
                && newPoint.x() >= borders.xMin() && newPoint.x() <= borders.xMax()
                && newPoint.y() >= borders.yMin() && newPoint.y() <= borders.yMax();
    }

    private Color mixColor(Color first, Color second) {
        return new Color(
                (first.r() + second.r()) / 2,
                (first.g() + second.g()) / 2,
                (first.b() + second.b()) / 2
        );
    }

    private int extension(int size, double min, double max, double point) {
        return size - (int) Math.ceil(
                (max - point) / (max - min) * size
        );
    }
}
