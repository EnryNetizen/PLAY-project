package unibo.javafxmvc.controller;

import javafx.scene.image.Image;
import com.gluonhq.charm.glisten.control.Avatar;
import javafx.scene.paint.Color;
import unibo.javafxmvc.Main;

public class AuxiliaryController {
    public static void setImageTo(Avatar avt, Image img){
        avt.setImage(img);
    }
    public static void setAvatarTo(Avatar avt){
        avt.setImage(new Image(Main.class.getResourceAsStream("/unibo/javafxmvc/Images/avatar.png")));
    }
    public static String toRgbString(Color color) {
        return "rgb(" + (int) (color.getRed() * 255) + "," + (int) (color.getGreen() * 255) + "," + (int) (color.getBlue() * 255) + ")";
    }
}
