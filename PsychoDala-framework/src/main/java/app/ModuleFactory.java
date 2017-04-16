package app;

import app.error.DefaultException;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Guido on 14.04.2017.
 */
public class ModuleFactory {

    public Module createModule(Class<? extends Module> theClass) throws DefaultException {
        try {
            return theClass.getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new DefaultException("Instantiation failed");
        } catch (IllegalAccessException e) {
            throw new DefaultException("Constructor could not be accessed");
        } catch (InvocationTargetException e) {
            throw new DefaultException("Constructor call failed");
        } catch (NoSuchMethodException e) {
            throw new DefaultException("No such constructor");
        }
    }
}
