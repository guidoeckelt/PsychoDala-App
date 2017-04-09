package main;

import app.Application;
import drawing.Drawing;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import render.DefaultRenderer;
import render.Renderer;
import render.RendererCanvas;

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
    @FXML
    BorderPane root;
    @FXML
    MenuBar menuBar;
    @FXML
    VBox renderCanvasContainer;
    RendererCanvas rendererCanvas;
    Canvas renderCanvas;
    @FXML
    MenuItem newDrawing;
    @FXML
    MenuItem newDrawingMenu;
    @FXML
    MenuItem saveDrawing;
    @FXML
    MenuItem saveAsDrawing;
    @FXML
    MenuItem openDrawing;
    @FXML
    Menu openRecent;
    @FXML
    MenuItem exportAsImage;
    @FXML
    MenuItem exit;
    @FXML
    MenuItem undo;
    @FXML
    MenuItem redo;
    @FXML
    MenuItem switchRenderState;
    @FXML
    MenuItem defaultB;
    @FXML
    MenuItem blackWhiteTiles;
    private Renderer renderer;

    public MainViewController(Stage primaryStage, Application app) {
        this.primaryStage = primaryStage;
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initiateWindow();
        this.createRenderCanvas();
        renderer = new DefaultRenderer(this.renderCanvas);
        this.addMenuHandler();
    }

    private void initiateWindow() {
        this.primaryStage.centerOnScreen();
        this.primaryStage.initStyle(StageStyle.DECORATED);
        this.primaryStage.setMinWidth(1200);
        this.primaryStage.setMinHeight(800);
        this.primaryStage.setTitle(app.getName());

        this.primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, this::OnCloseRequest);

        this.primaryStage.setScene(new Scene(this.root));
        this.primaryStage.show();
    }

    private void createRenderCanvas() {
        double width = 800;
        double height = 600;
        this.renderCanvas = new Canvas(width, height);
        this.renderCanvasContainer.getChildren().add(this.renderCanvas);

        this.rendererCanvas = new JavaFxCanvas(this.renderCanvasContainer, width, height);
    }

    private void addMenuHandler() {
        //File
        newDrawing.setOnAction(this::newDrawing);
        newDrawingMenu.setOnAction(this::newDrawingMenu);
        saveDrawing.setOnAction(this::saveDrawing);
        saveAsDrawing.setOnAction(this::saveAsDrawing);
        openDrawing.setOnAction(this::openDrawing);
        openRecent.setOnAction(this::openRecent);
        exportAsImage.setOnAction(this::exportAsImage);
        exit.setOnAction(this::exit);
        //Edit
        undo.setOnAction(this::undo);
        redo.setOnAction(this::redo);
        //View
        switchRenderState.setOnAction(this::switchRenderState);
        defaultB.setOnAction(this::defaultB);
        blackWhiteTiles.setOnAction(this::blackWhiteTiles);
    }

    private void OnCloseRequest(WindowEvent windowEvent) {
        this.renderer.stop();
    }

    private void updateWindowTitle(Drawing drawing) {
        String path = drawing.getCorrespondingFile().getAbsolutePath();
        String title = this.app.getName() + " - " + drawing.getName();
        this.primaryStage.setTitle(title);
    }

    private void newDrawing(ActionEvent actionEvent) {
        String name = "New Drawing";
        Drawing drawing = new Drawing();
        this.renderer.setDrawing(drawing);
        this.updateWindowTitle(drawing);
        this.renderer.start();
    }

    private void newDrawingMenu(ActionEvent actionEvent) {

    }

    private void saveDrawing(ActionEvent actionEvent) {

    }

    private void saveAsDrawing(ActionEvent actionEvent) {

    }

    private void openDrawing(ActionEvent actionEvent) {

    }

    private void openRecent(ActionEvent actionEvent) {

    }

    private void exportAsImage(ActionEvent actionEvent) {

    }

    private void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    private void undo(ActionEvent actionEvent) {

    }

    private void redo(ActionEvent actionEvent) {

    }

    private void switchRenderState(ActionEvent actionEvent) {
        if(this.renderer.isRunning()){
            this.renderer.stop();
        } else {
            this.renderer.start();
        }
        this.changeMenuItemTextAccordingly();
    }

    private void changeMenuItemTextAccordingly() {
        if (this.renderer.isRunning()) {
            this.switchRenderState.setText(this.STOP_TEXT);
        } else {
            this.switchRenderState.setText(this.START_TEXT);
        }
    }
    private void defaultB(ActionEvent actionEvent) {
        this.renderer.setBackground(DefaultRenderer.DEFAULT_BACKGROUND);
    }
    private void blackWhiteTiles(ActionEvent actionEvent) {
        this.renderer.setBackground(DefaultRenderer.BLACK_WHITE_TILES);
    }


}
