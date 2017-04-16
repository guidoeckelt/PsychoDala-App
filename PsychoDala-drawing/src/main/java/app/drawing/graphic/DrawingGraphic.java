package app.drawing.graphic;

import app.drawing.Drawing;

/**
 * Created by Guido on 14.04.2017.
 */
public class DrawingGraphic
        implements Graphic {

    protected Drawing drawing;

    public DrawingGraphic(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void paint(GraphicCanvas gc) {

    }

}
