package framework.commandlet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
public class CommandletGroup {

    private String name;
    private List<Commandlet> commandlets = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Commandlet> getCommandlets() {
        return commandlets;
    }
    public void setCommandlets(List<Commandlet> commandlets) {
        this.commandlets = commandlets;
    }

}
