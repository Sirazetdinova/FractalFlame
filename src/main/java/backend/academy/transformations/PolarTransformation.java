package backend.academy.transformations;

import backend.academy.entities.Point;

public class PolarTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = Math.atan(x / y) / Math.PI;
        double newY = Math.sqrt(x * x + y * y) - 1;
        return new Point(newX, newY);
    }
}
