package command;

/**
 * Created by Guido on 31.03.2017.
 */
public interface RecoverableCommand
    extends Command{

    void undo();
}
