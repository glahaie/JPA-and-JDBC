package courtier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by guillaume on 26/04/14.
 *
 * Courtier gérant l'accès aux livraisons.
 */
class CourtierBDLivraison {

    private Connection connection;

    public CourtierBDLivraison(Connection connection) {
        this.connection = connection;
    }


    public int chercherArticlesLivresParCommandeEtNoArticle(int noCommande, int noArticle)  throws Exception {
        PreparedStatement statement = connection.prepareStatement
                ("SELECT sum(quantiteLivree) as total FROM detailLivraison WHERE noArticle = ? AND noCommande = ?");
        statement.setInt(1, noArticle);
        statement.setInt(2, noCommande);

        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            int total = rs.getInt("total");

            connection.commit();
            statement.close();
            return total;
        } else {
            throw new Exception("Aucun article trouve");
        }
    }
}
