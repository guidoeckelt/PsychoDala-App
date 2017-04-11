package render;

import drawing.Drawing;
import graphic.Graphic;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * Created by Guido on 10.04.2017.
 */
public interface RendererCanvas {

    void clear();

    void paint(SpecialGraphicEnum specialGraphicEnum);

    void paintDrawing(Drawing drawing);

    Node toNode();

    Image toImage();

    Graphic getSelectedBackground();

    void selectBackground(Graphic background);

}
