package app.drawing.render;

import app.drawing.Drawing;
import app.drawing.graphic.Graphic;
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

    Graphic getSelectedPointer();

    void selectPointer(Graphic pointer);


}
