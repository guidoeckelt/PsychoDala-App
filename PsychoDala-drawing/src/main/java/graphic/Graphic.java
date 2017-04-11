package graphic;

import drawing.Drawing;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Guido on 09.04.2017.
 */
public abstract class Graphic {

    protected Point2D position;
    protected Drawing drawing;
    protected GraphicCanvas graphicCanvas;

    public Graphic(Drawing drawing) {
        this.drawing = drawing;
    }

    public void paint(GraphicCanvas graphicCanvas) {
        this.graphicCanvas = graphicCanvas;
        this.specificPaint();
    }

    public void render(GraphicsContext gc) {
        for (GraphicTask graphicTask : this.graphicCanvas.getGraphicTasks()) {
            graphicTask.run(gc);
        }
    }

    protected abstract void specificPaint();

}
