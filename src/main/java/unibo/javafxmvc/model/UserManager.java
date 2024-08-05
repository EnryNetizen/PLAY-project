/*
 package unibo.javafxmvc.model;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> users;
    private static String userHome;
    private static File file;

    public static void initialize(String fileName) {
        /*userHome = System.getProperty("user.home"); // Fallback

        File configDir = new File(userHome + "/Desktop/", "PlayProj");
        if (!configDir.exists()) configDir.mkdirs(); /

import unibo.javafxmvc.model.JsonSerializer;
import unibo.javafxmvc.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

file = new File(fileName);
loadUsers();
    }

/**
 * @return true se l'utente è stato aggiunto correttamente
 * @return false se l'utente esiste già oppure il file json risulta
 *         inaccessibile
 /
public static boolean addUser(User user) {
    if (!checkUser(user)) {
        return appendUserToFile(user);
    }
    return false;
}

/**
 * @param username dell'utente in UserManager.users
 * @return User se l'utente è presente
 * @return null se l'utente non è presente
 /
public static User findUserByUsername(String username) {
    username = username.toLowerCase().trim();
    for (User user : users) {
        if (user.getUsername().toLowerCase().equals(username)) {
            return user;
        }
    }
    return null;
}

public static boolean checkUser(User user) {
    return users.stream().anyMatch(u -> u.equals(user));
}

public static Boolean loadUsers() {
    UserManager.users = (ArrayList<User>) JsonSerializer.deserializeJsonFile(UserManager.file, User.class);
    return (UserManager.users != null);
}

public static Boolean serializeUsers() {
    return JsonSerializer.serializeToJson(UserManager.users, UserManager.file);
}

public static boolean appendUserToFile(User user) {
    loadUsers();
    UserManager.users.add(user);
    return serializeUsers();
}

public static String getSHA256Hash(String input) {
    try {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256"); // link alla documentazione:
        // https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html#getInstance-java.lang.String-
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256.digest(input.getBytes())) { // SHA256: digest(input[byte[UTF-8]]) => byte[]
            sb.append(String.format("%02x", b)); // %02x: formatta il byte b in hex a due cifre
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) { 
        throw new RuntimeException("SHA-256 algorithm not found", e);
    }
}
}

        */