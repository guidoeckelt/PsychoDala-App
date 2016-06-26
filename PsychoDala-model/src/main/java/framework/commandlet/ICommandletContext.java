package framework.commandlet;

import java.io.File;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
public interface ICommandletContext {

//====Commandlet-Handling
    List<CommandletGroup> getCommandletGroups();
    CommandletShorcutHandler getCommandletKeyHandlerFor(Commandlet expectedCommandlet);
//====Config
    void load();
    void save();

}
