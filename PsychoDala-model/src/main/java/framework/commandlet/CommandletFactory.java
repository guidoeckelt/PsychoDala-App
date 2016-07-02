package framework.commandlet;

import framework.io.Shortcut;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 02.07.2016.
 */
public class CommandletFactory {


    private List< CommandletGroupDescriptor> commandletGroupDescriptors;

    public CommandletFactory(List<CommandletGroupDescriptor> commandletGroupDescriptors) {
        this.commandletGroupDescriptors = commandletGroupDescriptors;
    }

    public List<CommandletGroupDescriptor> getCommandletGroupDescriptors() {
        return commandletGroupDescriptors;
    }

    public List<CommandletGroup> buildCommandlets() {
        List<CommandletGroup> commandletGroups = new ArrayList<>();
        for(CommandletGroupDescriptor commandletGroupDescriptor : commandletGroupDescriptors){
            CommandletGroup commandletGroup = new CommandletGroup();
            commandletGroup.setName(commandletGroupDescriptor.getName());

            for(CommandletDescriptor commandletDescriptor : commandletGroupDescriptor.getCommandletDescriptors()){
                Commandlet commandlet = buildCommandlet(commandletDescriptor);
                commandletGroup.getCommandlets().add(commandlet);
            }

            commandletGroups.add(commandletGroup);
        }
        return commandletGroups;
    }

    private Commandlet buildCommandlet(CommandletDescriptor commandletDescriptor) {
        Commandlet commandlet = null;
        try {
            commandlet = (Commandlet)Class.forName(commandletDescriptor.getClassStr()).getConstructor().newInstance();
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
        if (commandlet != null) {
            commandlet.setName(commandletDescriptor.getName());
            commandlet.setShortcut(buildShortcut(commandletDescriptor.getShortcut()));
            commandlet.setEventType(buildEventType(commandletDescriptor.getEventType()));
        }
        return commandlet;
    }

    private Shortcut buildShortcut(String shortcutStr){
        if(!shortcutStr.contains("+")){
             return new Shortcut(KeyCode.getKeyCode(shortcutStr));
        }else{
            String[] shortcutStrSplit = shortcutStr.split("\\+");//RegExp has to escape '+' sign -> split('\\+')
            String keyStr = shortcutStrSplit[1].trim();
            String specialKeyStr = shortcutStrSplit[0].trim();
            KeyCode key = KeyCode.getKeyCode(keyStr);
            KeyCode specialKey = KeyCode.getKeyCode(specialKeyStr);
            return new Shortcut(key,specialKey);
        }
    }

    private EventType<KeyEvent> buildEventType(String eventTypeStr) {
        if(eventTypeStr.equals(KeyEvent.KEY_RELEASED.getName())){
           return KeyEvent.KEY_RELEASED;
        }
        if(eventTypeStr.equals(KeyEvent.KEY_PRESSED.getName())){
            return KeyEvent.KEY_PRESSED;
        }
        return KeyEvent.KEY_TYPED;
    }
}
