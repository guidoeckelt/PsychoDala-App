package framework.commandlet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
@XmlRootElement(name="commandlet-configuration")
public class CommandletConfiguration {

    private List<CommandletGroup> commandletGroups;

    public List<CommandletGroup> getCommandletGroups() {
        return commandletGroups;
    }

    @XmlElement(name="commandlet-group")
    public void setCommandletGroups(List<CommandletGroup> commandletGroups) {
        this.commandletGroups = commandletGroups;
    }

}
