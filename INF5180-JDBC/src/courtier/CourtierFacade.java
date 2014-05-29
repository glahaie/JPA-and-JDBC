package courtier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by guillaume on 26/04/14.
 *
 * Patron facade pour matérialiser / dématérialiser les données.
 */
public class CourtierFacade {

    private Connection connection;
    private CourtierBDCommande courtCom;
    private CourtierBDClient courtClient;
    private CourtierBDArticle courtArticle;
    private CourtierBDLivraison courtLiv;

    public CourtierFacade() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE",
                "guillaume", "inf5180");

        connection.setAutoCommit(false);
        courtCom = new CourtierBDCommande(connection);
        courtClient = new CourtierBDClient(connection);
        courtArticle = new CourtierBDArticle(connection);
        courtLiv = new CourtierBDLivraison(connection);
    }


    public Client chercherClientParNo(int noClient, String motPasse) throws Exception {
        return courtClient.chercherClientParNoClient(noClient, motPasse);
    }

    public Commande chercherCommandeParNo(int noCommande, int noClient) throws Exception {
        return courtCom.chercherCommandeParNoCommande(noCommande, noClient);
    }

    public List<Commande> chercherCommandeParDate(String date, int noClient) throws Exception {
        return courtCom.chercherCommandeParDate(date, noClient);
    }

    public List<LigneCommande> chercherLignesCommandeParNo(int noCommande) throws Exception {
        return courtCom.chercherLignesCommande(noCommande);
    }

    public Article chercherArticleParNo(int noArticle) throws Exception {
        return courtArticle.chercherArticleParNoArticle(noArticle);
    }

    public int chercherTotalLivrePourCommandeArticle(int noArticle, int noCommande) throws Exception {
        return courtLiv.chercherArticlesLivresParCommandeEtNoArticle(noCommande, noArticle);
    }

    public void sauverCommande(Commande c, List<LigneCommande> l) throws Exception {
        courtCom.insererCommande(c,l);
    }

    public int chercherMaximumNoCommande() throws Exception {
        return courtCom.chercherMaximumNo();
    }


}
