package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by guillaume on 26/04/14.
 *
 * Interface graphique pour processer une nouvelles commande.
 */
public class NouvelleCommande extends JPanel {

    JButton button;
    SpringLayout spring;
    public JTextArea textArea;
    JTextField qte;
    JTextField noArticle;
    JLabel qteL;
    JLabel noArticleL;
    JPanel commande;
    JButton ajouter;
    JButton completer;
    Magasin m;
    JButton annuler;
    JLabel vide;


    public NouvelleCommande(final Magasin m) {
        super();
        this.m = m;
       this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
       textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setRows(20);
        textArea.setColumns(20);
       this.add(textArea);
        textArea.append("Commande pour le client " + m.getClient().getNoclient() + "\n");

        commande = new JPanel();
        commande.setLayout(new SpringLayout());

        qteL = new JLabel("Quantité");
        noArticleL = new JLabel("Numéro d'article");
        qte = new JTextField();
        noArticle = new JTextField();

        commande.add(noArticleL);
        commande.add(noArticle);
        commande.add(qteL);
        commande.add(qte);
        ajouter = new JButton("Ajouter l'article");

        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                long no = Long.parseLong(noArticle.getText());
                int quantite = Integer.parseInt(qte.getText());
                m.ajouterArticle(no, quantite);
            }
        });
        completer = new JButton("Completer commande");
        completer.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent a) {
                m.completerCommande();
            }
        });

        annuler = new JButton("Annuler la commande");
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m.annulerCommande();
            }
        });
        commande.add(ajouter);
        commande.add(completer);
        commande.add(annuler);

        vide = new JLabel("");
        commande.add(vide);

        SpringUtilities.makeCompactGrid(commande, 4, 2, 6, 6, 6, 6);

        this.add(commande);

    }
}
