package app.command;

/**
 * Created by Guido on 31.03.2017.
 */
public interface CommandProcessor {

    void executeCommand(Command command);

    void undoLastCommand();

    void undoCommand();
}
