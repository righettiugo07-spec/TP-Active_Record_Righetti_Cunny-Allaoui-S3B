import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Active Record de la table Film.
 * La colonne de la cle etrangere s'appelle id_rea (cf. testpersonne.sql),
 * l'attribut Java s'appelle id_real (cf. enonce).
 */
public class Film {

    private String titre;
    private int id;
    private int id_real;

    /**
     * 9.1 construit un film qui n'est pas encore dans la table
     *
     * @param titre       titre du film
     * @param realisateur realisateur (son id est recopie dans id_real)
     */
    public Film(String titre, Personne realisateur) {
        this.titre = titre;
        this.id = -1;
        this.id_real = realisateur.getId();
    }

    /**
     * 9.2 constructeur prive, utilise pour creer un film a partir
     * du resultat d'une requete
     */
    private Film(int id, int id_real, String titre) {
        this.id = id;
        this.id_real = id_real;
        this.titre = titre;
    }

    // ========================= RECHERCHES =========================

    /**
     * 11.1 retourne le film d'id donne
     *
     * @param id id cherche
     * @return le film, ou null s'il n'existe pas
     * @throws SQLException erreur SQL
     */
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

    /**
     * 11.2 retourne le realisateur du film (via l'active record Personne)
     *
     * @return le realisateur, ou null s'il n'existe pas dans la table
     * @throws SQLException erreur SQL
     */
    public Personne getRealisateur() throws SQLException {
        return Personne.findById(this.id_real);
    }

    // ========================= GETTERS / SETTERS =========================

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
