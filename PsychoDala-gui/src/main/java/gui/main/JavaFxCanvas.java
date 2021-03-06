package gui.main;

import app.drawing.Drawing;
import app.drawing.graphic.Graphic;
import app.drawing.graphic.GraphicCanvas;
import app.drawing.graphic.impl.*;
import app.drawing.render.RenderTask;
import app.drawing.render.RendererCanvas;
import app.drawing.render.SpecialGraphicEnum;
import app.drawing.structure.DrawingObject;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by Guido on 10.04.2017.
 */
public class JavaFxCanvas
        implements RendererCanvas {

    public static final Graphic DEFAULT_BACKGROUND = new DefaultBackground();
    public static final Graphic BLACK_WHITE_TILES = new BlackWhiteTilesBackground();
    public static final Graphic DEFAULT_POINTER = new DefaultPointer();
    public static final Graphic DOT_POINTER = new DotPointer();
    public static final Graphic CROSS_POINTER = new CrossPointer();

    private Pane parent;
    private double width;
    private double height;
    private Canvas canvas;
    private GraphicsContext gc;

    private Graphic selectedBackground;
    private Graphic selectedPointer;

    public JavaFxCanvas(Pane parent) {
        this(parent, 400, 400);
    }

    public JavaFxCanvas(Pane parent, double width, double height) {
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.createCanvas();
        this.addCanvasToParent();
    }


    private void createCanvas() {
        this.canvas = new Canvas(this.width, this.height);
        this.canvas.setCursor(Cursor.NONE);
//        this.canvas.getStyleClass().add("rendererCanvas");
        this.gc = this.canvas.getGraphicsContext2D();

    }

    private void addCanvasToParent() {
        this.parent.getChildren().add(0, this.canvas);
    }

    @Override
    public Node toNode() {
        return this.canvas;
    }

    @Override
    public Image toImage() {
        return this.canvas.snapshot(new SnapshotParameters(), null);
    }

    @Override
    public Graphic getSelectedBackground() {
        return this.selectedBackground;
    }

    @Override
    public void selectBackground(Graphic background) {
        this.selectedBackground = background;
    }

    @Override
    public Graphic getSelectedPointer() {
        return this.selectedPointer;
    }

    @Override
    public void selectPointer(Graphic pointer) {
        this.selectedPointer = pointer;
    }

    @Override
    public void clear() {
        if (this.canvas == null || this.gc == null)
            return;

        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        this.gc.clearRect(0, 0, width, height);
    }

    @Override
    public void paint(SpecialGraphicEnum specialGraphicEnum) {
        Graphic graphic = null;
        GraphicCanvas graphicCanvas = null;
        switch (specialGraphicEnum) {
            case BACKGROUND:
                graphic = this.selectedBackground != null ? this.selectedBackground : JavaFxCanvas.DEFAULT_BACKGROUND;
                graphicCanvas = new GraphicCanvas(this.canvas.getWidth(), this.canvas.getHeight());
                break;
            case POINTER:
                graphic = this.selectedPointer != null ? this.selectedPointer : JavaFxCanvas.DOT_POINTER;
                graphicCanvas = new GraphicCanvas();
                break;
        }
        this.paint(graphic, graphicCanvas);
    }

    @Override
    public void paintDrawing(Drawing drawing) {
        if (drawing == null) return;
        List<DrawingObject> drawingObjects = drawing.getDrawingObjects();
        for (DrawingObject drawingObject : drawingObjects) {
            this.paintDrawingObject(drawingObject);
        }
    }

    private void paintDrawingObject(DrawingObject drawingObject) {
        Graphic graphic = DefaultGraphicFactory.Instance().createGraphicForDrawingObject(drawingObject);
        this.paint(graphic, new GraphicCanvas());
    }

    private void paint(Graphic graphic, GraphicCanvas graphicCanvas) {
        if (graphic == null)
            return;
        graphic.paint(graphicCanvas);
        if (!graphicCanvas.isUsed())
            return;
        this.gc.save();
        for (RenderTask renderTask : graphicCanvas.getRenderTasks()) {
            renderTask.run(gc);
        }
        this.gc.restore();
//        this.gc.translate(graphicImage.getPosition().getX(), graphicImage.getPosition().getY());
//        this.gc.drawImage(graphicImage.getImage(), 0, 0);
    }

}
