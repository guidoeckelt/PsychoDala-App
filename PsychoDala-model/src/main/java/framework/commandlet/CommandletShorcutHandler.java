package framework.commandlet;


import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by Guido on 25.06.2016.
 */
public class CommandletShorcutHandler {

    private Commandlet commandlet;
    private EventType<KeyEvent> keyEventType;

    public CommandletShorcutHandler(Commandlet commandlet, EventType<KeyEvent> keyEventType) {
        this.commandlet = commandlet;
        this.keyEventType = keyEventType;

    }

    public void OnKeyInput(KeyEvent keyEvent){
        if(commandlet.getShortcut().hasSpecialKey()){
            if (keyEvent.getCode() == commandlet.getShortcut().getKey()) {
                if(isSpecialKeyDown(keyEvent)){
                    commandlet.execute();
                }
            }
        }else{
            if(keyEvent.getCode() == commandlet.getShortcut().getKey()){
                commandlet.execute();
            }
        }
    }

    private boolean isSpecialKeyDown(KeyEvent keyEvent){
        if(commandlet.getShortcut().getSpecialKey().equals(KeyCode.CONTROL) && keyEvent.isControlDown()){
            return true;
        }
        if(commandlet.getShortcut().getSpecialKey().equals(KeyCode.SHIFT) && keyEvent.isShiftDown()){
            return true;
        }
        if(commandlet.getShortcut().getSpecialKey().equals(KeyCode.ALT) && keyEvent.isAltDown()){
            return true;
        }
        return false;
    }


    public Commandlet getCommandlet() {
        return commandlet;
    }

    public EventType<KeyEvent> getKeyEventType() {
        return keyEventType;
    }
}
