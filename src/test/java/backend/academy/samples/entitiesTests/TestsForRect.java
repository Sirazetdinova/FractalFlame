package backend.academy.samples.entitiesTests;

import backend.academy.entities.Point;
import backend.academy.entities.Rect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForRect {
    @Test
    @DisplayName("rect contains test")
    void rectContainsTest() {
        Rect rect = new Rect(0, 0, 5, 5);
        Assertions.assertTrue(rect.contains(new Point(2, 2)));
        Assertions.assertFalse(rect.contains(new Point(10, 10)));
    }
}
