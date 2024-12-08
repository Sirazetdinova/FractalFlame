package backend.academy.samples.imageMakerTests;

import backend.academy.entities.FractalImage;
import backend.academy.imageMakers.ImageFormat;
import backend.academy.imageMakers.ImageUtils;
import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForImageMaker {
    @Test
    @DisplayName("create and save image test")
    void saveImageTest() {
        FractalImage image = FractalImage.create(100, 100);
        File file = new File("src/main/java/edu/fractalFlame/images/test.png");
        if (file.exists()) {
            file.delete();
        }
        ImageUtils.save(image, Path.of("src/main/java/edu/fractalFlame/images/test.png"), ImageFormat.PNG);
        Assertions.assertTrue(new File("src/main/java/edu/fractalFlame/images/test.png").exists());
    }
}
