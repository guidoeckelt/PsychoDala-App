package framework;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
@XmlRootElement(name="configuration")
public class CommandletFactory {

    private List<CommandletGroup> commandletGroups;

    public List<CommandletGroup> getCommandletGroups() {
        return commandletGroups;
    }

    @XmlElement(name="commandlets")
    public void setCommandletGroups(List<CommandletGroup> commandletGroups) {
        this.commandletGroups = commandletGroups;
    }

}
