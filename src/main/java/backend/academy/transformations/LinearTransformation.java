package backend.academy.transformations;

import backend.academy.entities.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
