package graphic;

import drawing.DrawingObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guido on 09.04.2017.
 */
public class GraphicFactory {

    private static GraphicFactory INSTANCE;
    private Map<Class<? extends DrawingObject>, Graphic> classGraphicMap = new HashMap<>();

    private GraphicFactory() {
        this.registerDefaultGraphics();
    }

    public static GraphicFactory Instance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphicFactory();
        }
        return INSTANCE;
    }

    private void registerDefaultGraphics() {

    }

    public Graphic createGraphicFrom(Class<? extends DrawingObject> theClass) {
        if (theClass == null) {
            return null;
        }
        Graphic graphic = this.classGraphicMap.get(theClass);
        return graphic;
    }

    public void registerGraphic(Class<? extends DrawingObject> theClass, Graphic graphic) {
        if (theClass == null) {
            return;
        }
        if (graphic == null) {
            return;
        }
        this.classGraphicMap.put(theClass, graphic);
    }

}
