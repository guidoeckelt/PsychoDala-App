package app.drawing.render.impl;

import java.util.TimerTask;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultRendererTimerTask
    extends TimerTask{

    private DefaultRenderer renderer;

    public DefaultRendererTimerTask(DefaultRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        this.renderer.render();
    }

}
