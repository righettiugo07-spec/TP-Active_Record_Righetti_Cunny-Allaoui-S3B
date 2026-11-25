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
