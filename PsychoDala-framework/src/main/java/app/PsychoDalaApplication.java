package app;

import app.command.CommandProcessor;
import app.command.DefaultCommandProcessor;

import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public class PsychoDalaApplication
    implements Application{

    private final List<Module> modules;
    private CommandProcessor commandProcessor;

    public PsychoDalaApplication(List<Module> modules) {
        this.modules = modules;
        this.commandProcessor = new DefaultCommandProcessor();
    }

    @Override
    public String getName() {
        return "PsychoDala";
    }

}
