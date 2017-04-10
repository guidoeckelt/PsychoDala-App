package main;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import render.RendererCanvas;

/**
 * Created by Guido on 10.04.2017.
 */
public class JavaFxCanvas
        implements RendererCanvas {

    private Pane parent;
    private double width;
    private double height;
    private Canvas canvas;

    public JavaFxCanvas(Pane parent) {
        this(parent, 400, 400);
    }

    public JavaFxCanvas(Pane parent, double width, double height) {
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.canvas = new Canvas(width, height);
        this.addCanvasToParent();
    }

    public void setParent(Pane parent) {
        this.parent = parent;
    }

    private void addCanvasToParent() {
        this.parent.getChildren().add(this.canvas);
    }

    @Override
    public Node toNode() {
        return this.canvas;
    }

}
