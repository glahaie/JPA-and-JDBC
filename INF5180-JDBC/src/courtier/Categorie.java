package courtier;

public class Categorie {

    private int code;
    private int parent;
    private String libelle;

    public Categorie(int code, String libelle, int parent) {
        this.code = code;
        this.libelle = libelle;
        this.parent = parent;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    //A changer : On fait une requete pour trouver le parent
    public int getParent() {
        return parent;
    }
    public void setParent(int parent) {
        this.parent = parent;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
