package framework.commandlet;

import java.util.List;

/**
 * Created by Guido on 04.07.2016.
 */
public interface ICommandletFactory {
    List<CommandletGroup> buildCommandlets();
}
