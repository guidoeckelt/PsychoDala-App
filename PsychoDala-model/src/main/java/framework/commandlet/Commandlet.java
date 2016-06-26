package framework.commandlet;

import framework.io.Shortcut;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Guido on 22.06.2016.
 */
public class Commandlet {


    private String name;
    private Shortcut shortcut;
    private EventType<KeyEvent> eventType;

    private String classStr;
    private String shortcutStr;
    private String eventTypeStr;

    public void execute(){
        System.out.println(name+" is executed");
    }

    private void updateShorcut(String shortcutStr){
        if(!shortcutStr.contains("+")){
            buildShortcut(shortcutStr);
        }else{
            String[] shortcutStrSplit = shortcutStr.split("\\+");//RegExp : escaped '+' sign to split by '+'
            buildShortcutWithSpecialKey(shortcutStrSplit[1].trim(),shortcutStrSplit[0].trim());//Format is : SpecialKey+Key -> 1 , 0
        }
    }
    private void buildShortcut(String key){
        shortcut = new Shortcut(KeyCode.getKeyCode(key));

    }
    private void buildShortcutWithSpecialKey(String keyStr, String specialKeyStr){
        KeyCode key = KeyCode.getKeyCode(keyStr);
        KeyCode specialKey = KeyCode.getKeyCode(specialKeyStr);
        shortcut = new Shortcut(key,specialKey);
    }

    private void updateEventType(String eventTypeStr){
        if(eventTypeStr.equals(KeyEvent.KEY_RELEASED.getName())){
            eventType = KeyEvent.KEY_RELEASED;
        }
        if(eventTypeStr.equals(KeyEvent.KEY_PRESSED.getName())){
            eventType = KeyEvent.KEY_PRESSED;
        }
        if(eventTypeStr.equals(KeyEvent.KEY_TYPED.getName())){
            eventType = KeyEvent.KEY_TYPED;
        }
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

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setShortcutStr(String shortcut) {
        this.shortcutStr = shortcut;
        updateShorcut(shortcut);
    }
    public String getShortcutStr() {
        return shortcutStr;
    }

    @XmlAttribute
    public void setEventTypeStr(String eventTypeStr) {
        this.eventTypeStr = eventTypeStr;
        updateEventType(eventTypeStr);
    }
    public String getEventTypeStr() {
        return eventTypeStr;
    }

    @XmlAttribute(name="class")
    public void setClassStr(String classStr) {
        this.classStr = classStr;
    }
    public String getClassStr() {
        return classStr;
    }

}
