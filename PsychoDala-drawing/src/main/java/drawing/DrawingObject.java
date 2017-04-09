package drawing;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Created by Guido on 06.07.2016.
 */
public abstract class DrawingObject {

    protected Point2D measurePoint;
    protected Dimension2D size;

//======CTOR
    public DrawingObject(Point2D measurePoint, Dimension2D size) {
        this.measurePoint = measurePoint;
        this.size = size;
    }
//======================Abstract


//========Getter
    public Point2D getMeasurePoint() {
        return measurePoint;
    }

    public void setMeasurePoint(Point2D measurePoint) {
        this.measurePoint = measurePoint;
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }
}
