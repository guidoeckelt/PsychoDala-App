package app.drawing.graphic.impl;

import app.drawing.graphic.DrawingGraphic;
import app.drawing.graphic.Graphic;
import app.drawing.graphic.GraphicFactory;
import app.drawing.structure.DrawingObject;

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
    private Map<Class<? extends DrawingObject>, Class<? extends DrawingGraphic>> drawingObjectGraphicMap = new HashMap<>();

    private DefaultGraphicFactory() {
    }

    public static GraphicFactory Instance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultGraphicFactory();
        }
        return INSTANCE;
    }

    public boolean registerGraphicForDrawingObject
            (Class<? extends DrawingObject> drawingObjectClass, Class<? extends DrawingGraphic> graphicClass) {
        if (drawingObjectClass == null) {
            return false;
        }
        if (graphicClass == null) {
            return false;
        }
        this.drawingObjectGraphicMap.put(drawingObjectClass, graphicClass);
        return this.drawingObjectGraphicMap.containsKey(drawingObjectClass);
    }

    public Graphic createGraphicForDrawingObject(DrawingObject drawingObject) {
        if (drawingObject == null) {
            return null;
        }
        Class<? extends DrawingGraphic> graphicClass = this.drawingObjectGraphicMap.get(drawingObject.getClass());
        if (this.hasOnlyConstructorWithDrawingObjectParameter(graphicClass)) {
            Constructor<? extends DrawingGraphic> constructor = this.retrieveConstructorWithDrawingObjectParameter(graphicClass);
            return this.executeConstructor(constructor, drawingObject);
        }
        return new NullGraphic();
    }

    private boolean hasOnlyConstructorWithDrawingObjectParameter(Class<? extends DrawingGraphic> graphicClass) {
        if (graphicClass.getConstructors().length == 1) {
            if (graphicClass.getConstructors()[0].getParameters().length == 1) {
                Type parameterType = graphicClass.getConstructors()[0].getParameters()[0].getParameterizedType();
                return parameterType.equals(DrawingObject.class);
            }
        }
        return false;
    }

    private boolean hasConstructorWithoutParameter(Class<? extends DrawingGraphic> graphicClass) {
        if (graphicClass.getConstructors().length == 2) {
            for (Constructor<?> constructor : graphicClass.getConstructors()) {
                if (constructor.getParameters().length == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private Constructor<? extends DrawingGraphic> retrieveConstructorWithoutParameters(Class<? extends DrawingGraphic> graphicClass) {
        for (Constructor<?> constructor : graphicClass.getConstructors()) {
            if (constructor.getParameters().length == 0) {
                try {
                    return this.retrieveTypedConstructor(constructor);
                } catch (Exception e) {
                    System.out.println("DefaultGraphicFactory-Exception: " + e.getMessage());
                }
            }
        }
        return null;
    }

    private Constructor<? extends DrawingGraphic> retrieveConstructorWithDrawingObjectParameter(Class<? extends DrawingGraphic> graphicClass) {
        for (Constructor<?> constructor : graphicClass.getConstructors()) {
            if (this.hasOnlyConstructorWithDrawingObjectParameter(graphicClass)) {
                try {
                    return retrieveTypedConstructor(constructor);
                } catch (Exception e) {
                    System.out.println("DefaultGraphicFactory-Exception: " + e.getMessage());
                }
            }
        }
        return null;
    }

    private Constructor<? extends DrawingGraphic> retrieveTypedConstructor(Constructor<?> constructor) throws Exception {
        try {
            return (Constructor<? extends DrawingGraphic>) constructor.getDeclaringClass().getConstructor();
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
            throw new Exception("No Constructor Found for " + constructor);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
            throw new Exception("Constructor could not be cast :" + constructor);
        }
    }

    private Graphic executeConstructor(Constructor<? extends DrawingGraphic> apportiateConstructor) {
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

    private Graphic executeConstructor(Constructor<? extends DrawingGraphic> apportiateConstructor, DrawingObject drawingObject) {
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
