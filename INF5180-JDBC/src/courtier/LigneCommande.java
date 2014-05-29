package courtier;

/**
 * Classe bean représentant la table LigneCommande.
 */

public class LigneCommande {

    private int noCommande;
    private int noArticle;
    private int qte;


    public LigneCommande(int noCommande, int noArticle, int qte) {
        this.noCommande = noCommande;
        this.noArticle = noArticle;
        this.qte = qte;
    }


    public int getNoCommande() {
        return noCommande;
    }


    public void setNoCommande(int noCommande) {
        this.noCommande = noCommande;
    }


    public int getNoArticle() {
        return noArticle;
    }


    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }


    public int getQte() {
        return qte;
    }


    public void setQte(int qte) {
        this.qte = qte;
    }
}
