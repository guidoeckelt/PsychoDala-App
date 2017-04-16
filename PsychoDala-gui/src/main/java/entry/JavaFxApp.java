package entry;

import app.Application;
import app.bootstrap.Bootstrapper;
import gui.error.JavaFxErrorHandler;
import gui.main.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by Guido on 20.06.2016.
 */
public class JavaFxApp
        extends javafx.application.Application {

    private Application app;

    public static void main(String[] args) {
        JavaFxApp.launch(JavaFxApp.class, args);
    }

    @Override
    public void init() throws Exception {
        Bootstrapper bootstrapper = new JavaFxBootstrapper(this.getParameters());
        app = bootstrapper.run();
    }

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/MainView.fxml"));
        loader.setController(new MainViewController(primaryStage, app));

        try {
            loader.load();
        } catch (Exception e) {
            this.handleFxmlLoadException(e);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();

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
