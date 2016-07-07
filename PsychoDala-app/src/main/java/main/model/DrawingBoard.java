package main.model;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

/**
 * Created by Guido on 06.07.2016.
 */
public class DrawingBoard
        extends Canvas{

    private GraphicsContext context;
    private double border = 2;

    public DrawingBoard(Dimension2D suitableWidth) {
        super(suitableWidth.getWidth(),suitableWidth.getHeight());
        this.context = this.getGraphicsContext2D();
    }


    public void drawBackground(){
        context.setStroke(Color.BLACK);
        context.setLineWidth(border);
        context.strokeRect(0,0, this.getWidth(), this.getHeight());
        Dimension2D bounds = new Dimension2D(this.getWidth()-border*2,this.getHeight()-border*2);
        double size = this.getWidth()/15;

        Paint currentCoor = Color.WHITE;
        Paint rowstart = currentCoor;
        for (double y = 0; y < bounds.getHeight();y+=size){
            for (double x = 0; x < bounds.getWidth();x+=size){

                context.setFill(currentCoor);
                context.fillRect(x,y,size,size);
                if(currentCoor == Color.WHITE){
                    currentCoor = Color.BLACK;
                }else{
                    currentCoor = Color.WHITE;
                }
            }
            if(rowstart == currentCoor){
                if(currentCoor == Color.WHITE){
                    currentCoor = Color.BLACK;
                }else{
                    currentCoor = Color.WHITE;
                }
            }
            rowstart = currentCoor;
        }
    }
}
