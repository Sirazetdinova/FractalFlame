package backend.academy.samples.transformationsTests;

import backend.academy.transformations.AffineTransformation;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForAffineTransformations {
    @Test
    @DisplayName("is correct affine coefficients test")
    void isCorrectCoefficientsTest() {
        AffineTransformation affineTransformation = AffineTransformation.generate(ThreadLocalRandom.current());
        Assertions.assertTrue(
            (Math.pow(affineTransformation.a(), 2) + Math.pow(affineTransformation.d(), 2) < 1)
                && (Math.pow(affineTransformation.b(), 2) + Math.pow(affineTransformation.e(), 2) < 1)
                && ((Math.pow(affineTransformation.a(), 2) + Math.pow(affineTransformation.b(), 2)
                + Math.pow(affineTransformation.d(), 2) + Math.pow(affineTransformation.e(), 2)
                < (1 + (Math.pow(affineTransformation.a() * affineTransformation.e() - affineTransformation.b()
                * affineTransformation.d(), 2))))));
    }

    @Test
    @DisplayName("generate list of transformation test")
    void generateListOfTransformationsTest() {
        Assertions.assertEquals(AffineTransformation.generateAffineList(10).size(), 10);
    }
}
