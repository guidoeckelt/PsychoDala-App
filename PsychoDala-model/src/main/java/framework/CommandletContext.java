package framework;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
public class CommandletContext implements ICommandletContext{

    private File file;
    private List<CommandletGroup> commandletGroups;

    public CommandletContext(String filePath) {
        this.file = new File(filePath+"commandlets.config");
    }

    private void dummydata(){
        commandletGroups = new ArrayList<>();

        CommandletGroup commandletGroup = new CommandletGroup();
        commandletGroup.setName("File");
        List<Commandlet> commandlets = new ArrayList<>();
        Commandlet command = new Commandlet();
        command.setName("Save As");
        command.setShortcut("STRG+S");
        commandlets.add(command);
        commandletGroup.setCommandlets(commandlets);
        commandletGroups.add(commandletGroup);

        commandletGroup = new CommandletGroup();
        commandletGroup.setName("Edit");
        commandlets = new ArrayList<>();
        command = new Commandlet();
        command.setName("Undo");
        command.setShortcut("STRG+Z");
        commandlets.add(command);
        command = new Commandlet();
        command.setName("Redo");
        command.setShortcut("STRG+Y");
        commandlets.add(command);
        commandletGroup.setCommandlets(commandlets);
        commandletGroups.add(commandletGroup);
    }

    public void save() {
        CommandletFactory commandletFactory = new CommandletFactory();
        commandletFactory.setCommandletGroups(commandletGroups);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(commandletFactory, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void load() {
        CommandletFactory commandletFactory;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            commandletFactory = (CommandletFactory) unmarshaller.unmarshal(file);
            commandletGroups = commandletFactory.getCommandletGroups();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public List<CommandletGroup> getCommandletGroups() {
        return commandletGroups;
    }
}
