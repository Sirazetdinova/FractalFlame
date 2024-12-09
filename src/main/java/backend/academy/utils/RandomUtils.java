package backend.academy.utils;

import backend.academy.entities.Point;
import backend.academy.entities.Rect;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtils {
    private RandomUtils() {
    }

    public static <E> E random(List<E> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static Point random(Rect rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Rect must not be null");
        }
        return new Point(
            ThreadLocalRandom.current().nextDouble(rect.x(), rect.x() + rect.width()),
            ThreadLocalRandom.current().nextDouble(rect.y(), rect.y() + rect.height())
        );
    }
}
