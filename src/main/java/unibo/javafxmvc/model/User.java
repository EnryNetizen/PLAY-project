package unibo.javafxmvc.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User {
    private String nome;
    private String cognome;
    private String username;
    private String password; // memorizzata come SHA-256 hash
    private byte[] avatar;
    private String color;   // sul mio sistema: bianco: 0xffffffff

    public User(String nome, String cognome, String username, String password, byte[] avatar, String color) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.color = color;
    }

    // equals() e hashCode() sono necessari per la comparazione degli oggetti (User)
    /** @return true <=> nome && cognome && username (!password) */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(nome, user.nome) &&
                Objects.equals(cognome, user.cognome) &&
                Objects.equals(username, user.username);
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
        } catch (NoSuchAlgorithmException e) { // TODO: gestione della generazione dell'eccezione
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, username, password);
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Boolean checkPassword(String input) {
        return this.password.equals(input);
    }
    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s%n", nome, cognome, username, password);
    }
}
