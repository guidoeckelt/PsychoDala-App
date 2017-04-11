package main;

import app.Application;
import drawing.Drawing;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import render.Renderer;
import render.RendererCanvas;
import render.defauItt.DefaultRenderer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    HBox topContainer;
    @FXML
    VBox barContainer;
    @FXML
    HBox openDrawingsList;
    @FXML
    MenuBar menuBar;
    @FXML
    VBox toolsBarContainer;
    @FXML
    VBox rendererCanvasContainer;
    @FXML
    ImageView imageView;
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
    MenuItem defaultBackground;
    @FXML
    MenuItem blackWhiteTiles;
    @FXML
    ToggleButton pointTool;
    @FXML
    ToggleButton drawTool;
    private RendererCanvas rendererCanvas;
    private Renderer renderer;
    private List<Drawing> openDrawings;

    public MainViewController(Stage primaryStage, Application app) {
        this.primaryStage = primaryStage;
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initiateWindow();
        this.addMenuHandler();
        this.createRendererAndRendererCanvas();
        this.openDrawings = new ArrayList<>();

//        this.newDrawing();
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

    private void createRendererAndRendererCanvas() {
        double width = 800;
        double height = 600;
        this.rendererCanvas = new JavaFxCanvas(this.rendererCanvasContainer, width, height);
        this.renderer = new DefaultRenderer(this.rendererCanvas, imageView);
    }

    private void addMenuHandler() {
        //File
        newDrawing.setOnAction(event -> this.newDrawing());
        newDrawingMenu.setOnAction(event -> this.newDrawingMenu());
        saveDrawing.setOnAction(event -> this.saveDrawing());
        saveAsDrawing.setOnAction(event -> this.saveAsDrawing());
        openDrawing.setOnAction(event -> this.openDrawing());
        openRecent.setOnAction(event -> this.openRecent());
        exportAsImage.setOnAction(event -> this.exportAsImage());
        exit.setOnAction(event -> this.exit());
        //Edit
        undo.setOnAction(event -> this.undo());
        redo.setOnAction(event -> this.redo());
        //View
        switchRenderState.setOnAction(event -> this.switchRenderState());
        defaultBackground.setOnAction(event -> this.defaultBackground());
        blackWhiteTiles.setOnAction(event -> this.blackWhiteTiles());
    }

    private void OnCloseRequest(WindowEvent windowEvent) {
        this.renderer.stop();
    }

    private void updateWindowTitle(Drawing drawing) {
        File file = drawing.getCorrespondingFile();
        String path = file.getAbsolutePath();
        boolean isTempFile = file.getParentFile().getName().equals("Temp");
        String title = this.app.getName() + " - " + drawing.getName() + (isTempFile ? " - [" + path + "]" : "");
        this.primaryStage.setTitle(title);
    }

    private void newDrawing() {
        String name = "New Drawing" + (this.openDrawings.size() + 1);
        Drawing drawing = new Drawing(name);
        this.renderer.selectDrawing(drawing);

        if (!this.renderer.isRunning())
            this.renderer.start();

        this.addToOpenDrawings(drawing);
        this.updateWindowTitle(drawing);

    }

    private void addToOpenDrawings(Drawing drawing) {
        this.openDrawings.add(drawing);
        Node node = new Pane();
        this.openDrawingsList.getChildren().add(node);
    }

    private void newDrawingMenu() {

    }

    private void saveDrawing() {

    }

    private void saveAsDrawing() {

    }

    private void openDrawing() {

    }

    private void openRecent() {

    }

    private void exportAsImage() {

    }

    private void exit() {
        Platform.exit();
    }

    private void undo() {

    }

    private void redo() {

    }

    private void switchRenderState() {
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

    private void defaultBackground() {
        this.rendererCanvas.selectBackground(JavaFxCanvas.DEFAULT_BACKGROUND);
    }

    private void blackWhiteTiles() {
        this.rendererCanvas.selectBackground(JavaFxCanvas.BLACK_WHITE_TILES);
    }


}
