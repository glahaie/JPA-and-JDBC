package courtier;

import java.sql.*;

/*
    Patron courtier pour l'accès aux clients.
 */

class CourtierBDClient {

    private Connection connection;

    public CourtierBDClient(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Client chercherClientParNoClient(int noClient, String motPasse)
            throws Exception {

        PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM Client WHERE noClient = ? " +
                        "AND motDepasse = ?");
        statement.setInt(1, noClient);
        statement.setString(2, motPasse);

        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            Client client = new Client(noClient,
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("adresseLivraison"),
                    rs.getString("dateInscription"),
                    rs.getString("heureInscription"),
                    rs.getString("courriel"),
                    motPasse);
            statement.close();
            return client;
        } else {
            throw new Exception("Aucun client trouve");
        }
    }

}
