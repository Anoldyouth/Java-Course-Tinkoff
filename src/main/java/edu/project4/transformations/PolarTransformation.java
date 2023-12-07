package edu.project4.transformations;

import edu.project4.records.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = theta(point) / Math.PI;
        double y = radius(point) - 1;

        return new Point(x, y);
    }
}
