package graphic.defaultt;

import com.sun.istack.internal.NotNull;
import drawing.DrawingObject;
import graphic.Graphic;
import graphic.GraphicFactory;
import graphic.NullGraphic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultGraphicFactory
        implements GraphicFactory {

    private static DefaultGraphicFactory INSTANCE;
    private Map<Class<? extends DrawingObject>, Graphic> classGraphicMap = new HashMap<>();

    private DefaultGraphicFactory() {
    }

    public static GraphicFactory Instance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultGraphicFactory();
        }
        return INSTANCE;
    }

    public boolean registerGraphicFor(Class<? extends DrawingObject> theClass, Graphic graphic) {
        if (theClass == null) {
            return false;
        }
        if (graphic == null) {
            return false;
        }
        this.classGraphicMap.put(theClass, graphic);
        return this.classGraphicMap.containsKey(theClass);
    }

    public Graphic createGraphicFor(DrawingObject drawingObject) {
        if (drawingObject == null) {
            return null;
        }
        return this.retrieveNewGraphicInstanceFor(drawingObject);
    }

    private Graphic retrieveNewGraphicInstanceFor(@NotNull DrawingObject drawingObject) {
        Class<? extends Graphic> graphicClass = this.classGraphicMap.get(drawingObject.getClass()).getClass();
        Constructor<? extends Graphic> apportiateConstructor = this.retrieveApportiateConstructor
                (graphicClass, drawingObject);
        return this.executeConstructor(apportiateConstructor, drawingObject);
    }

    private Constructor<? extends Graphic> retrieveApportiateConstructor
            (Class<? extends Graphic> graphicClass, DrawingObject drawingObject) {
        if (!this.hasOnlyConstructorWithDrawingObjectParameter(graphicClass)) {
            return this.retrieveConstructorWithoutParameters(graphicClass);
        }
        for (Constructor<?> constructor : graphicClass.getConstructors()) {
            if (this.hasOnlyConstructorWithDrawingObjectParameter(graphicClass)) {
                try {
                    return retrieveConstructor(constructor);
                } catch (Exception e) {
                    System.out.println("DefaultGraphicFactory-Exception: " + e.getMessage());
                }
            }
        }
        return null;
    }

    private Graphic executeConstructor
            (Constructor<? extends Graphic> apportiateConstructor, DrawingObject drawingObject) {
        if (apportiateConstructor.getParameters().length == 0) {
            return instantiateNewGraphicObject(apportiateConstructor);
        } else {
            return instantiateNewGraphicObject(apportiateConstructor, drawingObject);
        }
    }

    private boolean hasOnlyConstructorWithDrawingObjectParameter(Class<? extends Graphic> graphicClass) {
        if (graphicClass.getConstructors().length == 1) {
            if (graphicClass.getConstructors()[0].getParameters().length == 1) {
                Type parameterType = graphicClass.getConstructors()[0].getParameters()[0].getParameterizedType();
                return parameterType.equals(DrawingObject.class);
            }
        }
        return false;
    }

    private Constructor<? extends Graphic> retrieveConstructorWithoutParameters(Class<? extends Graphic> graphicClass) {
        for (Constructor<?> constructor : graphicClass.getConstructors()) {
            if (constructor.getParameters().length == 0) {
                try {
                    return this.retrieveConstructor(constructor);
                } catch (Exception e) {
                    System.out.println("DefaultGraphicFactory-Exception: " + e.getMessage());
                }
            }
        }
        return null;
    }

    private Constructor<? extends Graphic> retrieveConstructor(Constructor<?> constructor) throws Exception {
        try {
            return (Constructor<? extends Graphic>) constructor.getDeclaringClass().getConstructor();
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
            throw new Exception("No Constructor Found for " + constructor);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
            throw new Exception("Constructor could not be cast :" + constructor);
        }
    }

    private Graphic instantiateNewGraphicObject(Constructor<? extends Graphic> apportiateConstructor) {
        Graphic newGraphicInstance;
        try {
            return apportiateConstructor.newInstance();
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        }
        return newGraphicInstance;
    }

    private Graphic instantiateNewGraphicObject
            (Constructor<? extends Graphic> apportiateConstructor, DrawingObject drawingObject) {
        Graphic newGraphicInstance;
        try {
            return apportiateConstructor.newInstance(drawingObject);
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException: " + e.getMessage());
            newGraphicInstance = new NullGraphic();
        }
        return newGraphicInstance;

    }


}
