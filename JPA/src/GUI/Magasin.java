package GUI;

import magasin.*;

import javax.persistence.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaume on 25/04/14.
 *
 * Gestion de l'interface graphique et des appels à la façade d'accès pour matérialiser / dématérialiser.
 */
public class Magasin extends JPanel{

    private ClientEntity c;
    private MagasinFacade mf;

    public JFrame frame;
    public CommandeEntity com;
    public NouvelleCommande nc;
    public List<LignecommandeEntity> lignes;
    public int total;
    public ChercherCommande cc;

    public Magasin() {

        try {
            frame = new JFrame("Magasin");
            mf = new MagasinFacade();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la BD");
        }
    }

    public MagasinFacade getFacade() {
        return mf;
    }

    public ClientEntity getClient() {
        return c;
    }

    public void nouvelleRecherche(int noClient, String motPasse) {
        try {
            c = mf.trouverClientParNo(noClient, motPasse);

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

            c = mf.trouverClientParNo(noClient, motPasse);

            this.removeAll();
            nc = new NouvelleCommande(this);
            this.add(nc);
            this.revalidate();
            this.repaint();

            com = new CommandeEntity();
            BigDecimal b = mf.trouverMaxNoCommande();


            com.setNocommande(b.add(new BigDecimal(1)));
            lignes = new ArrayList<LignecommandeEntity>();
            total = 0;

            java.util.Date date = new java.util.Date();
            Format sd = new SimpleDateFormat("dd/MM/yy");
            com.setDatecommande(sd.format(date));
            com.setNoclient(c.getNoclient());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : numéro de client ou mot de passe invalide");
        }
    }

    public void ajouterArticle(long no, int qte)  {
        try {

            ArticleEntity a = mf.trouverArticleParNo(no);

            //On a l'article: on affiche les détails, et on l'ajoute à la commande
            LignecommandeEntity lc = new LignecommandeEntity();
            lc.setNocommande(com.getNocommande());
            lc.setNoarticle(a.getNoarticle());
            lc.setQuantite(new BigDecimal(qte));
            lignes.add(lc);

            int totalTemp = total;
            total += qte*a.getPrixunitaire().intValue();

            String s = "Article " + a.getNoarticle() +  " prix: " + a.getPrixunitaire() + " qte : "
                        + qte + " total: " + (qte*a.getPrixunitaire().intValue()) + "\n";
            nc.textArea.append(s);
            nc.textArea.append("total = " + total+"\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: article inexistant");
        }
    }

    public void completerCommande() {
        if(lignes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vous devez commander au moins un article");
        } else {
            try {
                mf.entrerCommande(com, lignes);
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

    public static void main(String argv[]) throws Exception {

        Magasin m = new Magasin();
        m.add(new ChoixAction(m));
        m.frame.setSize(500, 500);
        m.frame.getContentPane().add(m);
        m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.frame.setVisible(true);

    }

    public void annulerCommande() {
        JOptionPane.showMessageDialog(this, "Commande annulée");

        //On revient au début
        this.removeAll();
        this.add(new ChoixAction(this));
        this.revalidate();
        this.repaint();
    }
}
