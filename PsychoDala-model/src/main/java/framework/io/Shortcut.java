package framework.io;

import javafx.scene.input.KeyCode;

/**
 * Created by Guido on 25.06.2016.
 */
public class Shortcut {

    private KeyCode key;
    private KeyCode specialKey;

    private boolean hasSpecialKey;

    public Shortcut(KeyCode key) {
        this.key = key;
        this.specialKey = null;
        this.hasSpecialKey = false;
    }

    public Shortcut(KeyCode key, KeyCode specialKey) {
        this.specialKey = specialKey;
        this.key = key;
        this.hasSpecialKey = true;
    }

    public KeyCode getKey() {
        return key;
    }

    public KeyCode getSpecialKey() {
        return specialKey;
    }

    public boolean hasSpecialKey() {
        return hasSpecialKey;
    }
}
