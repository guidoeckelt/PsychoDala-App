package app.drawing.graphic;

import app.drawing.structure.DrawingObject;

/**
 * Created by Guido on 10.04.2017.
 */
public interface GraphicFactory {

    boolean registerGraphicForDrawingObject(Class<? extends DrawingObject> drawingObjectClass, Class<? extends DrawingGraphic> graphicClass);

    Graphic createGraphicForDrawingObject(DrawingObject theClass);

}
