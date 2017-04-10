package graphic;

import drawing.DrawingObject;

/**
 * Created by Guido on 10.04.2017.
 */
public interface GraphicFactory {

    boolean registerGraphicFor(Class<? extends DrawingObject> theClass, Graphic graphic);

    Graphic createGraphicFor(DrawingObject theClass);

}
