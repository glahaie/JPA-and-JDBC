package courtier;
import java.sql.*;

/**
 * Classe bean représentant la table Livraison.
 */


public class Livraison {

    private int noLivraison;
    private java.sql.Date dateLivraison;


    public Livraison(int noLivraison, Date dateLivraison) {
        super();
        this.noLivraison = noLivraison;
        this.dateLivraison = dateLivraison;
    }


    public int getNoLivraison() {
        return noLivraison;
    }


    public void setNoLivraison(int noLivraison) {
        this.noLivraison = noLivraison;
    }


    public java.sql.Date getDateLivraison() {
        return dateLivraison;
    }


    public void setDateLivraison(java.sql.Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }
}
