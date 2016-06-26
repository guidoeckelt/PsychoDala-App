package framework.application;

/**
 * Created by Guido on 25.06.2016.
 */
public class ApplicationContext implements IApplicationContext{

    private String applicationDirectory;
    private String configDirectory;

    public ApplicationContext() {
        this.applicationDirectory = "";
        this.configDirectory = applicationDirectory+"conf\\";
    }

    public String getApplicationDirectory() {
        return applicationDirectory;
    }

    public String getConfigDirectory() {
        return configDirectory;
    }
}
