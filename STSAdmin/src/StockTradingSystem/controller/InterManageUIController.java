package StockTradingSystem.controller;

import StockTradingSystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InterManageUIController  implements Initializable {
    private Main application;
    public void setApp(Main app) {
        this.application = app;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void personalInfo(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void modifyPassword(ActionEvent actionEvent) {
    }
}
