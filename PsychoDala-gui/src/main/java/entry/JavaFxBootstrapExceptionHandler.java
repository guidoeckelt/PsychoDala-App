package entry;

import javafx.scene.control.Alert;

/**
 * Created by Guido on 08.04.2017.
 */
public class JavaFxBootstrapExceptionHandler {

    private String header;
    private String contentText;

    public JavaFxBootstrapExceptionHandler(Exception e) {
        this.createHeaderAndContentText(e);
        this.createAndShowAlert();
    }

    private void createHeaderAndContentText(Exception e){
        String article = this.isFirstLetterVowel(e) ? "an" : "a";
        this.header = "While bootstrapping "+article+" "+e.getClass().getSimpleName()+" occured";
        this.contentText = "Message:\n"+e.getMessage();
    }
    private void createAndShowAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(this.header);
        alert.setContentText(this.contentText);
        alert.show();
    }

    private boolean isFirstLetterVowel(Exception e) {
        String firstLetter = e.getClass().getSimpleName().substring(0,1).toLowerCase();
        return firstLetter.matches("[AEIOUaeiou]");
    }
}
