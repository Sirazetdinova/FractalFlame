package backend.academy.imageMakers;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {
    private static final int RED_BITS = 16;
    private static final int GREEN_BITS = 8;

    private ImageUtils() {}

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.getR() << RED_BITS) | (pixel.getG() << GREEN_BITS) | pixel.getB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
