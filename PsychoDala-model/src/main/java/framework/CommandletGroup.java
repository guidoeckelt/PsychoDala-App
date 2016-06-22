package framework;

import org.omg.CORBA.COMM_FAILURE;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
@XmlRootElement(name="commandlets")
public class CommandletGroup {

    private String name;
    private List<Commandlet> commandlets;

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public List<Commandlet> getCommandlets() {
        return commandlets;
    }
    @XmlElement(name="commandlet")
    public void setCommandlets(List<Commandlet> commandlets) {
        this.commandlets = commandlets;
    }
}
