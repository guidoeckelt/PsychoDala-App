package graphic.defaultt;

import graphic.GraphicTask;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by Guido on 11.04.2017.
 */
public class SetFillTask
        implements GraphicTask {

    private Paint fill;

    public SetFillTask(Paint fill) {
        this.fill = fill;
    }

    @Override
    public void run(GraphicsContext gc) {
        gc.setFill(fill);
    }
}
