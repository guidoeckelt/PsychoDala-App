package app;

import app.command.Command;

import java.util.List;

/**
 * Created by Guido on 31.03.2017.
 */
public interface Module {

    String getName();

    List<Command> registerCommands();


    void cleanUp();

}
