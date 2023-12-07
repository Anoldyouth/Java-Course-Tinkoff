package edu.project4.transformations;

import edu.project4.records.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double radiusSquared = radiusSquared(point);

        double x = point.x() * Math.sin(radiusSquared) - point.y() * Math.cos(radiusSquared);
        double y = point.x() * Math.cos(radiusSquared) + point.y() * Math.sin(radiusSquared);

        return new Point(x, y);
    }
}
