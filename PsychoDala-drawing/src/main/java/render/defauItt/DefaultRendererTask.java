package render.defauItt;

import java.util.TimerTask;

/**
 * Created by Guido on 09.04.2017.
 */
public class DefaultRendererTask
    extends TimerTask{

    private DefaultRenderer renderer;

    public DefaultRendererTask(DefaultRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        this.renderer.render();
    }

}
