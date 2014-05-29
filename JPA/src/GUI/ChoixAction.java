package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SpringLayout;

/**
 * Created by guillaume on 26/04/14.
 *
 * Interface graphique permettant de choisir l'action d'un client: rechercher des commandes ou entrer une
 * nouvelle commande.
 */
public class ChoixAction extends JPanel {

    JLabel noClientL, mPasseL;
    JTextField noClient, mPasse;
    JButton nouvelleCommande, chercherCommande;
    Magasin m;

    public ChoixAction(final Magasin m) {
        this.m = m;
        this.setLayout(new SpringLayout());

        noClientL = new JLabel("Numéro de client");
        mPasseL = new JLabel("mot de passe");
        noClient = new JTextField();
        mPasse = new JTextField();
        noClientL.setLabelFor(noClient);
        mPasseL.setLabelFor(mPasse);

        nouvelleCommande = new JButton("Nouvelle commande");
        nouvelleCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m.nouvelleCommande(Integer.parseInt(noClient.getText()), mPasse.getText());
            }
        });

        chercherCommande = new JButton("Chercher commande");
        chercherCommande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m.nouvelleRecherche(Integer.parseInt(noClient.getText()), mPasse.getText());
            }
        });

        this.add(noClientL);
        this.add(noClient);
        this.add(mPasseL);
        this.add(mPasse);
        this.add(nouvelleCommande);
        this.add(chercherCommande);

        SpringUtilities.makeCompactGrid(this, 3, 2, 6, 6, 6, 6);

    }
}
