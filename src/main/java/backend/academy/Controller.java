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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private static final Path OUTPUT_DIRECTORY = Path.of(System.getProperty("user.home"), "FractalFlame");
    private static final Path IMAGE_PATH = OUTPUT_DIRECTORY.resolve("image.png");

    private static final int DEFAULT_IMAGE_WIDTH = 3840;
    private static final int DEFAULT_IMAGE_HEIGHT = 2160;
    private static final Rect DEFAULT_RENDER_RECT = new Rect(-3, -2, 6, 6);

    private static final Map<String, Transformation> TRANSFORMATION_MAP = Map.of(
        "Disk Transformation", new DiskTransformation(),
        "Heart Transformation", new HeartTransformation(),
        "Hyperbolic Transformation", new HyperbolicTransformation(),
        "Linear Transformation", new LinearTransformation(),
        "Polar Transformation", new PolarTransformation(),
        "Sine Transformation", new SineTransformation(),
        "Spherical Transformation", new SphericalTransformation()
    );

    private final Renderer renderer = new Renderer();

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

    private void renderImage(
        List<Transformation> transformations,
        int affine,
        int symmetry,
        int samples,
        int iterations,
        boolean useMultithreading
    ) {
        try {
            backend.academy.renderers.Renderer localRenderer = useMultithreading
                ? new MultiThreadRenderer()
                : new SingleThreadRenderer();

            FractalImage image = localRenderer.render(
                FractalImage.create(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT),
                DEFAULT_RENDER_RECT,
                transformations,
                affine,
                symmetry,
                samples,
                iterations
            );

            LogGammaCorrectionProcessor processor = new LogGammaCorrectionProcessor();
            processor.process(image, 1.0);
            ImageUtils.save(image, IMAGE_PATH, ImageFormat.PNG);

        } catch (Exception ignored) {
            // Ошибка подавлена.
        }
    }

    public void createDirectory() {
        try {
            if (!Files.exists(OUTPUT_DIRECTORY)) {
                Files.createDirectories(OUTPUT_DIRECTORY);
            }
        } catch (Exception ignored) {
            // Ошибка подавлена.
        }
    }
}
