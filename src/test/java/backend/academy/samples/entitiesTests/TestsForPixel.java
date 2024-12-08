package backend.academy.samples.entitiesTests;

import backend.academy.entities.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForPixel {
    @Test
    @DisplayName("correct color test")
    void correctColorTest() {
        Pixel pixel = new Pixel();
        pixel.setColor(100, 100, 100);
        pixel.setCorrectedColor(50, 50, 50);
        Assertions.assertEquals(pixel.getR(), 75);
        Assertions.assertEquals(pixel.getG(), 75);
        Assertions.assertEquals(pixel.getB(), 75);
    }
}
