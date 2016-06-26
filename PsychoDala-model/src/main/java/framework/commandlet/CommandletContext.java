package framework.commandlet;

import framework.application.IApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
        loadFromConfig();
        loadExplicitCommandlets();
        createShortcutHandlers();
    }
    public void save() {
        File file = new File(applicationContext.getConfigDirectory()+"commandlets.config");
        CommandletConfiguration commandletConfiguration = new CommandletConfiguration();
        commandletConfiguration.setCommandletGroups(commandletGroups);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletConfiguration.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(commandletConfiguration, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private void loadFromConfig(){
        File file = new File(applicationContext.getConfigDirectory()+"commandlets.config");
        CommandletConfiguration commandletConfiguration;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandletConfiguration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            commandletConfiguration = (CommandletConfiguration) unmarshaller.unmarshal(file);
            commandletGroups = commandletConfiguration.getCommandletGroups();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void loadExplicitCommandlets(){
        for (CommandletGroup commandletGroup : commandletGroups){
            int size = commandletGroup.getCommandlets().size();
            for(int i = 0; i < size;i++){
                Commandlet commandlet = commandletGroup.getCommandlets().get(i);
                System.out.println(commandlet.getClassStr());
                Commandlet explicitCommandlet;
                try {
                    explicitCommandlet = (Commandlet)Class.forName(commandlet.getClassStr()).getConstructor().newInstance();
                    explicitCommandlet.setEventTypeStr(commandlet.getEventTypeStr());
                    explicitCommandlet.setShortcutStr(commandlet.getShortcutStr());
                    explicitCommandlet.setName(commandlet.getName());
                    commandletGroup.replace(explicitCommandlet);
                } catch (InstantiationException instantEx) {
                    System.out.println("InstantiationException : "+instantEx.getMessage());
                } catch (IllegalAccessException illegalAccessEx) {
                    System.out.println("IllegalAccessException : "+illegalAccessEx.getMessage());
                } catch (InvocationTargetException invocTargetEx) {
                    System.out.println("InvocationTargetException : "+invocTargetEx.getMessage());
                } catch (NoSuchMethodException noSuchMethodEx) {
                    System.out.println("NoSuchMethodException : "+noSuchMethodEx.getMessage());
                } catch (ClassNotFoundException classNotFoundEx) {
                    System.out.println("ClassNotFoundException : "+classNotFoundEx.getMessage());
                }
            }
        }
    }

    private void createShortcutHandlers(){
        commandletShorcutHandlers = new ArrayList<>();

        for(CommandletGroup commandletGroup : commandletGroups){
            for(Commandlet commandlet : commandletGroup.getCommandlets()){
                commandletShorcutHandlers.add(new CommandletShorcutHandler(commandlet, commandlet.getEventType()));
            }
        }
    }

    private void dummydata(){
        commandletGroups = new ArrayList<>();

        CommandletGroup commandletGroup = new CommandletGroup();
        commandletGroup.setName("File");
        List<Commandlet> commandlets = new ArrayList<>();
        Commandlet command = new Commandlet();
        command.setName("Save As");
        command.setShortcutStr("STRG+S");
        commandlets.add(command);
        commandletGroup.setCommandlets(commandlets);
        commandletGroups.add(commandletGroup);

        commandletGroup = new CommandletGroup();
        commandletGroup.setName("Edit");
        commandlets = new ArrayList<>();
        command = new Commandlet();
        command.setName("Undo");
        command.setShortcutStr("STRG+Z");
        commandlets.add(command);
        command = new Commandlet();
        command.setName("Redo");
        command.setShortcutStr("STRG+Y");
        commandlets.add(command);
        commandletGroup.setCommandlets(commandlets);
        commandletGroups.add(commandletGroup);
    }
}
