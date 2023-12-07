package edu.project4;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.project4.json.Config;
import edu.project4.json.PresetAffineTransformation;
import edu.project4.records.Color;
import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageSize;
import edu.project4.renderers.MultithreadedRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleRenderer;
import edu.project4.transformations.ColoredTransformation;
import edu.project4.transformations.LinearTransformation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateImageAction {
    private final static Logger LOGGER = LogManager.getLogger();

    private GenerateImageAction() {
    }

    public static void generate(String configPath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Config configObject = mapper.readValue(new File(configPath), Config.class);

            validate(configObject);

            List<ColoredTransformation> affine = prepareAffine(
                    configObject.getRandomAffineTransformationsCount(),
                    configObject.getPresetAffineTransformations()
            );

            GeneratedPixelMap map;
            Renderer renderer;

            if (configObject.getThreadsCount() <= 1) {
                renderer = new SingleRenderer();
            } else {
                renderer = new MultithreadedRenderer(configObject.getThreadsCount());
            }

            map = renderer.render(
                    new ImageSize(configObject.getWidth(), configObject.getHeight()),
                    new GenerationBorders(
                            configObject.getxMin(),
                            configObject.getxMax(),
                            configObject.getyMin(),
                            configObject.getyMax()
                    ),
                    affine,
                    Arrays.stream(configObject.getNonlinearTransformations()).toList(),
                    configObject.getSamples(),
                    configObject.getIterations()
            );

            if (configObject.isWithCorrection()) {
                map = ImageUtils.correction(map, configObject.getGamma());
            }

            ImageUtils.save(
                    map,
                    Path.of(configObject.getDirectory(), configObject.getFilename()),
                    configObject.getFileType()
            );
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static List<ColoredTransformation> prepareAffine(int randomCount, PresetAffineTransformation[] preset) {
        List<ColoredTransformation> affine = new ArrayList<>();

        for (int i = 0; i < randomCount; i++) {
            affine.add(new ColoredTransformation(
                    LinearTransformation.randomTransformation(),
                    Color.generate()
            ));
        }

        if (preset != null) {
            for (PresetAffineTransformation presetObj : preset) {
                affine.add(new ColoredTransformation(
                        new LinearTransformation(
                                presetObj.getA(), presetObj.getB(), presetObj.getC(),
                                presetObj.getD(), presetObj.getE(), presetObj.getF()
                        ),
                        new Color(
                                presetObj.getRed(), presetObj.getGreen(), presetObj.getBlue()
                        )
                ));
            }
        }

        return affine;
    }

    private static void validate(Config config) {
        if (config.getWidth() <= 0) {
            throw new IllegalArgumentException("Неверная ширина изображения");
        }

        if (config.getHeight() <= 0) {
            throw new IllegalArgumentException("Неверная высота изображения");
        }

        if (config.getThreadsCount() <= 0) {
            throw new IllegalArgumentException("Неверное количество потоков");
        }

        if (config.getSamples() <= 0) {
            throw new IllegalArgumentException("Неверное количество повторений");
        }

        if (config.getIterations() <= 0) {
            throw new IllegalArgumentException("Неверное количество итераций");
        }

        if (config.getRandomAffineTransformationsCount() <= 0) {
            throw new IllegalArgumentException("Неверное количество случайных преобразований");
        }

        if (config.getRandomAffineTransformationsCount() < 0) {
            throw new IllegalArgumentException("Неверное количество случайных преобразований");
        }

        if (config.getRandomAffineTransformationsCount() == 0 && config.getPresetAffineTransformations().length == 0) {
            throw new IllegalArgumentException("Отсутствуют линейные преобразования");
        }

        if (config.getNonlinearTransformations().length == 0) {
            throw new IllegalArgumentException("Отсутствуют нелинейные преобразования");
        }

        if (config.getDirectory() == null) {
            throw new IllegalArgumentException("Отсутствует путь для сохранения файла");
        }
    }
}
