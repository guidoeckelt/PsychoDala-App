package framework.application;

/**
 * Created by Guido on 25.06.2016.
 */
public class ApplicationContext implements IApplicationContext{

    private String applicationDirectory;
    private String configDirectory;
    private String iconsDirectory;

    public void load() {
        this.applicationDirectory = "";
        this.configDirectory = applicationDirectory+"conf\\";
        this.iconsDirectory = applicationDirectory+"icons\\";
    }

    public void save() {

    }

    public String getApplicationDirectory() {
        return applicationDirectory;
    }

    public String getConfigDirectory() {
        return configDirectory;
    }

    @Override
    public String getIconsDirectory() {
        return iconsDirectory;
    }
}
