package error;

/**
 * Created by Guido on 10.04.2017.
 */
public interface ErrorHandler {

    void showException(Exception e);

    void showException(Exception e, String contentText);

    void showException(Exception e, String headerText, String contentText);

    void showInfo(String headerText, String contentText);

    void showConfirmation(String headerText, String contentText);
}
