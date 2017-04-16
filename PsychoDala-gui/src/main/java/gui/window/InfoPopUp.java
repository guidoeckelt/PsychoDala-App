package gui.window;

import app.window.PopUpType;

/**
 * Created by Guido on 10.04.2017.
 */
public class InfoPopUp
        extends JavaFxPopUp {

    public InfoPopUp(String headerText, String contentText) {
        super(PopUpType.INFORMATION, headerText, contentText);
    }

    public InfoPopUp(String titleText, String headerText, String contentText) {
        super(PopUpType.INFORMATION, headerText, contentText);
        this.titleText = titleText;
    }

}
