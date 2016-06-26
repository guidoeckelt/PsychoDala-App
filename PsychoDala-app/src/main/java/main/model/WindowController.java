package main.model;

import entry.App;
import framework.IControllerBase;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Guido on 20.06.2016.
 */
public class WindowController
        extends IControllerBase{

    private Stage primaryStage;
    private boolean isPressedBefore;
    private Point2D currentMousePoint;
    private Point2D currentWindowPoint;
    private BooleanProperty maximizedOrdered;

//==============IControllerBase-Implementation
    protected void load() {
        primaryStage = App.PRIMARYSTAGE;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        isPressedBefore = false;
        maximizedOrdered = new SimpleBooleanProperty(false);
    }

    protected void cleanup() {

    }
//==============Window-Actions
    public boolean shutdownApplication(){
        Platform.exit();
        return true;
    }

    public boolean minimize(){
        this.primaryStage.toBack();//setIconified(!primaryStage.isIconified());
        return true;
    }

    public boolean switchWindowState(){
        currentWindowPoint = new Point2D(primaryStage.getX(),primaryStage.getY());
        this.primaryStage.setMaximized(!primaryStage.isMaximized());
        if(!primaryStage.isMaximized()){
            primaryStage.setX(currentWindowPoint.getX());
            primaryStage.setY(currentWindowPoint.getY());
        }
        return true;
    }
//========WindowTranslation
    private void moveWindow(MouseEvent me){
        Point2D newMousePoint = new Point2D(me.getScreenX(),me.getScreenY());
        Point2D diff = currentMousePoint.subtract(newMousePoint);
        currentMousePoint = newMousePoint;
        double x = primaryStage.getX()+diff.getX()*-1;
        double y = primaryStage.getY()+diff.getY()*-1;
        tranlsateWindow(new Point2D(x, y));
        maximizedOrdered.setValue(primaryStage.getY()<= 0);
        if(primaryStage.getY()<= 0){
            System.out.println("Preview switchWindowState");
        }else{
            System.out.println("No switchWindowState");
        }
    }

    private void tranlsateWindow(Point2D newPoint){
        primaryStage.setX(newPoint.getX());
        primaryStage.setY(newPoint.getY());
    }

    private void startDrag(MouseEvent me){
        currentMousePoint = new Point2D(me.getScreenX(),me.getScreenY());
    }

//==========MouseDragHandling
    public void onMouseDragged(MouseEvent me) {
        if(!isPressedBefore){
            startDrag(me);
            isPressedBefore = true;
        }else{
            moveWindow(me);
        }
    }

    public void onMouseReleased(MouseEvent me) {
        isPressedBefore = false;
        if(maximizedOrdered.get() && !primaryStage.isMaximized()){
            switchWindowState();
        }else if(!maximizedOrdered.get() && primaryStage.isMaximized()){
            switchWindowState();
        }
    }



}
