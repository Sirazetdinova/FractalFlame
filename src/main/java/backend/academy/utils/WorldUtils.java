package backend.academy.utils;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import backend.academy.entities.Point;
import backend.academy.entities.Rect;

public final class WorldUtils {
    private WorldUtils() {
    }

    public static Pixel mapRange(FractalImage canvas, Point point, Rect world) {
        if (canvas == null || point == null || world == null) {
            throw new IllegalArgumentException("Canvas, point, and world must not be null");
        }
        if (!world.contains(point)) {
            return null;
        }
        int pixelX = (int) ((point.x() - world.x()) / world.width() * canvas.width());
        int pixelY = (int) ((point.y() - world.y()) / world.height() * canvas.height());
        return canvas.pixel(pixelX, pixelY);
    }

    public static Point rotate(Rect world, Point point, double theta) {
        if (world == null || point == null) {
            throw new IllegalArgumentException("World and point must not be null");
        }
        double centerX = world.x() + world.width() / 2;
        double centerY = world.y() + world.height() / 2;

        double rotatedX = (point.x() - centerX) * Math.cos(theta) - (point.y() - centerY) * Math.sin(theta);
        double rotatedY = (point.x() - centerX) * Math.sin(theta) + (point.y() - centerY) * Math.cos(theta);

        return new Point(rotatedX + centerX, rotatedY + centerY);
    }
}
