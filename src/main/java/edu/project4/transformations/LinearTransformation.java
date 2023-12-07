package edu.project4.transformations;

import edu.project4.records.Point;
import java.util.Random;

public class LinearTransformation implements Transformation {
    private final static int BORDER = 2;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;

        return new Point(x, y);
    }

    public LinearTransformation(
            double a,
            double b,
            double c,
            double d,
            double e,
            double f
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public static LinearTransformation randomTransformation() {
        Random rand = new Random();
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;

        do {
            do {
                a = rand.nextDouble(-1, 1);
                d = rand.nextDouble(a * a, 1);

                if (rand.nextBoolean()) {
                    d = -d;
                }
            } while ((a * a + d * d) > 1);
            do {
                b = rand.nextDouble(-1, 1);
                e = rand.nextDouble(b * b, 1);

                if (rand.nextBoolean()) {
                    e = -e;
                }
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e)
                > (1 + (a * e - d * b) * (a * e - d * b)));

        c = rand.nextDouble(-BORDER, BORDER);
        f = rand.nextDouble(-BORDER, BORDER);

        return new LinearTransformation(a, b, c, d, e, f);
    }

    @Override
    public String toString() {
        return String.format("(%f * x + %f * y + %f, %f * x + %f * y + %f)", a, b, c, d, e, f);
    }
}
