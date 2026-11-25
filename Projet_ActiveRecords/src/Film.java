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
