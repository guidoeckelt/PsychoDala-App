package graphic.defaultt;

import graphic.GraphicTask;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Guido on 11.04.2017.
 */
public class FillRectTask
        implements GraphicTask {


    private double x;
    private double y;
    private double width;
    private double height;

    public FillRectTask(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run(GraphicsContext gc) {
        gc.fillRect(x, y, width, height);
    }

}
