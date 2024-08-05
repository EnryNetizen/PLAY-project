package unibo.javafxmvc.controller;

import unibo.javafxmvc.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Circle circleAvatar;
    @FXML
    private Label lblUsername;
    @FXML
    private GridPane mainGridPane;
    @FXML
    private ImageView userAvatar;
    @FXML
    private Circle circle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        byte[] avatar = Main.currentUser.getAvatar();
        try{
            if(avatar == null){
                avatar = Files.readAllBytes((new File(getClass().getResource("/unibo/javafxmvc/Images/avatar.png").getPath())).toPath());
            }
            userAvatar.setImage(new Image(new ByteArrayInputStream(avatar)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        circle = new Circle(userAvatar.getFitWidth() / 2, userAvatar.getFitHeight() / 2, Math.min(userAvatar.getFitWidth(), userAvatar.getFitHeight()) / 2);
        circle.setStroke(Color.web(Main.currentUser.getColor()));
        circle.setStrokeWidth(0);
        userAvatar.setClip(circle);
        drawBorders(Color.web(Main.currentUser.getColor()));
        lblUsername.setText(Main.currentUser.getUsername());
    }
    private void drawBorders(Color borderColor){
        circleAvatar.setStroke(borderColor);
        lblUsername.setTextFill(borderColor);
        Line line = new Line(0, 0, mainGridPane.getWidth(), 0);
        line.setStroke(borderColor);
        line.setStrokeWidth(2);

        Pane linePane = new Pane();
        linePane.getChildren().add(line);
        linePane.setPrefSize(mainGridPane.getWidth(), line.getStrokeWidth());
        mainGridPane.add(linePane, 0, 1);
    }

}