package main.model;

import entry.App;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Guido on 20.06.2016.
 */
public class WindowController {

    private Stage primaryStage;

    public WindowController() {
        primaryStage = App.PRIMARYSTAGE;
        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    public boolean minimize(){
        this.primaryStage.toBack();//setIconified(!primaryStage.isIconified());
        return true;
    }

    public boolean maximize(){
        this.primaryStage.setMaximized(!primaryStage.isMaximized());
        return true;
    }

    public boolean exitApp(){
        Platform.exit();
        return true;
    }
}
