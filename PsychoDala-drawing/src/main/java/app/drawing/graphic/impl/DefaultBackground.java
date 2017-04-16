package app.drawing.graphic.impl;

import app.drawing.graphic.Graphic;
import app.drawing.graphic.GraphicCanvas;
import javafx.scene.paint.Color;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultBackground
        implements Graphic {

    private final Color defaultBackgroundColor = Color.color(0.76, 0.76, 0.76, 1);

    @Override
    public void paint(GraphicCanvas gc) {
        gc.setFill(this.defaultBackgroundColor);
        double width = gc.getWidth();
        double height = gc.getHeight();
        gc.fillRect(0, 0, width, height);
//        this.position = new Point2D(0,0);
    }

}
