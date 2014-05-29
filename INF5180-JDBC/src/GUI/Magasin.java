package GUI;

import courtier.*;
import javax.swing.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaume on 25/04/14.
 *
 * Classe gérant l'interface graphique et effectuant le pont avec la base de données.
 */
public class Magasin extends JPanel{

    private Client c;
    private Commande com;
    private JFrame frame;
    private NouvelleCommande nc;
    private int total;
    private ChercherCommande cc;
    private CourtierFacade cf;
    private List<LigneCommande> lc;

    public Magasin() throws Exception {
        cf = new CourtierFacade();
        frame = new JFrame("Magasin");
    }

    public Client getClient() {
        return c;
    }

    public CourtierFacade getCourtier() {
        return cf;
    }


    public void nouvelleRecherche(int noClient, String motPasse) {
        try {
            c = cf.chercherClientParNo(noClient, motPasse);
            this.removeAll();
            cc = new ChercherCommande(this);
            this.add(cc);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            System.out.println("Exception");
            JOptionPane.showMessageDialog(this, "Erreur : numéro de client ou mot de passe invalide");
        }
    }


    public void nouvelleCommande(int noClient, String motPasse) {
        try {
                c = cf.chercherClientParNo(noClient, motPasse);
            int noCommande = cf.chercherMaximumNoCommande();

                this.removeAll();
                nc = new NouvelleCommande(this);
                this.add(nc);
                this.revalidate();
                this.repaint();

                total = 0;

                java.util.Date date = new java.util.Date();
                Format sd = new SimpleDateFormat("dd/MM/yy");
                com = new Commande(noCommande, sd.format(date), noClient);

            lc = new ArrayList<LigneCommande>();


        } catch (Exception e) {
            System.out.println("Exception");
            JOptionPane.showMessageDialog(this, "Erreur : numéro de client ou mot de passe invalide");
        }
    }

    public void ajouterArticle(int no, int qte)  {
        try {

            Article a = cf.chercherArticleParNo(no);

            //On a l'article: on affiche les détails, et on l'ajoute à la commande
            lc.add(new LigneCommande(com.getNoCommande(), no, qte));


            total += qte*a.getPrixUnitaire();

             String s = "Article " + no +  " prix: " + a.getPrixUnitaire() + " qte : "
                        + qte + " total: " + (qte*a.getPrixUnitaire()) + "\n";
             nc.textArea.append(s);
            nc.textArea.append("total = " + total+"\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: article inexistant");
        }
    }

    public void completerCommande() {
        if(lc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vous devez commander au moins un article");
        } else {
            try {
                cf.sauverCommande(com, lc);
                JOptionPane.showMessageDialog(this, "Commande confirmée: Montant total de la commande : " + this.total);

                //On revient au début
                this.removeAll();
                this.add(new ChoixAction(this));
                this.revalidate();
                this.repaint();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la confirmation");
            }
        }

    }

    public void annulerCommande() {
        JOptionPane.showMessageDialog(this, "Commande annulée");

        //On revient au début
        this.removeAll();
        this.add(new ChoixAction(this));
        this.revalidate();
        this.repaint();
    }

    public static void main(String argv[]) throws Exception {

        Magasin m = new Magasin();

        m.add(new ChoixAction(m));
        m.frame.setSize(500, 500);
        m.frame.getContentPane().add(m);
        m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.frame.setVisible(true);

    }


}
