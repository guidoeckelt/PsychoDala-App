package entry;

import app.Application;
import app.Module;
import app.PsychoDalaApplication;
import bootstrap.Bootstrapper;
import bootstrap.CommandLineArgument;
import bootstrap.CommandLineArgumentParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public class JavaFxBootstrapper
    implements Bootstrapper {

    private String[] args;
    private List<CommandLineArgument> arguments;

    private javafx.application.Application.Parameters parameters;
    private List<Module> modules = new ArrayList<>();


    public JavaFxBootstrapper(javafx.application.Application.Parameters parameters) {
        this.parameters = parameters;
    }

    public Application run(){
//        this.parseArguments();
        this.loadModules();
        return new PsychoDalaApplication(this.modules);
    }

    private void parseArguments(){
        CommandLineArgumentParser argumentParser = new CommandLineArgumentParser(this.args);
        this.arguments = argumentParser.parse();

    }
    private void loadModules(){

    }
}
