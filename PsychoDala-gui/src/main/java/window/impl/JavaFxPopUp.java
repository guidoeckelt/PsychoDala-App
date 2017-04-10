package window.impl;

import javafx.scene.control.Alert;
import window.PopUp;
import window.PopUpType;

/**
 * Created by Guido on 10.04.2017.
 */
public class JavaFxPopUp
        implements PopUp {

    private final String defaultTitleText = "PopUp";
    private final String defaultHeaderText = "Ein Neues PopUp";
    private final String defaultContentText = "Irgendwie ist es zu einen neuem PopUp gekommen";
    protected String titleText;
    protected String headerText;
    protected String contentText;
    private Alert alert;
    private PopUpType popUpEnum;

    public JavaFxPopUp(String contentText) {
        this(PopUpType.INFORMATION, null, contentText);
        this.defaultHeader();
    }

    public JavaFxPopUp(String headerText, String contentText) {
        this(PopUpType.INFORMATION, headerText, contentText);
    }

    public JavaFxPopUp(PopUpType popUpType, String headerText, String contentText) {
        this.popUpEnum = popUpType;
        this.defaultTitleText();
        this.headerText = headerText;
        this.contentText = contentText;
    }

    public void show() {
        this.alert = new Alert(this.mapToAlertType(this.popUpEnum));
        this.alert.setTitle(this.titleText);
        this.alert.setHeaderText(this.headerText);
        this.alert.setContentText(this.contentText);
        this.alert.show();
    }

    public void setType(PopUpType popUpType) {
        alert.setAlertType(this.mapToAlertType(popUpType));
    }

    protected void defaultTitleText() {
        this.titleText = this.defaultTitleText;
    }

    protected void defaultHeader() {
        this.headerText = this.defaultHeaderText;
    }

    protected void defaultContentText() {
        this.contentText = this.defaultContentText;
    }

    protected Alert.AlertType mapToAlertType(PopUpType popUpType) {
        switch (popUpType) {
            case INFORMATION:
                return Alert.AlertType.INFORMATION;
            case CONFIRMATION:
                return Alert.AlertType.CONFIRMATION;
            case ERROR:
                return Alert.AlertType.ERROR;
            default:
                return Alert.AlertType.INFORMATION;
        }
    }

}
