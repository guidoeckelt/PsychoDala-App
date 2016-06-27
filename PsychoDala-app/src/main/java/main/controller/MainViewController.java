package main.controller;

import entry.App;
import framework.*;
import framework.application.ApplicationContext;
import framework.application.IApplicationContext;
import framework.commandlet.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.model.WindowController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 20.06.2016.
 */
public class MainViewController
        extends IControllerBase
        implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    MenuBar menu;
    @FXML
    HBox windowButtonBox;
    @FXML
    Button btnMinimize;
    @FXML
    Button btnSwitchMaximize;
    @FXML
    Button btnExit;

    private IApplicationContext applicationContext;
    private ICommandletContext commandletContext;
    private WindowController windowController;

//==============IControllerBase-Implementation
    protected void load() {
        applicationContext = new ApplicationContext();
        applicationContext.load();
        commandletContext = new CommandletContext(applicationContext);
        commandletContext.load();
        windowController = new WindowController();
    }

    protected void cleanup() {
        applicationContext.save();
        commandletContext.save();
    }
//=================FX-Controller
    public void initialize(URL location, ResourceBundle resources) {
        btnMinimize.setOnAction(event -> windowController.minimize());
        btnSwitchMaximize.setOnAction(event -> windowController.switchWindowState());
        btnExit.setOnAction(event -> windowController.shutdownApplication());
        menu.addEventHandler(MouseEvent.MOUSE_DRAGGED,windowController::onMouseDragged);
        menu.addEventHandler(MouseEvent.MOUSE_RELEASED,windowController::onMouseReleased);
        loadMenus();
    }

//Methods
    private void loadMenus(){
        for(CommandletGroup commandletGroup : commandletContext.getCommandletGroups()){
            Menu menu = new Menu();
            menu.setText(commandletGroup.getName());
            for(Commandlet commandlet : commandletGroup.getCommandlets()){
                //
                CommandletShorcutHandler commandletShorcutHandler = commandletContext.getCommandletKeyHandlerFor(commandlet);
                App.PRIMARYSTAGE.addEventHandler(commandletShorcutHandler.getKeyEventType(), commandletShorcutHandler::OnKeyInput);
                MenuItem menuItem = new MenuItem();
                menu.setOnAction(action-> commandlet.execute());
                menuItem.setText(commandlet.getName()+" ("+commandlet.getShortcutStr()+")");
                menu.getItems().add(menuItem);
            }
            this.menu.getMenus().add(menu);
        }
    }
}
