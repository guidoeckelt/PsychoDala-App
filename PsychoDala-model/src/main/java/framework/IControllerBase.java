package framework;

/**
 * Created by Guido on 25.06.2016.
 */
public abstract class IControllerBase {

    protected IControllerBase() {
        load();
    }

    protected abstract void load();

    protected abstract void cleanup();
}
