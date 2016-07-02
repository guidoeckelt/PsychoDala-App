package framework.commandlet;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Guido on 02.07.2016.
 */
public class CommandletDescriptor {

    private String name;
    private String shortcut;
    private String eventType;
    private String classStr;

    @XmlAttribute(name="name")
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @XmlAttribute(name="shortcut")
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    public String getShortcut() {
        return shortcut;
    }

    @XmlAttribute(name="eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getEventType() {
        return eventType;
    }

    @XmlAttribute(name="class")
    public void setClass(String classStr) {
        this.classStr = classStr;
    }
    public String getClassStr() {
        return classStr;
    }
}
