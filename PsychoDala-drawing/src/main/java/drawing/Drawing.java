package drawing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guido on 06.07.2016.
 */
public class Drawing {

    private List<DrawingObject> drawingObjects = new ArrayList<>();
    private File correspondingFile;
//======CTOR
    public Drawing() {
    }

    public Drawing(File correspondingFile) {
        this.correspondingFile = correspondingFile;
    }
//======================Methods
    public void translate(){

    }

    public List<DrawingObject> getDrawingObjects() {
        return drawingObjects;
    }

    public void setDrawingObjects(List<DrawingObject> drawingObjects) {
        this.drawingObjects = drawingObjects;
    }

    public File getCorrespondingFile() {
        return correspondingFile;
    }

    public void setCorrespondingFile(File correspondingFile) {
        this.correspondingFile = correspondingFile;
    }
}
