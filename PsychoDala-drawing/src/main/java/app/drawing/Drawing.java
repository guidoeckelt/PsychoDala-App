package app.drawing;

import app.drawing.structure.DrawingObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Guido on 06.07.2016.
 */
public class Drawing {

    private String name;
    private File correspondingFile;
    private List<DrawingObject> drawingObjects = new ArrayList<>();
//======CTOR
    public Drawing() {
        this("New Drawing", null);
    }

    public Drawing(File correspondingFile) {
        this(correspondingFile.getName(), correspondingFile);
    }

    public Drawing(String name) {
        this(name, null);
    }

    public Drawing(String name, File correspondingFile) {
        this.name = name;
        this.correspondingFile = correspondingFile;
        this.loadFile();

    }

    private void loadFile() {
        if (this.correspondingFile == null) {
            this.createTempFile();
            return;
        }


    }

    private void createTempFile() {
        try {
            this.correspondingFile = File.createTempFile(this.name, ".psy");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("SecurityException: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(this.correspondingFile.getAbsolutePath());
    }

    //======================Methods

    //==========================Getter&Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public File getCorrespondingFile() {
        return correspondingFile;
    }
    public void setCorrespondingFile(File correspondingFile) {
        this.correspondingFile = correspondingFile;
    }

    public List<DrawingObject> getDrawingObjects() {
        return Collections.unmodifiableList(this.drawingObjects);
    }

}
