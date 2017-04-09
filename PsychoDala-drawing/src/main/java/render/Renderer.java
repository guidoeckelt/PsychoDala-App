package render;

import drawing.Drawing;
import graphic.Graphic;
import javafx.scene.canvas.Canvas;

/**
 * Created by Guido on 09.04.2017.
 */
public interface Renderer {

    void start();
    void stop();
    boolean isRunning();

    Drawing getDrawing();

    void setDrawing(Drawing drawing);

    Graphic getBackground();

    void setBackground(Graphic background);

    Canvas getCanvas();

    void setCanvas(Canvas canvas);
}
