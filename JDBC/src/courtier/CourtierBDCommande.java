package courtier;

import java.sql.*;
import java.util.*;

/*
  Courtier pour l'accès aux commandes et aux lignes de commandes.
 */

class CourtierBDCommande {

    private Connection connection;
    private PreparedStatement statement;

    public CourtierBDCommande(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Commande chercherCommandeParNoCommande(int noCommande, int noClient)
            throws Exception {

        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Commande WHERE noCommande = ?  AND noClient = ?");
        statement.setInt(1, noCommande);
        statement.setInt(2, noClient);

        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            Commande commande = new Commande(noCommande,
                    rs.getString("dateCommande"),
                    rs.getString("statutCommande"),
                    noClient);

            connection.commit();
            statement.close();
            return commande;
        } else {
            connection.rollback();
            throw new Exception("Aucune commande trouvee.");
        }
    }

    public List<Commande> chercherCommandeParDate(String date, int noClient)
            throws Exception {

        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Commande WHERE dateCommande = ?  AND noClient = ?");
        statement.setString(1, date);
        statement.setInt(2, noClient);

        ResultSet rs = statement.executeQuery();

        if(rs.isBeforeFirst()) {
            List<Commande> l = new ArrayList<Commande>();
            while(rs.next()) {
                Commande c = new Commande(rs.getInt("noCommande"),
                        rs.getString("dateCommande"),
                        rs.getString("statutCommande"),
                        noClient);
                l.add(c);
            }

            connection.commit();
            statement.close();
            return l;
        } else {
            throw new Exception("Aucune commande trouvee.");
        }
    }

    public List<LigneCommande> chercherLignesCommande(int noCommande) throws Exception {
        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM LigneCommande WHERE noCommande = ? ");
        statement.setInt(1, noCommande);

        ResultSet rs = statement.executeQuery();
        if(rs.isBeforeFirst()) {
            List<LigneCommande> l = new ArrayList<LigneCommande>();
            while(rs.next()) {
                LigneCommande c = new LigneCommande(noCommande,
                        rs.getInt("noArticle"),
                        rs.getInt("quantite"));
                l.add(c);
            }
            connection.commit();
            statement.close();
            return l;
        } else {
            throw new Exception("Aucune commande trouvee.");
        }
    }

    //TODO: gérer les transactions
    public void insererCommande(Commande c, List<LigneCommande> l) throws Exception {

        try {
            c.setStatut("confirme");

            //On insere
           statement = connection.prepareStatement("INSERT INTO Commande VALUES(?,?,?,?)");
            statement.setInt(1, c.getNoCommande());
            statement.setString(2, c.getDateCommande());
            statement.setString(3, c.getStatut());
            statement.setInt(4, c.getNoClient());

            int resultat = statement.executeUpdate();

            //On insere les lignes
            statement = connection.prepareStatement("INSERT INTO LigneCommande VALUES(?,?,?)");
            for(LigneCommande ligne : l) {
                statement.setInt(1, ligne.getNoCommande());
                statement.setInt(2, ligne.getNoArticle());
                statement.setInt(3, ligne.getQte());
                statement.addBatch();
            }

            int resultats[] = statement.executeBatch();
            connection.commit();
            statement.close();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Erreur d'insertion");
        }
    }

    public int chercherMaximumNo() throws Exception {
        //On trouve le numéro de commande à inserer
        Statement st = connection.createStatement();

        int noMax = 0;
        ResultSet rs = st.executeQuery("SELECT max(noCommande) as max FROM Commande");
        if(rs.next()) {
            noMax = rs.getInt("max");
        }
        return noMax+1;
    }

}
