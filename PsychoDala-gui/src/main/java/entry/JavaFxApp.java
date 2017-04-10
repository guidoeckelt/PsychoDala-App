package entry;

import app.Application;
import bootstrap.Bootstrapper;
import error.JavaFxErrorHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import main.MainViewController;

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/MainView.fxml"));
        loader.setController(new MainViewController(primaryStage, app));

        try {
            loader.load();
        } catch (Exception e) {
            this.handleFxmlLoadException(e);
        }
    }

    private void handleFxmlLoadException(Exception e) {
        String article = this.isFirstLetterVowel(e) ? "an" : "a";
        String headerText = "While bootstrapping " + article + " " + e.getClass().getSimpleName() + " occured";
        String contentText = "Message:\n" + e.getMessage();
        JavaFxErrorHandler.Instance().showException(e, headerText, contentText);
    }

    private boolean isFirstLetterVowel(Exception e) {
        String firstLetter = e.getClass().getSimpleName().substring(0, 1).toLowerCase();
        return firstLetter.matches("[AEIOUaeiou]");
    }
}
