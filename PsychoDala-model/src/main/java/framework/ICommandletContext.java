package framework;

import java.io.File;
import java.util.List;

/**
 * Created by Guido on 22.06.2016.
 */
public interface ICommandletContext {

    void save();
    void load();

    List<CommandletGroup> getCommandletGroups();
    File getFile();

}
