package render;

import drawing.Drawing;

/**
 * Created by Guido on 09.04.2017.
 */
public interface Renderer {

    void start();

    void stop();

    boolean isRunning();

    Drawing getSelectedDrawing();

    void selectDrawing(Drawing drawing);

    RendererCanvas getRendererCanvas();

    void setRendererCanvas(RendererCanvas rendererCanvas);

}
