package controller;

import app.Application;
import graphic.impl.BlackWhiteTilesBackground;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import render.Renderer;
import render.DefaultRenderer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 31.03.2017.
 */
public class MainViewController
    implements Initializable{

    private final String STOP_TEXT = "Stop Rendering";
    private final String START_TEXT = "Start Rendering";

    private final Application app;
    private final Stage primaryStage;
    private Renderer renderer;

    public MainViewController(Stage primaryStage, Application app) {
        this.primaryStage = primaryStage;
        this.app = app;
    }

    @FXML
    BorderPane root;
    @FXML
    MenuBar menuBar;
    @FXML
    Canvas drawingScene;

    @FXML
    MenuItem switchRenderState;
    @FXML
    MenuItem defaultB;
    @FXML
    MenuItem blackWhiteTiles;
    @FXML
    MenuItem newDrawing;
    @FXML
    MenuItem save;
    @FXML
    MenuItem openDrawing;
    @FXML
    MenuItem exportAs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initiateWindow();
        renderer = new DefaultRenderer(drawingScene);
        renderer.start();
        switchRenderState.setOnAction(this::switchRenderState);
        defaultB.setOnAction(this::defaultB);
        blackWhiteTiles.setOnAction(this::blackWhiteTiles);
        newDrawing.setOnAction(this::newDrawing);
        save.setOnAction(this::save);
        openDrawing.setOnAction(this::openDrawing);
        exportAs.setOnAction(this::exportAs);
    }

    private void switchRenderState(ActionEvent actionEvent) {
        if(this.renderer.isRunning()){
            this.renderer.stop();
            this.switchRenderState.setText(this.START_TEXT);
        }
        else {
            this.renderer.start();
            this.switchRenderState.setText(this.STOP_TEXT);
        }
    }
    private void defaultB(ActionEvent actionEvent) {
        this.renderer.setBackground(DefaultRenderer.DEFAULT_BACKGROUND);
    }
    private void blackWhiteTiles(ActionEvent actionEvent) {
        this.renderer.setBackground(DefaultRenderer.BLACK_WHITE_TILES);
    }

    private void newDrawing(ActionEvent actionEvent) {

    }
    private void save(ActionEvent actionEvent) {

    }
    private void openDrawing(ActionEvent actionEvent) {

    }
    private void exportAs(ActionEvent actionEvent) {

    }

    private void initiateWindow(){
        this.primaryStage.centerOnScreen();
        this.primaryStage.initStyle(StageStyle.DECORATED);
        this.primaryStage.setMinWidth(800);
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setTitle(app.getName());

        this.primaryStage.setScene(new Scene(this.root));
        this.primaryStage.show();
    }
}
