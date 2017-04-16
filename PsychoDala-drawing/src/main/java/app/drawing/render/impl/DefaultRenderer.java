package app.drawing.render.impl;

import app.drawing.Drawing;
import app.drawing.render.Renderer;
import app.drawing.render.RendererCanvas;
import app.drawing.render.SpecialGraphicEnum;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Guido on 01.04.2017.
 */
public class DefaultRenderer
        implements Renderer {

    private RendererCanvas rendererCanvas;
    private ImageView imageView;
    private Drawing selectedDrawing;
    private Timer renderProcess;
    private long paintDelay;
    private boolean isRunning;

    public DefaultRenderer(RendererCanvas rendererCanvas, ImageView imageView) {
        this.rendererCanvas = rendererCanvas;
        this.imageView = imageView;

        this.paintDelay = 100;
    }

    @Override
    public void start() {
        this.startRenderProcess();
    }

    private void startRenderProcess(){
        this.renderProcess = new Timer("RenderProcess");
        TimerTask task = new DefaultRendererTimerTask(this);
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
        this.rendererCanvas.clear();
    }

    private void stopRenderProcess(){
        if (!this.isRunning)
            return;
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
    public Drawing getSelectedDrawing() {
        return this.selectedDrawing;
    }

    @Override
    public void selectDrawing(Drawing drawing) {
        this.selectedDrawing = drawing;
    }

    @Override
    public RendererCanvas getRendererCanvas() {
        return this.rendererCanvas;
    }

    @Override
    public void setRendererCanvas(RendererCanvas rendererCanvas) {
        this.stop();
        this.rendererCanvas = rendererCanvas;
        this.start();
    }

    void render() {
        this.rendererCanvas.clear();
        this.rendererCanvas.paint(SpecialGraphicEnum.BACKGROUND);
        this.rendererCanvas.paintDrawing(this.selectedDrawing);
        this.rendererCanvas.paint(SpecialGraphicEnum.POINTER);

//        Platform.runLater(() -> {
//            Image image = this.rendererCanvas.toImage();
//            this.imageView.setImage(image);
//        });
    }

}