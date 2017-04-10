package graphic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Guido on 09.04.2017.
 */
public class GraphicCanvas {

    private double width;
    private double height;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean isUsed;

    public GraphicCanvas() {
        this(100,100);
    }

    public GraphicCanvas(double width, double height) {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width,height);
        this.gc = this.canvas.getGraphicsContext2D();
        this.isUsed = false;
    }

    public void setFill(Color fill) {
        this.gc.setFill(fill);
        this.isUsed = true;
    }

    public void fillRect(double x, double y, double width, double height) {
        this.gc.fillRect(x,y, width,height);
        this.isUsed = true;
    }

    public Image toImage() {
        return this.canvas.snapshot(null,null);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
