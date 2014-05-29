package courtier;


/**
 * Classe bean représentant la table Commande.
 */

public class Commande {


    private int noCommande;
    private String dateCommande;
    private String statut;
    private int noClient;
    public Commande(int noCommande, String dateCommande, String statut,
                    int noClient) {
        this.noCommande = noCommande;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.noClient = noClient;
    }

    public Commande(int noCommande, String dateCommande, int noClient) {
        this.noCommande = noCommande;
        this.dateCommande = dateCommande;
        this.noClient = noClient;
    }

    public int getNoCommande() {
        return noCommande;
    }

    public void setNoCommande(int noCommande) {
        this.noCommande = noCommande;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNoClient() {
        return noClient;
    }

    public void setNoClient(int noClient) {
        this.noClient = noClient;
    }


}
