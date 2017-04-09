package main;

import javafx.scene.Parent;
import render.RendererCanvas;

/**
 * Created by Guido on 10.04.2017.
 */
public class JavaFxCanvas
        implements RendererCanvas {

    private Parent parent;
    private double width;
    private double height;

    public JavaFxCanvas(Parent parent) {
        this(parent, 400, 400);
    }

    public JavaFxCanvas(Parent parent, double width, double height) {
        this.width = width;
        this.height = height;
        this.parent = parent;

    }

}
