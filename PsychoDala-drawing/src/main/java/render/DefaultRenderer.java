package render;

import drawing.Drawing;
import drawing.DrawingObject;
import graphic.impl.BlackWhiteTilesBackground;
import graphic.impl.DefaultBackground;
import graphic.Graphic;
import graphic.GraphicCanvas;
import graphic.GraphicImage;
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


    public DefaultRenderer(Canvas c) {
        this.canvas = c;
        this.gc = c.getGraphicsContext2D();
        this.paintDelay = 10;

        this.defaultPointer = null;
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
        this.clear();
        this.clear();
        this.clear();
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
    public void setDrawing(Drawing drawing) {
        this.currentDrawing = drawing;
    }

    @Override
    public void setBackground(Graphic background) {
        this.selectedBackground = background;
    }
    @Override
    public Graphic getBackground() {
        return this.selectedBackground;
    }

    void paintAction(){
        Platform.runLater(() -> {
            this.clear();
            this.paint(SpecialDrawing.BACKGROUND);
            this.paintDrawing();
            this.paint(SpecialDrawing.POINTER);
        });
    }
    private void clear() {
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        this.gc.clearRect(0,0, width,height);
    }
    private void paintDrawing(){
        if(currentDrawing == null) return;
        List<DrawingObject> drawingObjects = this.currentDrawing.getDrawingObjects();
        for (DrawingObject drawingObject: drawingObjects) {
            Graphic graphic = null;
            this.paint(graphic, new GraphicCanvas());
        }
    }
    private void paint(SpecialDrawing type) {
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
        this.gc.drawImage(graphicImage.getImage(),
                        graphicImage.getPosition().getX(),graphicImage.getPosition().getY());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.renderProcess.cancel();
    }
}