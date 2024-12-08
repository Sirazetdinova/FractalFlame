package backend.academy.samples.entitiesTests;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForFractalImage {
    @Test
    @DisplayName("create fractal image test")
    void createFractalImageTest() {
        FractalImage image = FractalImage.create(1920, 1080);
        Assertions.assertEquals(image.width(), 1920);
        Assertions.assertEquals(image.height(), 1080);
        Assertions.assertTrue(() -> {
            for (int i = 0; i < image.height() * image.width(); i++) {
                if (image.data()[i].getG() != 0
                || image.data()[i].getR() != 0
                || image.data()[i].getB() != 0
                || image.data()[i].getHitCount() != 0
                || image.data()[i].getNormal() != 0) {
                    return false;
                }
            }
            return true;
        });
    }

    @Test
    @DisplayName("fractal image contains test")
    void fractalImageContainsTest() {
        FractalImage image = FractalImage.create(1920, 1080);
        Assertions.assertTrue(image.contains(100, 100));
        Assertions.assertFalse(image.contains(10000, 10000));
    }

    @Test
    @DisplayName("fractal image get pixel test")
    void fractalImagePixelTest() {
        FractalImage image = FractalImage.create(5, 5);
        Pixel expected = image.data()[17];
        Assertions.assertEquals(expected, image.pixel(2, 3));
        Assertions.assertNull(image.pixel(100, 100));
    }
}
