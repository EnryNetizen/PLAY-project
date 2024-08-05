module unibo.javafxmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.util;

    opens unibo.javafxmvc to javafx.fxml;
    opens unibo.javafxmvc.model to com.google.gson; //  gson accede agli attributi privati dei model

    opens unibo.javafxmvc.controller to javafx.fxml;
    opens unibo.javafxmvc.DAO to com.google.gson;

    exports unibo.javafxmvc.exception;
    exports unibo.javafxmvc.model;
    exports unibo.javafxmvc;
    exports unibo.javafxmvc.controller;
    exports unibo.javafxmvc.DAO;
}