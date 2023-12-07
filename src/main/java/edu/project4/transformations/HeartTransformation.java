package edu.project4.transformations;

import edu.project4.records.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radius = radius(point);
        double theta = theta(point);

        double x = radius * Math.sin(radius * theta);
        double y = radius * -Math.cos(radius * theta);

        return new Point(x, y);
    }
}
