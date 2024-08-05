package unibo.javafxmvc.DAO;

import java.sql.*;
import unibo.javafxmvc.exception.ConnectionException;
import unibo.javafxmvc.model.User;
/* Da un'analisi del log di refresh del database è risultato che:
    Inizio introspezione: 20:12:27
    Esecuzione della prima query: 20:12:28
    Tempo di esecuzione della query: 18 ms
    Tempo di recupero dei dati: 47 ms
    Connessione al database stabilita: 20:12:28

    ⚠️ per resettare gli id della tabella user: ALTER TABLE "User" ALTER COLUMN ID RESTART WITH 1;
 */
public class DatabaseManager {
    protected static String dbURL;
    protected static Connection connection;
    protected static final String connectionExceptionMessage;
    protected static final String npeMessage;

    static{
        connection = null;
        connectionExceptionMessage = "Errore di connessione al database: connessione nulla. \n DatabaseManager.inizialize() non è stato eseguito o è stato invocato un metodo senza prevedere la connessione";
        npeMessage = "Connection point to null";
    }
    /**inizializza la connessione al database con l'URL specificato
     * @param dbURL URL del database
     * @throws ConnectionException se la connessione non è stata stabilita correttamente
     * */
    public static void inizialize(String dbURL) throws ConnectionException {
        DatabaseManager.dbURL = dbURL;
        try {
            connection = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            throw new ConnectionException("Errore di connessione al database: " + e.getMessage(), e);
        }
        informStatus(Status.STABILITA);
    }
    public static void informStatus(Status st){
        System.out.println(String.format("Connessione al database %-10s con successo", st));
    }
    /**Chiude la connessione al database
     * @throws SQLException se si verifica un errore durante la chiusura della connessione
     * @see DatabaseManager#inizialize(String)
     * */
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                informStatus(Status.CHIUSA);
            } catch (SQLException e) {
                System.out.println("Fallimento durante la chiusura della connessione");
                throw e;
            }
        }
    }
}
