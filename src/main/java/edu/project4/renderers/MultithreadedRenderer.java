package edu.project4.renderers;

import edu.project4.records.Color;
import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageSize;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultithreadedRenderer implements Renderer {
    private final static Logger LOGGER = LogManager.getLogger();
    private final int threadCount;

    public MultithreadedRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * Многопоточная генерация изображения
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
        List<Callable<GeneratedPixelMap>> tasks = new ArrayList<>();
        int remains = samples % threadCount;

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            tasks.add(() -> new SingleRenderer().render(
                    imageSize,
                    borders,
                    affine,
                    variations,
                    samples / threadCount + ((finalI < remains) ? 1 : 0),
                    iterations
            ));
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            List<Future<GeneratedPixelMap>> futures = executorService.invokeAll(tasks);

            List<GeneratedPixelMap> created = new ArrayList<>();
            for (var future : futures) {
                created.add(future.get());
            }

            return new GeneratedPixelMap(merge(created), imageSize.width(), imageSize.height());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage());

            return new GeneratedPixelMap(Map.of(), imageSize.width(), imageSize.height());
        }
    }

    /**
     * Вспомогательная функция для объединения результатов потоков в один
     *
     * @param forMerge результаты потоков
     * @return объединенный результат
     */
    private Map<Point, Pixel> merge(List<GeneratedPixelMap> forMerge) {
        return forMerge.stream()
                .map(GeneratedPixelMap::pixelMap)
                .flatMap(pixelMap -> pixelMap.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        mergePixels()
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        pointPixelEntry -> new Pixel(
                                new Color(
                                        pointPixelEntry.getValue().color().r() / threadCount,
                                        pointPixelEntry.getValue().color().g() / threadCount,
                                        pointPixelEntry.getValue().color().b() / threadCount
                                ),
                                pointPixelEntry.getValue().hitCount()
                        )
                ));
    }

    /**
     * Функция объединения двух пикселей в один
     *
     * @return объединенный пиксель
     */
    private static BinaryOperator<Pixel> mergePixels() {
        return (pixel1, pixel2) -> new Pixel(
                new Color(
                        pixel1.color().r() + pixel2.color().r(),
                        pixel1.color().g() + pixel2.color().g(),
                        pixel1.color().b() + pixel2.color().b()
                ),
                pixel1.hitCount() + pixel2.hitCount()
        );
    }
}
