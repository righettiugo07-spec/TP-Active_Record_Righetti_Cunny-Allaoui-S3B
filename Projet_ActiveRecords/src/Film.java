import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Film {

    private String titre;
    private int id;
    private int id_real;

    public Film(String titre, Personne realisateur) {
        this.titre = titre;
        this.id = -1;
        this.id_real = realisateur.getId();
    }

    private Film(int id, int id_real, String titre) {
        this.id = id;
        this.id_real = id_real;
        this.titre = titre;
    }

    public static Film findById(int id) throws SQLException {
        Connection connect = DBConnection.getConnection();
        PreparedStatement prep = connect.prepareStatement("SELECT * FROM Film WHERE id=?;");
        prep.setInt(1, id);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        Film res = null;
        if (rs.next()) {
            res = new Film(rs.getInt("id"), rs.getInt("id_rea"), rs.getString("titre"));
        }
        return res;
    }

    public Personne getRealisateur() throws SQLException {
        return Personne.findById(this.id_real);
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        String createString = "CREATE TABLE Film ( "
                + "id INTEGER AUTO_INCREMENT, "
                + "titre varchar(40) NOT NULL, "
                + "id_rea INTEGER DEFAULT NULL, "
                + "PRIMARY KEY (id), "
                + "FOREIGN KEY (id_rea) REFERENCES Personne(id))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
    }

    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getConnection();
        Statement stmt = connect.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS Film");
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", titre=" + titre + ", id_real=" + id_real + "]";
    }
}
