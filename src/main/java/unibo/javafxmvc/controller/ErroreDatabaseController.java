package unibo.javafxmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import unibo.javafxmvc.Main;

public class ErroreDatabaseController implements Initializable {
    @FXML
    private Label lblDatabase;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblDatabase.setText("Percorso indicato per il database: \n" + Main.getDbURLlikeAbsolutePath());
    }
}
