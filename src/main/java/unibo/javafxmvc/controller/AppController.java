package unibo.javafxmvc.controller;

import javafx.application.Platform;
import unibo.javafxmvc.DAO.DatabaseManager;

import java.sql.SQLException;

public class AppController {
    public static void handleWindowClose() {
        try {
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}
