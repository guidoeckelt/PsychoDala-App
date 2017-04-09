package graphic;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Created by Guido on 09.04.2017.
 */
public class GraphicImage {

    private Image image;
    private Point2D position;

    public GraphicImage(Image image, Point2D position) {
        this.image = image;
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public Point2D getPosition() {
        return position;
    }

}
