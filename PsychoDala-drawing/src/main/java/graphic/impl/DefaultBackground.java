package graphic.impl;

import graphic.Graphic;
import graphic.GraphicCanvas;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultBackground
    extends Graphic {

    private final Color defaultBackgroundColor = Color.color(0.3,0.3,0.3,1);

    @Override
    protected void specificPaint(GraphicCanvas gc) {
        gc.setFill(this.defaultBackgroundColor);
        double width = gc.getWidth();
        double height = gc.getHeight();
        gc.fillRect(0,0, width,height);
        this.position = new Point2D(0,0);
    }

}
