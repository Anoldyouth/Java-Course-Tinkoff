package edu.project4.transformations;

import edu.project4.records.Point;

public class SpiralTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = (Math.cos(theta) + Math.sin(radius)) / radius;
        double y = (Math.sin(theta) - Math.cos(radius)) / radius;

        return new Point(x, y);
    }
}
