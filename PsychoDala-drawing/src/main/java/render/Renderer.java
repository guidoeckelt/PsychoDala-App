package render;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Guido on 01.04.2017.
 */
public class Renderer
    implements Runnable{
    private final Color defaultBackgroundColor = Color.color(0.3,0.3,0.3,1);

    private Thread renderProcess;
    private Canvas canvas;
    private GraphicsContext gc;

    public Renderer(Canvas c) {
        this.canvas = c;
        this.gc = c.getGraphicsContext2D();
    }

    public void start() {
        this.renderProcess = new Thread(this);
        this.renderProcess.start();
    }

    @Override
    public void run() {
        this.draw(SpecialDrawing.BACKGROUND);

        this.draw(SpecialDrawing.POINTER);
    }

    private void draw(SpecialDrawing type) {
        switch(type){
            case BACKGROUND: this.background(); break;
            case POINTER: this.pointer(); break;
        }
    }

    private void background(){
        this.gc.setFill(this.defaultBackgroundColor);
        double width = this.canvas.getWidth();
        double height = this.canvas.getHeight();
        gc.fillRect(0,0, width,height);
    }
    private void pointer() {

    }


}
enum SpecialDrawing{
    BACKGROUND,
    POINTER
}
