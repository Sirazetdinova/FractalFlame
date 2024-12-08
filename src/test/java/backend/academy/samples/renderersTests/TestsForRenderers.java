package backend.academy.samples.renderersTests;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Rect;
import backend.academy.renderers.MultiThreadRenderer;
import backend.academy.renderers.SingleThreadRenderer;
import backend.academy.transformations.LinearTransformation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForRenderers {
    @Test
    @DisplayName("single thread renderer test")
    void correctSingleThreadRenderTest() {
        Assertions.assertDoesNotThrow(() -> new SingleThreadRenderer().render(
            FractalImage.create(100, 100),
            new Rect(-1, -1, 2, 2),
            List.of(new LinearTransformation()),
            2,
            1,
            1,
            1
        ));
    }

    @Test
    @DisplayName("multi thread renderer test")
    void correctMultiThreadRenderTest() {
        Assertions.assertDoesNotThrow(() -> new MultiThreadRenderer().render(
            FractalImage.create(100, 100),
            new Rect(-1, -1, 2, 2),
            List.of(new LinearTransformation()),
            2,
            1,
            1,
            1
        ));
    }
}
