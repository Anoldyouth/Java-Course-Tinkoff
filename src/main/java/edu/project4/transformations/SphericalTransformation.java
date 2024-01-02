package edu.project4.transformations;

import edu.project4.records.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radiusSquared = radiusSquared(point);

        double x = point.x() / radiusSquared;
        double y = point.y() / radiusSquared;

        return new Point(x, y);
    }
}
