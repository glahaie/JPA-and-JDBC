package GUI;

import magasin.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by guillaume on 26/04/14.
 *
 * Interface graphique pour chercher des commandes d'un client.
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
                String s = date.getText();
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
            List<CommandeEntity> liste = m.getFacade().chercherCommandeParDate(s,m.getClient().getNoclient().intValue());

            textArea.setText("");
            afficherInfoClient(m.getClient());
            for (CommandeEntity c : liste) {
                afficherCommandeInfo(c);
                textArea.append("\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Aucune commande");
        }

    }

    private void afficherCommandeParNo(int no) {

        try{
            CommandeEntity com = m.getFacade().chercherCommandeParNo(no, m.getClient().getNoclient().intValue());

            textArea.setText("");
            afficherInfoClient(m.getClient());
            afficherCommandeInfo(com);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Aucune commande");
            }
    }

    private void afficherCommandeInfo(CommandeEntity c) {

        try {
            List<LignecommandeEntity> lignes = m.getFacade().chercherLignesCommande(c.getNocommande().intValue());
            textArea.append("Détail de la commande " + c.getNocommande() + "\n");


            int total = 0;
            for (LignecommandeEntity l : lignes) {
                //On va chercher les infos sur l'article
                ArticleEntity a = m.getFacade().chercherArticleParNo(l.getNoarticle().intValue());


                textArea.append("Article " + a.getNoarticle() + " : " + a.getDescription() + " prix: " + a.getPrixunitaire() +
                        " quantité : " + l.getQuantite());

                total += a.getPrixunitaire().intValue() * l.getQuantite().intValue();

                try {
                    //On pourrait vérifier ici avec sum ,mais je veux traiter l'exception
                    List<DetaillivraisonEntity> livraison = m.getFacade().getLivraisonParNoArticleEtNoCommande(
                            l.getNoarticle().intValue(), l.getNocommande().intValue());
                    int totalLivre = 0;
                    for (DetaillivraisonEntity d : livraison) {
                        totalLivre += d.getQuantitelivree().intValue();
                    }
                    textArea.append(" livre: " + totalLivre + "\n");
                } catch (Exception e) {
                    textArea.append(" livre: 0\n");
                }
            }

            //On affiche le total
            textArea.append("Total : " + total + "\n");
            textArea.append("--------------------\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la lecture de la commande");
        }
    }

    private void afficherInfoClient(ClientEntity c) {
        textArea.append("Client " + c.getNoclient() + " : " + c.getPrenom() + " " + c.getNom() + "\n");
        textArea.append("Adresse: " + c.getAdresselivraison() + "\n");
        textArea.append("courriel : " + c.getCourriel() + "\n");
        textArea.append("Inscription : " + c.getDateinscription() + "\n");
    }
}
