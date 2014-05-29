package GUI;

import courtier.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by guillaume on 26/04/14.
 *
 * Classe affichant le formulaire et le résultat d'une recherche de commande pour un client
 * On peut chercher à l'aide d'un numéro de commande, ou d'une date.
 */
public class ChercherCommande extends JPanel {

    JButton button;
    SpringLayout spring;
    public JTextArea textArea;
    JTextField date;
    JTextField noCommande;
    JLabel dateL;
    JLabel noCommandeL;
    JPanel commande;
    JButton chercherNo;
    JButton chercherDate;
    Magasin m;


    public ChercherCommande(final Magasin m) {
        super();
        this.m = m;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setRows(20);
        textArea.setColumns(20);
        this.add(textArea);

        commande = new JPanel();
        commande.setLayout(new SpringLayout());

        dateL = new JLabel("Date de la commande");
        noCommandeL = new JLabel("Numéro de la commande");
        date = new JTextField();
        noCommande = new JTextField();


        chercherNo = new JButton("Chercher par numéro d'article");
        chercherDate = new JButton("Chercher par date");

        chercherNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int no = Integer.parseInt(noCommande.getText());
                afficherCommandeParNo(no);
            }
        });

        chercherDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String s = date.getText().trim();
                afficherCommandesParDate(s);
            }


        });
        commande.add(noCommandeL);
        commande.add(dateL);
        commande.add(noCommande);
        commande.add(date);
        commande.add(chercherNo);
        commande.add(chercherDate);

        SpringUtilities.makeCompactGrid(commande, 3, 2, 6, 6, 6, 6);

        this.add(commande);
        afficherInfoClient(m.getClient());

    }

    private void afficherCommandesParDate(String s) {

        try {
            List<Commande> liste = m.getCourtier().chercherCommandeParDate(s, m.getClient().getNoClient());

            if(liste.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucune commande");
            } else {
                textArea.setText("");
                afficherInfoClient(m.getClient());
                for (Commande c : liste) {
                    afficherCommandeInfo(c);
                    textArea.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Aucune commande");
        }

    }

    private void afficherCommandeParNo(int no) {

        try{
            Commande c = m.getCourtier().chercherCommandeParNo(no, m.getClient().getNoClient());

            textArea.setText("");
            afficherInfoClient(m.getClient());
            afficherCommandeInfo(c);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Aucune commande");
            }

    }

    private void afficherCommandeInfo(Commande c) {

        List<LigneCommande> l;

        textArea.append("Détail de la commande " + c.getNoCommande() + "\n");

        try {
            l = m.getCourtier().chercherLignesCommandeParNo(c.getNoCommande());

            //On va chercher les infos sur l'article
            Article a = null;
            int total = 0;
            for(LigneCommande ligne:l) {
                a = m.getCourtier().chercherArticleParNo(ligne.getNoArticle());
                textArea.append("Article " + a.getNoArticle() + " : " + a.getDescription() + " prix: " + a.getPrixUnitaire() +
                        " quantité : " + ligne.getQte());

                total += a.getPrixUnitaire()*ligne.getQte();
                //Maintenant on cherche combien l'article est livré
                try {
                    //On pourrait vérifier ici avec sum ,mais je veux traiter l'exception
                    int qteLivre = m.getCourtier().chercherTotalLivrePourCommandeArticle(a.getNoArticle(), c.getNoCommande());
                    textArea.append(" livre: " + qteLivre + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    textArea.append(" livre: 0\n");
                }

            }

            //On affiche le total
            textArea.append("Total : " + total+ "\n");
            textArea.append("--------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la requete");
        }
    }

    private void afficherInfoClient(Client c) {
        textArea.append("Client " + c.getNoClient() + " : " + c.getPrenom() + " " + c.getNom() + "\n");
        textArea.append("Adresse: " + c.getAdresseLivraison() + "\n");
        textArea.append("courriel : " + c.getCourriel() + "\n");
        textArea.append("Inscription : " + c.getDateInscription() + "\n");
    }
}
