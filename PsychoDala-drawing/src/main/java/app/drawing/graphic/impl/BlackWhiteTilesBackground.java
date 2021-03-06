package app.drawing.graphic.impl;

import app.drawing.graphic.Graphic;
import app.drawing.graphic.GraphicCanvas;
import javafx.scene.paint.Color;

/**
 * Created by Guido on 09.04.2017.
 */
public class BlackWhiteTilesBackground
        implements Graphic {

    private final Color black = Color.color(0.05,0.05,0.05,1);
    private final Color white = Color.color(0.95,0.95,0.95,1);


    @Override
    public void paint(GraphicCanvas gc) {
        Color currentColor = this.black;
        Color lastRowFirst = currentColor;
        double size = 20;
        for (int y = 0; y < gc.getHeight(); y += size) {
            for (int x = 0; x < gc.getWidth(); x += size) {
                gc.setFill(currentColor);
                gc.fillRect(x, y, size, size);
                currentColor = currentColor.equals(this.black)
                            ? this.white
                            : this.black;
            }
            currentColor = lastRowFirst.equals(currentColor)
                    ? this.otherColor(currentColor) : currentColor;
            lastRowFirst = currentColor;

        }
//        this.position = new Point2D(0,0);
    }

    private Color otherColor(Color currentColor){
        return currentColor.equals(this.black)? this.white: this.black;
    }

}
