package framework.commandlet;

import framework.application.ApplicationContext;
import framework.application.IApplicationContext;
import javafx.application.Application;

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
public class CommandletContext
        implements ICommandletContext{

    private IApplicationContext applicationContext;
    private List<CommandletGroup> commandletGroups;
    private List<CommandletShorcutHandler> commandletShorcutHandlers;

    private List<CommandletGroupDescriptor> commandletGroupDescriptors;
    private CommandletFactory commandletFactory;

    public CommandletContext(IApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public CommandletShorcutHandler getCommandletKeyHandlerFor(Commandlet expectedCommandlet) {
        for (CommandletShorcutHandler commandletShorcutHandler : commandletShorcutHandlers) {
            if(commandletShorcutHandler.getCommandlet().equals(expectedCommandlet)) {
                return commandletShorcutHandler;
            }
        }
        return null;
    }

    public List<CommandletGroup> getCommandletGroups() {
        return commandletGroups;
    }

    public void load() {
        loadDescriptors();
        loadCommandlets();
        createShortcutHandlers();
    }
    public void save() {
        File file = new File(applicationContext.getConfigDirectory()+"commandlets.config");
        CommandletConfiguration commandletConfiguration = new CommandletConfiguration();
        commandletConfiguration.setCommandletGroupDescriptors(commandletFactory.getCommandletGroupDescriptors());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletConfiguration.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(commandletConfiguration, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private void loadDescriptors(){
        File file = new File(applicationContext.getConfigDirectory()+"commandlets.config");
        CommandletConfiguration commandletConfiguration;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletConfiguration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            commandletConfiguration = (CommandletConfiguration) unmarshaller.unmarshal(file);
            commandletGroupDescriptors = commandletConfiguration.getCommandletGroups();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private void loadCommandlets(){
        commandletFactory = new CommandletFactory(commandletGroupDescriptors);
        commandletGroups = commandletFactory.buildCommandlets();
    }

    private void createShortcutHandlers(){
        commandletShorcutHandlers = new ArrayList<>();

        for(CommandletGroup commandletGroup : commandletGroups){
            for(Commandlet commandlet : commandletGroup.getCommandlets()){
                commandletShorcutHandlers.add(new CommandletShorcutHandler(commandlet, commandlet.getEventType()));
            }
        }
    }
}
