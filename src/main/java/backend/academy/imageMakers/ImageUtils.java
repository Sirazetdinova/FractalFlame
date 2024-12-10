package backend.academy.imageMakers;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ImageUtils {
    private static final int RED_BITS = 16;
    private static final int GREEN_BITS = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        if (image == null || filename == null || format == null) {
            throw new IllegalArgumentException("Image, filename, and format must not be null");
        }
        if (!ImageIO.getImageWritersByFormatName(format.name().toLowerCase()).hasNext()) {
            throw new IllegalArgumentException("Unsupported image format: " + format);
        }

        File outputFile = filename.toFile();
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            log.error("Failed to create directories for: {}", parentDir.getAbsolutePath());
            throw new RuntimeException("Failed to create directories for: " + parentDir.getAbsolutePath());
        }

        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.getR() << RED_BITS) | (pixel.getG() << GREEN_BITS) | pixel.getB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        try {
            if (!ImageIO.write(bufferedImage, format.name().toLowerCase(), outputFile)) {
                log.error("Failed to write image to file: {}", filename);
                throw new RuntimeException("Failed to write image: " + filename);
            }
        } catch (IOException e) {
            log.error("Error while saving image to file: {}", filename, e);
            throw new RuntimeException("Error while saving image to file: " + filename, e);
        }
    }
}
