package framework.commandlet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 02.07.2016.
 */
public class CommandletGroupDescriptor {

    private String name;
    private List<CommandletDescriptor> commandletDescriptors = new ArrayList<>();

    @XmlAttribute(name="name")
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @XmlElement(name="commandlet")
    public List<CommandletDescriptor> getCommandletDescriptors() {
        return commandletDescriptors;
    }
    public void setCommandletDescriptors(List<CommandletDescriptor> commandletDescriptors) {
        this.commandletDescriptors = commandletDescriptors;
    }
}
