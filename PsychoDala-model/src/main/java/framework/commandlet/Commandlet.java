package framework.commandlet;

import framework.io.Shortcut;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Guido on 22.06.2016.
 */
public class Commandlet
        implements ICommandlet{

    private String name;
    private Shortcut shortcut;
    private EventType<KeyEvent> eventType;

    public void execute(){
        System.out.println("Default: "+name+" is executed");
    }

    public Shortcut getShortcut() {
        return shortcut;
    }
    public void setShortcut(Shortcut shortcut) {
        this.shortcut = shortcut;
    }

    public EventType<KeyEvent> getEventType() {
        return eventType;
    }
    public void setEventType(EventType<KeyEvent> eventType) {
        this.eventType = eventType;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


}
