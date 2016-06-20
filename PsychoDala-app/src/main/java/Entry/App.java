package entry;/**
 * Created by Guido on 20.06.2016.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.run();


        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
