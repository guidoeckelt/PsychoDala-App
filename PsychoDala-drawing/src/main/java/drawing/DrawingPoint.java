package drawing;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Created by Guido on 06.07.2016.
 */
public class DrawingPoint
        extends DrawingObject{

//======CTOR
    public DrawingPoint(Point2D measurePoint) {
        this(measurePoint,new Dimension2D(1,1));
    }
    public DrawingPoint(Point2D measurePoint, Dimension2D size) {
        super(measurePoint, size);
    }
//=====Meehtods
    public void translate() {

    }

}
