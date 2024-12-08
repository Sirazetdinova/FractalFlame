package backend.academy.transformations;

import backend.academy.entities.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {}
