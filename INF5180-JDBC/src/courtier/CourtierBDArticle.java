package courtier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
Patron courtier pour l'accès aux articles.
 */

class CourtierBDArticle {

    private Connection connection;

    public CourtierBDArticle(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Article chercherArticleParNoArticle(int noArticle)
            throws Exception {

        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Article WHERE noArticle = ? ");
        statement.setInt(1, noArticle);

        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            Article article = new Article(noArticle,
                    rs.getString("description"),
                    rs.getInt("prixUnitaire"),
                    rs.getString("image"),
                    rs.getString("URL"),
                    rs.getInt("quantiteEnStock"));

            statement.close();
            return article;
        } else {
            throw new Exception("Aucun article trouve");
        }
    }
}