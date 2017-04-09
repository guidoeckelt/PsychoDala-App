package graphic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Created by Guido on 09.04.2017.
 */
public abstract class Graphic {

    protected Point2D position;

    public GraphicImage paint(GraphicCanvas gc){
        this.specificPaint(gc);
        Image image = gc.image();
        return new GraphicImage(image, this.position);
    }

    protected abstract void specificPaint(GraphicCanvas gc);

}
