package framework.commandlet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
@XmlRootElement(name="commandlet-configuration")
public class CommandletConfiguration {

    private List<CommandletGroupDescriptor> commandletGroups = new ArrayList<>();

    @XmlElement(name="commandlet-group")
    public List<CommandletGroupDescriptor> getCommandletGroups() {
        return commandletGroups;
    }
    public void setCommandletGroupDescriptors(List<CommandletGroupDescriptor> commandletGroups) {
        this.commandletGroups = commandletGroups;
    }

}
