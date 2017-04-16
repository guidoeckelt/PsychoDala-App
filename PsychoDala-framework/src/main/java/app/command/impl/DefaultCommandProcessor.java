package app.command.impl;

import app.command.Command;
import app.command.CommandProcessor;
import app.command.RecoverableCommand;

import java.util.Stack;

/**
 * Created by Guido on 31.03.2017.
 */
public class DefaultCommandProcessor
        implements CommandProcessor {

    private Stack<RecoverableCommand> history = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        if(command instanceof RecoverableCommand)
            history.push(((RecoverableCommand) command));
    }

    public void undoLastCommand(){
        history.pop().undo();
    }

    public void undoCommand(){

    }
}
