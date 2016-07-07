package display.controller;

import com.sun.org.apache.bcel.internal.generic.DREM;
import display.model.Renderer;
import drawing.Drawing;
import framework.controller.IControllerBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.StackPane;
import main.model.DrawingBoard;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 22.06.2016.
 */
public class DisplayViewController extends IControllerBase implements Initializable{

    @FXML
    StackPane canvasRoot;

    private Renderer renderer;
    private DrawingBoard drawingBoard;
    private Drawing loadedDrawing;

//==============IControllerBase-Implementation
    protected void load() {
    }

    protected void cleanup() {

    }

//=================FX-Controller
    public void initialize(URL location, ResourceBundle resources) {
        Dimension2D suitableSize = calculateSize();
        drawingBoard = new DrawingBoard(suitableSize);
        canvasRoot.getChildren().add(drawingBoard);
        System.out.println(canvasRoot.getChildren().size());
        renderer = new Renderer(drawingBoard);
        renderer.testStart();
    }

    private Dimension2D calculateSize() {
        Dimension2D size;
        double frame = 20;
//        if(canvasRoot.getPrefHeight() <600 || canvasRoot.getPrefWidth() < 800) {
//            size = new Dimension2D(canvasRoot.getPrefWidth()-frame, canvasRoot.getPrefHeight()-frame);
//            System.out.println(size);
//            return size;
//
//        }
        size = new Dimension2D(800-frame, 600-frame);
        System.out.println(size);
        return size;
    }

}
