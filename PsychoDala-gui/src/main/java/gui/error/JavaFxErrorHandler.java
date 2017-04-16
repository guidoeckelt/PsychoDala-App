package gui.error;

import app.error.ErrorHandler;
import app.window.Window;
import gui.window.ExceptionPopUp;

/**
 * Created by Guido on 10.04.2017.
 */
public class JavaFxErrorHandler
        implements ErrorHandler {

    private static ErrorHandler INSTANCE;

    private JavaFxErrorHandler() {

    }

    public static ErrorHandler Instance() {
        if (INSTANCE == null) {
            INSTANCE = new JavaFxErrorHandler();
        }
        return INSTANCE;
    }

    @Override
    public void showException(Exception e) {
        Window window = new ExceptionPopUp(e);
        window.show();
    }

    @Override
    public void showException(Exception e, String contentText) {
        Window window = new ExceptionPopUp(e, contentText);
        window.show();
    }

    @Override
    public void showException(Exception e, String headerText, String contentText) {
        Window window = new ExceptionPopUp(e, headerText, contentText);
        window.show();
    }

}
