package backend.academy.renderers;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Rect;
import backend.academy.transformations.AffineTransformation;
import backend.academy.transformations.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer implements Renderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    ) {
        List<AffineTransformation> affineTransformations = AffineTransformation.generateAffineList(affineAmount);
        for (int num = 0; num < samples; num++) {
            renderFrame(iterPerSample, symmetry, canvas, world, variants, affineTransformations);
        }
        return canvas;
    }
}
