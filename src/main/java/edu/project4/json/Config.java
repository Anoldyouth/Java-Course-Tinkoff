package edu.project4.json;

import edu.project4.ImageFormat;
import edu.project4.transformations.TransformationsEnum;
import edu.project4.transformations.Transformation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("checkstyle:MagicNumber")
public class Config {
    private int height = 1080;
    private int width = 1920;
    private double xMin = -1.77;
    private double xMax = 1.77;
    private double yMin = -1;
    private double yMax = 1;
    private int threadsCount = 1;
    private int samples = 30;
    private int iterations = 100_000;
    private int randomAffineTransformationsCount = 5;
    private ImageFormat fileType = ImageFormat.BMP;
    private boolean withCorrection = true;
    private PresetAffineTransformation[] presetAffineTransformations;
    private String directory = "./";
    private String filename = LocalDateTime.now().toString();
    private Transformation[] nonlinearTransformations;
    private double gamma = 2.2;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public int getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getRandomAffineTransformationsCount() {
        return randomAffineTransformationsCount;
    }

    public void setRandomAffineTransformationsCount(int randomAffineTransformationsCount) {
        this.randomAffineTransformationsCount = randomAffineTransformationsCount;
    }

    public ImageFormat getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = ImageFormat.valueOf(fileType);
    }

    public boolean isWithCorrection() {
        return withCorrection;
    }

    public void setWithCorrection(boolean withCorrection) {
        this.withCorrection = withCorrection;
    }

    public PresetAffineTransformation[] getPresetAffineTransformations() {
        return presetAffineTransformations;
    }

    public void setPresetAffineTransformations(PresetAffineTransformation[] presetAffineTransformations) {
        this.presetAffineTransformations = presetAffineTransformations;
    }

    public Transformation[] getNonlinearTransformations() {
        if (nonlinearTransformations == null) {
            this.nonlinearTransformations = Arrays.stream(TransformationsEnum.values())
                    .map(TransformationsEnum::getTransformation)
                    .toList().toArray(new Transformation[]{});
        }

        return nonlinearTransformations;
    }

    public void setNonlinearTransformations(String[] nonlinearTransformations) {
        List<Transformation> transformations = new ArrayList<>();

        for (String transformationName: nonlinearTransformations) {
            transformations.add(TransformationsEnum.valueOf(transformationName).getTransformation());
        }

        this.nonlinearTransformations = transformations.toArray(new Transformation[]{});
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
}
