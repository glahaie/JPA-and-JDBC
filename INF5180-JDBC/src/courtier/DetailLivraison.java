package courtier;

/**
 * Classe bean représentant la table DetailLivraison.
 */

public class DetailLivraison {

    private Livraison livraison;
    private Article article;
    private Commande commande;
    private int qteLivree;

    public DetailLivraison(Livraison livraison, Article article,
                           Commande commande, int qteLivree) {
        super();
        this.livraison = livraison;
        this.article = article;
        this.commande = commande;
        this.qteLivree = qteLivree;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getQteLivree() {
        return qteLivree;
    }

    public void setQteLivree(int qteLivree) {
        this.qteLivree = qteLivree;
    }

}
