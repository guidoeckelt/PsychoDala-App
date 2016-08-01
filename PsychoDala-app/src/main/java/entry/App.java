package entry;/**
 * Created by Guido on 20.06.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    public static Stage PRIMARYSTAGE;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.run();
        App.PRIMARYSTAGE = primaryStage;
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/MainView.fxml"));
        Parent node;
        try {
            node = loader.load();
            primaryStage.setScene(new Scene(node));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();



    }

    public static void exit(){

    }
}
