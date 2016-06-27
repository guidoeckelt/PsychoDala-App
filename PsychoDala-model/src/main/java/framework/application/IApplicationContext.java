package framework.application;

/**
 * Created by Guido on 25.06.2016.
 */
public interface IApplicationContext {

    void load();
    void save();

    String getApplicationDirectory();
    String getConfigDirectory();
}
