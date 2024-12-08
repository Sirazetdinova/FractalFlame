package backend.academy.transformations;

import backend.academy.entities.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record AffineTransformation(
    double a, double b, double d, double e, double c, double f, Color color) implements Transformation {
    private static final int COLOR_RANGE = 256;

    private static boolean isCorrect(double a, double b, double d, double e, double c, double f) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }

    public static AffineTransformation generate(Random random) {
        double a = random.nextDouble(-1, 1);
        double b = random.nextDouble(-1, 1);
        double d = random.nextDouble(-1, 1);
        double e = random.nextDouble(-1, 1);
        double c = random.nextDouble(-1, 1);
        double f = random.nextDouble(-1, 1);
        while (!isCorrect(a, b, d, e, c, f)) {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
            c = random.nextDouble(-1, 1);
            f = random.nextDouble(-1, 1);
        }
        return new AffineTransformation(a, b, d, e, c, f,
            new Color(random.nextInt(COLOR_RANGE), random.nextInt(COLOR_RANGE), random.nextInt(COLOR_RANGE))
        );
    }

    public static List<AffineTransformation> generateAffineList(int amount) {
        List<AffineTransformation> transformations = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < amount; i++) {
            transformations.add(AffineTransformation.generate(random));
        }
        return transformations;
    }

    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }
}
