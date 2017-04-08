package command;

import java.util.Stack;

/**
 * Created by Guido on 31.03.2017.
 */
public class StandardCommandProcessor
    implements CommandProcessor{

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
