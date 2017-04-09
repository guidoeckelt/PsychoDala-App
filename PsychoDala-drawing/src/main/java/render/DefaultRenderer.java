package render;

import drawing.Drawing;
import drawing.DrawingObject;
import graphic.Graphic;
import graphic.GraphicCanvas;
import graphic.GraphicFactory;
import graphic.GraphicImage;
import graphic.impl.BlackWhiteTilesBackground;
import graphic.impl.DefaultBackground;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guido on 01.04.2017.
 */
public class DefaultRenderer
    implements Renderer{

    public static final Graphic DEFAULT_BACKGROUND = new DefaultBackground();
    public static final Graphic BLACK_WHITE_TILES = new BlackWhiteTilesBackground();
    private final Graphic defaultPointer;

    private Canvas canvas;
    private GraphicsContext gc;
    private Graphic selectedBackground;

    private Drawing currentDrawing;
    private Timer renderProcess;
    private long paintDelay;
    private boolean isRunning;


    public DefaultRenderer() {
        this(null);
    }

    public DefaultRenderer(Canvas c) {
        this.defaultPointer = null;
        this.canvas = c;
        this.gc = c.getGraphicsContext2D();

        this.paintDelay = 10;

    }

    @Override
    public void start() {
        this.startRenderProcess();
    }
    private void startRenderProcess(){

        this.renderProcess = new Timer("RenderProcess");
        TimerTask task = new DefaultRendererTask(this);
        try {
            this.renderProcess.scheduleAtFixedRate(task, 0, this.paintDelay);
        }catch (IllegalStateException e){
            System.out.println("IllegalThreadStateException: "+e.getMessage());
            return;
        }catch (IllegalArgumentException e){
            System.out.println("IllegalArgumentException: "+e.getMessage());
            return;
        }catch (NullPointerException e){
            System.out.println("NullPointerException: "+e.getMessage());
            return;
        }

        this.isRunning = true;
    }
    @Override
    public void stop() {
        this.stopRenderProcess();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clearCanvas();
    }
    private void stopRenderProcess(){

        try{
            this.renderProcess.cancel();
        }catch(Exception e){
            System.out.println("Exception: "+e.getMessage());
            return;
        }

        this.isRunning = false;
    }
    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public Drawing getDrawing() {
        return this.currentDrawing;
    }

    @Override
    public void setDrawing(Drawing drawing) {
        this.currentDrawing = drawing;
    }

    @Override
    public Graphic getBackground() {
        return this.selectedBackground;
    }

    @Override
    public void setBackground(Graphic background) {
        this.selectedBackground = background;
    }

    @Override
    public Canvas getCanvas() {
        return this.canvas;
    }

    @Override
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    void paintAction(){
        Platform.runLater(() -> {
            this.clearCanvas();
            this.paint(SpecialGraphicEnum.BACKGROUND);
            this.paintDrawing();
            this.paint(SpecialGraphicEnum.POINTER);
        });
    }

    private void clearCanvas() {
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        this.gc.clearRect(0,0, width,height);
    }
    private void paintDrawing(){
        if(currentDrawing == null) return;
        List<DrawingObject> drawingObjects = this.currentDrawing.getDrawingObjects();
        for (DrawingObject drawingObject : drawingObjects) {
            Graphic graphic = GraphicFactory.Instance().createGraphicFrom(drawingObject.getClass());
            this.paint(graphic, new GraphicCanvas());
        }
    }

    private void paint(SpecialGraphicEnum type) {
        Graphic graphic = null;
        GraphicCanvas graphicCanvas = null;
        switch(type){
            case BACKGROUND:
                graphic = this.selectedBackground!=null?this.selectedBackground:DefaultRenderer.DEFAULT_BACKGROUND;
                graphicCanvas = new GraphicCanvas(this.canvas.getWidth(),this.canvas.getHeight());
                break;
            case POINTER:
                graphic = this.defaultPointer;
                graphicCanvas = new GraphicCanvas();
                break;
        }
        this.paint(graphic, graphicCanvas);
    }
    private void paint(Graphic graphic, GraphicCanvas graphicCanvas){
        if(graphic == null) return;
        GraphicImage graphicImage = graphic.paint(graphicCanvas);
        if (graphicImage == null) {
            return;
        }
        this.gc.drawImage(graphicImage.getImage(),
                        graphicImage.getPosition().getX(),graphicImage.getPosition().getY());
    }

}