package bootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public class CommandLineArgumentParser {

    private List<CommandLineArgument> arguments = new ArrayList<>();

    public CommandLineArgumentParser(String[] args) {
        this.parseArguments(args);
    }

    private void parseArguments(String[] args){

    }

    public List<CommandLineArgument> parse() {
        return arguments;
    }

}
