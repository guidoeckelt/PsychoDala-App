package framework.commandlet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
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

    public void replace(Commandlet commandlet){
        int size = commandlets.size();
        for(int i = 0 ; i < size;i++){
            Commandlet oldCommandlet = commandlets.get(i);
            if(commandlet.getName().equalsIgnoreCase(oldCommandlet.getName())){
                commandlets.add(i, commandlet);
                commandlets.remove(oldCommandlet);
            }
        }
    }
}
