package courtier;


/**
 * Classe bean représentant la table Article.
 */

public class Article {

    private int noArticle;
    private String description;
    private int prixUnitaire;
    private String image;
    private String URL;
    private int qteEnStock;
    private Categorie categorie;


    public Article(int noArticle, String description, int prixUnitaire,
                   String image, String URL, int qteEnStock, Categorie categorie) {
        this.noArticle = noArticle;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.image = image;
        this.URL = URL;
        this.qteEnStock = qteEnStock;
        this.categorie = categorie;
    }

    public Article(int noArticle, String description, int prixUnitaire,
                   String image, String uRL, int qteEnStock) {
        this.noArticle = noArticle;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.image = image;
        URL = uRL;
        this.qteEnStock = qteEnStock;
    }


    public int getNoArticle() {
        return noArticle;
    }
    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String uRL) {
        URL = uRL;
    }
    public int getQteEnStock() {
        return qteEnStock;
    }
    public void setQteEnStock(int qteEnStock) {
        this.qteEnStock = qteEnStock;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


}
