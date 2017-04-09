package graphic;

import javafx.geometry.Point2D;

/**
 * Created by Guido on 09.04.2017.
 */
public abstract class Graphic {

    protected Point2D position;

    public GraphicImage paint(GraphicCanvas gc){
        this.specificPaint(gc);
        if (!gc.isUsed()) {
            return null;
        }
        return new GraphicImage(gc.toImage(), this.position);
    }

    protected abstract void specificPaint(GraphicCanvas gc);

}
