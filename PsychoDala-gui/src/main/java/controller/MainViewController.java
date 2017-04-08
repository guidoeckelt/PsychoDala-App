package controller;

import app.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import render.Renderer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 31.03.2017.
 */
public class MainViewController
    implements Initializable{

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
    Canvas drawingScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initiateWindow();
        renderer = new Renderer(drawingScene);
        renderer.start();
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
