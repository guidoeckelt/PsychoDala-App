package framework;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Guido on 22.06.2016.
 */
@XmlRootElement(name = "Commandlet")
public class Commandlet {


    private String name;
    private String shortcut;

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    public String getShortcut() {
        return shortcut;
    }

}
