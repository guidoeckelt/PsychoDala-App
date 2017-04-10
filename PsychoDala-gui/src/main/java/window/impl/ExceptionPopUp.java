package window.impl;

import window.PopUpType;

/**
 * Created by Guido on 10.04.2017.
 */
public class ExceptionPopUp
        extends JavaFxPopUp {

    private Exception e;

    public ExceptionPopUp(Exception e) {
        this(e, null, null);
        this.createTextsFromException();
    }

    public ExceptionPopUp(Exception e, String contentText) {
        this(e, null, contentText);
        this.header();
    }

    public ExceptionPopUp(String headerText, String contentText) {
        this(null, headerText, contentText);
    }

    public ExceptionPopUp(Exception e, String headerText, String contentText) {
        super(PopUpType.ERROR, headerText, contentText);
        this.e = e;
        this.headerText = headerText;
        this.contentText = contentText;
    }

    private void createTextsFromException() {
        this.header();
        this.content();
    }

    private void header() {
        String name = this.e.getClass().getSimpleName();
        String article = this.isFirstLetterVowel(name) ? "an" : "a";
        this.headerText = "" + article + " " + name + " occured";

    }

    private void content() {
        this.contentText = "Message:\n" + e.getMessage();
    }

    private boolean isFirstLetterVowel(String word) {
        String firstLetter = word.substring(0, 1).toLowerCase();
        return firstLetter.matches("[AEIOUaeiou]");
    }

    public boolean hasException() {
        return this.e != null;
    }

    public Exception getE() {
        return e;
    }

}
