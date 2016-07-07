package display.model;

import main.model.DrawingBoard;

/**
 * Created by Guido on 07.07.2016.
 */
public class Renderer {

    private DrawingBoard drawingBoard;
    private int askedFPS;

    public Renderer(DrawingBoard drawingBoard) {
        this.drawingBoard = drawingBoard;
    }

    public void render(int fps){
        askedFPS = fps;

        drawingBoard.drawBackground();
    }

    public void testStart(){
        drawingBoard.drawBackground();
    }

}
