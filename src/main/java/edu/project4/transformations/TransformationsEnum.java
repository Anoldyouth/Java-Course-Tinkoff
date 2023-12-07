package edu.project4.transformations;

import edu.project4.transformations.CylinderTransformation;
import edu.project4.transformations.DiamondTransformation;
import edu.project4.transformations.DiscTransformation;
import edu.project4.transformations.FisheyeTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HorseshoeTransformation;
import edu.project4.transformations.HyperbolicTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.SpiralTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.Transformation;

public enum TransformationsEnum {
    CYLINDER(new CylinderTransformation()),
    DIAMOND(new DiamondTransformation()),
    DISC(new DiscTransformation()),
    FISHEYE(new FisheyeTransformation()),
    HANDKERCHIEF(new HandkerchiefTransformation()),
    HEART(new HeartTransformation()),
    HORSESHOE(new HorseshoeTransformation()),
    HYPERBOLIC(new HyperbolicTransformation()),
    POLAR(new PolarTransformation()),
    SPHERICAL(new SphericalTransformation()),
    SPIRAL(new SpiralTransformation()),
    SWIRL(new SwirlTransformation());

    private final Transformation transformation;

    TransformationsEnum(Transformation transformation) {
        this.transformation = transformation;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public static Transformation getByNumber(long number) {
        return values()[(int) (number % values().length)].getTransformation();
    }
}
