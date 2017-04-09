package entry;

import app.Application;
import bootstrap.Bootstrapper;
import controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by Guido on 20.06.2016.
 */
public class JavaFxApp
        extends javafx.application.Application {

    public static void main(String[] args) {
        JavaFxApp.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Bootstrapper bootstrapper = new JavaFxBootstrapper(this.getParameters());
        Application app = bootstrapper.run();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        loader.setController(new MainViewController(primaryStage, app));

        try {
            loader.load();
        } catch (Exception e) {
            new JavaFxBootstrapExceptionHandler(e);
        }
    }
}
