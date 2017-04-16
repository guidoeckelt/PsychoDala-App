package entry;

import app.Application;
import app.Module;
import app.ModuleFactory;
import app.PsychoDalaApplication;
import app.bootstrap.Bootstrapper;
import app.bootstrap.CommandLineArgument;
import app.bootstrap.CommandLineArgumentParser;
import app.command.Command;
import app.drawing.DrawingModule;
import app.error.DefaultException;
import gui.error.JavaFxErrorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public class JavaFxBootstrapper
    implements Bootstrapper {

    private String[] args;
    private javafx.application.Application.Parameters parameters;
    private List<CommandLineArgument> arguments;
    private ModuleFactory moduleFactory = new ModuleFactory();
    private List<Command> allCommands = new ArrayList<>();
    private List<Module> allModules = new ArrayList<>();

    public JavaFxBootstrapper(javafx.application.Application.Parameters parameters) {
        this.parameters = parameters;

    }

    public JavaFxBootstrapper(String[] args) {
        this.args = args;

    }

    public Application run(){
//        this.parseArguments();
        this.loadModules();
        return new PsychoDalaApplication(this.allModules, this.allCommands);
    }

    private void parseArguments(){
        CommandLineArgumentParser argumentParser = new CommandLineArgumentParser(this.args);
        this.arguments = argumentParser.parse();

    }
    private void loadModules(){
        Module module = null;
        try {
            module = moduleFactory.createModule(DrawingModule.class);
            this.allCommands.addAll(module.registerCommands());
        } catch (DefaultException e) {
            JavaFxErrorHandler.Instance().showException(e);
        }
        this.allModules.add(module);
    }

}
