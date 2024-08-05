package unibo.javafxmvc.model;

public class FormValidator {
    public static Boolean validateName(String name) {
        return name.trim().matches("^[a-zA-Z]+( [a-zA-Z]+)*$");
    }
    public static Boolean validateUsername(String username) {
        return username.trim().matches("^[a-zA-Z0-9]{3,}$");
    }
    public static Boolean validatePassword(String password) {
        return password.matches("^[^\\s]{4,}$");
    }
}