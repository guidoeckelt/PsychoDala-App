package render;

import drawing.Drawing;
import graphic.Graphic;
import graphic.impl.BlackWhiteTilesBackground;

/**
 * Created by Guido on 09.04.2017.
 */
public interface Renderer {

    void start();
    void stop();
    boolean isRunning();
    void setDrawing(Drawing drawing);

    void setBackground(Graphic background);
    Graphic getBackground();
}
