package app.drawing;

import app.Module;
import app.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 14.04.2017.
 */
public class DrawingModule
        implements Module {


    @Override
    public String getName() {
        return "DrawingModule";
    }

    @Override
    public List<Command> registerCommands() {
        List<Command> commands = new ArrayList<>();
        return commands;
    }

    @Override
    public void cleanUp() {

    }

}
