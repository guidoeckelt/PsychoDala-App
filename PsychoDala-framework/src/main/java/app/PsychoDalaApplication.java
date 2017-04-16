package app;

import app.command.Command;
import app.command.CommandProcessor;
import app.command.impl.DefaultCommandProcessor;

import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public class PsychoDalaApplication
    implements Application{

    private final List<Module> modules;
    private List<Command> allCommands;
    private CommandProcessor commandProcessor;

    public PsychoDalaApplication(List<Module> modules, List<Command> allCommands) {
        this.modules = modules;
        this.allCommands = allCommands;
        this.commandProcessor = new DefaultCommandProcessor();
    }

    @Override
    public String getName() {
        return "PsychoDala";
    }

    @Override
    public void cleanUp() {
        for (Module module : this.modules) {
            module.cleanUp();
        }
    }
}
