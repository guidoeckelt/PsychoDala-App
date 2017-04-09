package render;

import drawing.DrawingObject;
import graphic.Graphic;
import graphic.GraphicCanvas;
import graphic.GraphicImage;
import javafx.application.Platform;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultRendererTask
    extends TimerTask{

    private DefaultRenderer renderer;

    public DefaultRendererTask(DefaultRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        this.renderer.paintAction();
    }

}
