
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //Instance unique du Singleton
    private static DBConnection instance;

    //Connexion à la base de données
    private Connection connection;

    //Paramètres de connexion
    private String userName = "root";
    private String password = "";
    private String serverName = "127.0.0.1";
    private String portNumber = "3306";
    private String nomDB = "testpersonne";

    //Création d'un constructeur privée
    private DBConnection() {

    }

    //Retourne l'unique instance du Singleton
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    //Retourne la connexion JDBC
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + nomDB;
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }

    //Change la bd utilisé
    public void setNomDB(String nomDB) throws SQLException {
        //Ferme l'ancienne connexion si elle est ouverte
        if(connection != null && !connection.isClosed()){
            connection.close();
        }

        //Enregistre le nouveau nom de la base de donnée
        this.nomDB = nomDB;

        //On force la création d'une nouvelle connexion pour actualiser
        connection = null;
    }
}
