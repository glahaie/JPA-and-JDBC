package courtier;


/**
 * Classe bean représentant la table Client.
 */

public class Client {

    private int noClient;



    private String nom;
    private String prenom;
    private String adresseLivraison;
    private String dateInscription;
    private String heureInscription;
    private String courriel;
    private String motDePasse;

    public Client(int noClient, String nom, String prenom,
                  String adresseLivraison, String dateInscription,
                  String heureInscription, String courriel, String motDePasse) {
        this.noClient = noClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseLivraison = adresseLivraison;
        this.dateInscription = dateInscription;
        this.heureInscription = heureInscription;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
    }

    public int getNoClient() {
        return noClient;
    }


    public void setNoClient(int noClient) {
        this.noClient = noClient;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getAdresseLivraison() {
        return adresseLivraison;
    }


    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }


    public String getDateInscription() {
        return dateInscription;
    }


    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }


    public String getHeureInscription() {
        return heureInscription;
    }


    public void setHeureInscription(String heureInscription) {
        this.heureInscription = heureInscription;
    }


    public String getCourriel() {
        return courriel;
    }


    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }


    public String getMotDePasse() {
        return motDePasse;
    }


    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}
