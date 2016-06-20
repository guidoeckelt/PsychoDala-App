package entry;/**
 * Created by Guido on 20.06.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.run();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/MainView.fxml"));
        Parent root;
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
