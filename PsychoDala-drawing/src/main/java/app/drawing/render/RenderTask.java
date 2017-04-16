package app.drawing.render;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Guido on 11.04.2017.
 */
public interface RenderTask {

    void run(GraphicsContext gc);

}
