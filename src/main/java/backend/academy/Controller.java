package backend.academy;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Rect;
import backend.academy.imageMakers.ImageFormat;
import backend.academy.imageMakers.ImageUtils;
import backend.academy.imageProcessors.LogGammaCorrectionProcessor;
import backend.academy.renderers.MultiThreadRenderer;
import backend.academy.renderers.SingleThreadRenderer;
import backend.academy.transformations.DiskTransformation;
import backend.academy.transformations.HeartTransformation;
import backend.academy.transformations.HyperbolicTransformation;
import backend.academy.transformations.LinearTransformation;
import backend.academy.transformations.PolarTransformation;
import backend.academy.transformations.SineTransformation;
import backend.academy.transformations.SphericalTransformation;
import backend.academy.transformations.Transformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller {
    private final Renderer renderer = new Renderer();
    public static final String IMAGE_PATH = "C:\\FractalFlame\\image.png";

    private static final Map<String, Transformation> TRANSFORMATION_MAP = Map.of(
        "Disk Transformation", new DiskTransformation(),
        "Heart Transformation", new HeartTransformation(),
        "Hyperbolic Transformation", new HyperbolicTransformation(),
        "Linear Transformation", new LinearTransformation(),
        "Polar Transformation", new PolarTransformation(),
        "Sine Transformation", new SineTransformation(),
        "Spherical Transformation", new SphericalTransformation()
    );

    public void run() {
        createDirectory();
        try {
            while (true) {
                renderer.displayMenu();
                List<String> selectedTransformations = renderer.getSelectedTransformations();

                int affine = renderer.promptForInt("Enter Affine Amount: ");
                int symmetry = renderer.promptForInt("Enter Symmetry: ");
                int samples = renderer.promptForInt("Enter Samples Amount: ");
                int iterations = renderer.promptForInt("Enter Iteration Amount: ");
                boolean useMultithreading = renderer.promptForBoolean("Enable MultiThreading?");

                List<Transformation> transformations = new ArrayList<>();
                for (String name : selectedTransformations) {
                    Transformation transformation = TRANSFORMATION_MAP.get(name);
                    if (transformation != null) {
                        transformations.add(transformation);
                    } else {
                        System.out.println("Warning: Transformation not recognized: " + name);
                    }
                }

                renderImage(transformations, affine, symmetry, samples, iterations, useMultithreading);
                if (!renderer.promptForBoolean("Render another image?")) {
                    break;
                }
            }
        } finally {
            renderer.closeScanner();
        }
    }

    private void renderImage(List<Transformation> transformations, int affine, int symmetry, int samples, int iterations, boolean useMultithreading) {
        try {
            FractalImage image;
            if (useMultithreading) {
                MultiThreadRenderer multiThreadRenderer = new MultiThreadRenderer();
                image = multiThreadRenderer.render(
                    FractalImage.create(3840, 2160),
                    new Rect(-3, -2, 6, 6),
                    transformations,
                    affine,
                    symmetry,
                    samples,
                    iterations
                );
            } else {
                SingleThreadRenderer singleThreadRenderer = new SingleThreadRenderer();
                image = singleThreadRenderer.render(
                    FractalImage.create(3840, 2160),
                    new Rect(-3, -2, 6, 6),
                    transformations,
                    affine,
                    symmetry,
                    samples,
                    iterations
                );
            }

            LogGammaCorrectionProcessor processor = new LogGammaCorrectionProcessor();
            processor.process(image, 1.0);

            ImageUtils.save(image, Path.of(IMAGE_PATH), ImageFormat.PNG);
            renderer.displayResult(IMAGE_PATH);
        } catch (Exception e) {
            log.error("Error occurred during rendering: ", e);
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void createDirectory() {
        Path path = Path.of("C:\\FractalFlame");
        if (!path.toFile().exists()) {
            path.toFile().mkdir();
        }
    }
}

