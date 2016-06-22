package main.controller;

import framework.Commandlet;
import framework.CommandletContext;
import framework.CommandletGroup;
import framework.ICommandletContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.model.WindowController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Guido on 20.06.2016.
 */
public class MainViewController implements Initializable{

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

    private WindowController windowController;
    private CommandletContext commandletContext;

    public MainViewController() {
        windowController = new WindowController();
        commandletContext = new CommandletContext("D:\\");
        commandletContext.load();
    }

    public void initialize(URL location, ResourceBundle resources) {
        btnMinimize.setOnAction(event -> windowController.minimize());
        btnSwitchMaximize.setOnAction(event -> windowController.maximize());
        btnExit.setOnAction(event -> windowController.exitApp());

        loadMenus();
    }

    private void loadMenus(){
        for(CommandletGroup commandletGroup : commandletContext.getCommandletGroups()){
            Menu menu = new Menu();
            menu.setText(commandletGroup.getName());
            for(Commandlet commandlet : commandletGroup.getCommandlets()){
                MenuItem menuItem = new MenuItem();
                menuItem.setText(commandlet.getName());
                menu.getItems().add(menuItem);
            }
            this.menu.getMenus().add(menu);
        }
    }
}
