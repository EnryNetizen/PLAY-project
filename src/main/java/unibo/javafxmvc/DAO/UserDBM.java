package unibo.javafxmvc.DAO;

import unibo.javafxmvc.exception.ConnectionException;
import unibo.javafxmvc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBM extends DatabaseManager {
    /**@return true se la connessione è stata stabilita correttamente e l'inserimento è andato a termine; <br> false se l'inserimento non è andato a termine correttamente
     * @throws ConnectionException se la connessione non è stata stabilita correttamente
     * */
    public static Boolean insertUser(User usr) throws ConnectionException{
        if(connection != null){
            int affectedRows = 0;
            try(PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO \"User\" (NOME, COGNOME, USERNAME, PASSWORD, AVATAR, COLOR) VALUES (?, ?, ?, ?, ?, ?)")){
                pstmt.setString(1, usr.getNome());
                pstmt.setString(2, usr.getCognome());
                pstmt.setString(3, usr.getUsername());
                pstmt.setString(4, usr.getPassword());
                pstmt.setBytes(5, usr.getAvatar());
                pstmt.setString(6, usr.getColor());
                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                return false;
            }
        } else throw new ConnectionException(connectionExceptionMessage, new NullPointerException(npeMessage));
    }
    /**@param username username dell'utente da verificare
     * @return true se l'utente con lo username specificato è presente nel database; <br> false se l'utente non è presente
     * @throws ConnectionException se la connessione non è stata stabilita correttamente
     * @throws SQLException se si verifica un errore durante l'esecuzione della query di recupero
     * */
    public static Boolean userExists(String username) throws ConnectionException, SQLException {
        if(connection != null){
            try(PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT USERNAME FROM \"User\" WHERE LOWER(USERNAME) = LOWER(?)")) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                throw e;
            }
        } else throw new ConnectionException(connectionExceptionMessage, new NullPointerException(npeMessage));
    }
    /**@return true se la password dell'utente corrisponde a quella memorizzata nel database; <br> false non corrisponde
     * @throws ConnectionException se la connessione non è stata stabilita correttamente
     * @throws SQLException se si verifica un errore durante l'esecuzione della query di recupero o l'Utente non è stato trovato o il dato è corrotto
     * */
    public static Boolean checkPasswordForUser(String username, String password) throws ConnectionException, SQLException {
        if(connection != null){
            try(PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT PASSWORD FROM \"User\" WHERE LOWER(USERNAME) = LOWER(?)")) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("PASSWORD").equals(password);
                } else {
                    throw new SQLException("Utente non trovato");
                }
            } catch (SQLException e) {
                throw e;
            }
        } else throw new ConnectionException(connectionExceptionMessage, new NullPointerException(npeMessage));
    }
    /**@return l'utente con lo username specificato; <br> null se l'utente non è stato trovato
     * @throws ConnectionException se la connessione non è stata stabilita correttamente
     * @throws SQLException se si verifica un errore durante l'esecuzione della query di recupero
     * */
    public static User getUser(String username) throws ConnectionException, SQLException {
        if(connection != null) {
            try (PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT NOME, COGNOME, USERNAME, PASSWORD, AVATAR, COLOR FROM \"User\" WHERE LOWER(USERNAME) = LOWER(?)")) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new User(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBytes("avatar"),
                            rs.getString("color")
                    );
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        else throw new ConnectionException(connectionExceptionMessage, new NullPointerException(npeMessage));
    }
}
