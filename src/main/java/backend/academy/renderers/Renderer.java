package backend.academy.renderers;

import backend.academy.entities.FractalImage;
import backend.academy.entities.Rect;
import backend.academy.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int samples,
        int iterPerSample
    );
}
