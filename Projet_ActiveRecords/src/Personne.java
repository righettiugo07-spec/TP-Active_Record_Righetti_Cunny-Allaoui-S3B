import java.sql.*;
import java.util.ArrayList;

public class Personne {

    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = -1;
    }

    private Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId(){
        return this.id;
    }

    public static Personne findById(int id) throws SQLException {
        Connection connect = DBConnection.getConnection();
        String sql = "SELECT * FROM personne WHERE id = ?;";
        PreparedStatement prep = connect.prepareStatement(sql);
        prep.setInt(1, id);

        ResultSet rs = prep.executeQuery();
        Personne p = null;

        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            p = new Personne(id, nom, prenom);
        }
        return p;
    }

    public static ArrayList<Personne> findByName(String nom) throws SQLException {
        Connection connect = DBConnection.getConnection();
        String sql = "SELECT * FROM personne WHERE nom = ?;";
        PreparedStatement prep = connect.prepareStatement(sql);
        prep.setString(1, nom);

        ResultSet rs = prep.executeQuery();
        ArrayList<Personne> lp = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String prenom = rs.getString("prenom");
            lp.add(new Personne(id, nom, prenom));
        }
        return lp;
    }

    public static ArrayList<Personne> findAll() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String sql = "SELECT * FROM personne;";
        PreparedStatement prep = connect.prepareStatement(sql);

        ResultSet rs = prep.executeQuery();
        ArrayList<Personne> lp = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            lp.add(new Personne(id, nom, prenom));
        }
        return lp;
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String createString = "CREATE TABLE personne ("
                + "id INTEGER AUTO_INCREMENT, "
                + "nom VARCHAR(40) NOT NULL, "
                + "prenom VARCHAR(40) NOT NULL, "
                + "PRIMARY KEY (id))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String deleteString = "DROP TABLE personne;";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(deleteString);
    }


    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
}
