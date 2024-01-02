package edu.project4;

import edu.project4.records.Color;
import edu.project4.records.GeneratedPixelMap;
import edu.project4.records.Pixel;
import edu.project4.records.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class ImageUtils {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int RED = 16;
    private final static int GREEN = 8;

    private ImageUtils() {
    }

    public static GeneratedPixelMap correction(GeneratedPixelMap map, double gamma) {
        List<CorrectionPixelPoint> correctionPixelPointList = getCorrectionPixelPoints(map);

        var pixelMap = correctionPixelPointList
                .stream()
                .collect(Collectors.toMap(
                        CorrectionPixelPoint::point,
                        correctionPixelPoint -> correctionPixelPoint.toPixel(gamma)
                ));

        return new GeneratedPixelMap(
                pixelMap,
                map.width(),
                map.height()
        );
    }

    @NotNull
    private static List<CorrectionPixelPoint> getCorrectionPixelPoints(GeneratedPixelMap map) {
        double max = 0;
        List<CorrectionPixelPoint> correctionPixelPointList = new ArrayList<>();

        for (var entry : map.pixelMap().entrySet()) {
            CorrectionPixelPoint point = new CorrectionPixelPoint(
                    entry.getKey(),
                    Math.log10(entry.getValue().hitCount()),
                    entry.getValue()
            );

            correctionPixelPointList.add(point);
            if (point.normal > max) {
                max = point.normal;
            }
        }

        return correctionPixelPointList;
    }

    private static Color colorCorrection(Color color, double normal, double gamma) {
        return new Color(
                (int) (color.r() * Math.pow(normal, 1 / gamma)),
                (int) (color.g() * Math.pow(normal, 1 / gamma)),
                (int) (color.b() * Math.pow(normal, 1 / gamma))
        );
    }

    public static void save(GeneratedPixelMap map, Path filename, ImageFormat format) {
        BufferedImage image = new BufferedImage(map.width(), map.height(), BufferedImage.TYPE_INT_RGB);

        setPixels(image, map.pixelMap());

        try {
            Path fullpath = addExtensionToPath(filename, format.name().toLowerCase());
            Files.createDirectories(fullpath.getParent());
            File outputfile = addExtensionToPath(filename, format.name().toLowerCase()).toFile();
            ImageIO.write(image, format.name().toLowerCase(), outputfile);
            LOGGER.info("Изображение сохранено в " + fullpath);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void setPixels(BufferedImage image, Map<Point, Pixel> pixelMap) {
        Pixel defaultPixel = new Pixel(new Color(0, 0, 0), 1);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = pixelMap.getOrDefault(new Point(x, y), defaultPixel);
                int rgb = (pixel.color().r() << RED) | (pixel.color().g() << GREEN) | pixel.color().b();
                image.setRGB(x, y, rgb);
            }
        }
    }

    private static Path addExtensionToPath(Path originalPath, String fileExtension) {
        // Получаем имя файла без расширения
        String fileNameWithoutExtension = originalPath.getFileName().toString().split("\\.")[0];

        // Объединяем имя файла с новым расширением и создаем новый путь
        return originalPath.resolveSibling(fileNameWithoutExtension + "." + fileExtension);
    }

    private record CorrectionPixelPoint(Point point, double normal, Pixel pixel) {
        public Pixel toPixel(double gamma) {
            return new Pixel(
                    colorCorrection(
                            pixel.color(),
                            normal,
                            gamma
                    ),
                    pixel.hitCount()
            );
        }
    }
}
