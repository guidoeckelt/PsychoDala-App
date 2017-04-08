package app;

import command.CommandProcessor;
import command.StandardCommandProcessor;

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
        this.commandProcessor = new StandardCommandProcessor();
    }

    @Override
    public String getName() {
        return "PsychoDala";
    }

}
