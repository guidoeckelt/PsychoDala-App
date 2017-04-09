package graphic;

import javafx.application.Platform;
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

    public GraphicCanvas() {
        this(100,100);
    }

    public GraphicCanvas(double width, double height) {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width,height);
        this.gc = this.canvas.getGraphicsContext2D();
    }

    public void setFill(Color fill) {
        this.gc.setFill(fill);
    }
    public void fillRect(double x, double y, double width, double height) {
        this.gc.fillRect(x,y, width,height);
    }
    public Image image() {
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

}
