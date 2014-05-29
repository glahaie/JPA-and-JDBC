package magasin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by guillaume on 26/04/14.
 *
 * Facade d'accès à la BD pour matérialiser / dématérialiser.
 */
public class MagasinFacade {


    private EntityManager em;
    private EntityTransaction et;


    public MagasinFacade() throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("INF5180");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }


    public ClientEntity trouverClientParNo(int noClient, String motPasse) throws Exception {
        et.begin();
        ClientEntity c;

        Query q = em.createQuery("SELECT c FROM ClientEntity c WHERE c.noclient = :unClient AND c.motdepasse = :unMotDePasse");
        q.setParameter("unClient", noClient);
        q.setParameter("unMotDePasse", motPasse);

        try {
            c = (ClientEntity) q.getSingleResult();
        } catch (Exception e) {
            et.rollback();
            throw new Exception("Erreur de client");
        }
        et.commit();
        return c;
    }


    public BigDecimal trouverMaxNoCommande() throws Exception {
        et.begin();
        Query q = em.createQuery("SELECT max(com.nocommande) FROM CommandeEntity com");
        BigDecimal b = (BigDecimal)q.getSingleResult();

        et.commit();
        return b;
    }


    public ArticleEntity trouverArticleParNo(long no) throws Exception {

        et.begin();
        ArticleEntity a;

        Query q = em.createQuery("SELECT a FROM ArticleEntity a WHERE a.noarticle = :unNoArticle");
        q.setParameter("unNoArticle", no);
        try {
            a = (ArticleEntity)q.getSingleResult();
        } catch (Exception e) {
            et.rollback();
            throw new Exception("Mauvais article");
        }
        et.commit();
        return a;
    }

    public void entrerCommande(CommandeEntity c, List<LignecommandeEntity> l) throws Exception {
        try {
            et.begin();
            c.setStatutcommande("Confirmé");
            em.persist(c);
            for (LignecommandeEntity ligne : l) {
                em.persist(ligne);
            }
            et.commit();
        } catch (Exception e) {
            et.rollback();
            throw new Exception("Erreur de matérialisation");
        }

    }

    public List<CommandeEntity> chercherCommandeParDate(String date, int noClient) throws Exception {
        et.begin();
        Query q = em.createQuery("SELECT c FROM CommandeEntity c WHERE c.noclient = :unNoClient" +
                " AND c.datecommande = :uneDate");
        q.setParameter("unNoClient", noClient);
        q.setParameter("uneDate", date);

        List<CommandeEntity> liste = (List<CommandeEntity>)q.getResultList();
        if(liste.isEmpty()) {
            et.rollback();
            throw new Exception("Liste vide");
        }
        et.commit();
        return liste;
    }

    public CommandeEntity chercherCommandeParNo(int noCommande, int noClient) throws Exception {
        et.begin();

        Query q = em.createQuery("SELECT com from CommandeEntity com WHERE com.nocommande = :unNoCommande" +
                " AND com.noclient = :unNoClient");
        q.setParameter("unNoCommande", noCommande);
        q.setParameter("unNoClient", noClient);

        CommandeEntity com;
        try {
            com = (CommandeEntity)q.getSingleResult();
        } catch (Exception e) {
            et.rollback();
            throw new Exception("Erreur");
        }

        et.commit();
        return com;
    }

    public List<LignecommandeEntity> chercherLignesCommande(int noCommande) throws Exception {
        et.begin();

        Query q = em.createQuery("SELECT ligne from LignecommandeEntity ligne WHERE ligne.nocommande = :unNoCommande");
        q.setParameter("unNoCommande", noCommande);

        List<LignecommandeEntity> lignes = (List<LignecommandeEntity>)q.getResultList();

        et.commit();
        return lignes;
    }

    public ArticleEntity chercherArticleParNo(int noArticle) throws Exception {
        et.begin();
        ArticleEntity a;
        Query q = em.createQuery("SELECT a from ArticleEntity a WHERE a.noarticle = :unNoArticle");
        q.setParameter("unNoArticle", noArticle);
        a = (ArticleEntity) q.getSingleResult();
        et.commit();
        return a;
    }

    public List<DetaillivraisonEntity> getLivraisonParNoArticleEtNoCommande(int noArticle, int noCommande) throws Exception {
        et.begin();

        Query q = em.createQuery("SELECT dl FROM DetaillivraisonEntity dl WHERE dl.noarticle = :unNoArticle" +
                " AND dl.nocommande = :unNoCommande");
        q.setParameter("unNoArticle", noArticle);
        q.setParameter("unNoCommande", noCommande);
        List<DetaillivraisonEntity> livraison = (List<DetaillivraisonEntity>) q.getResultList();
        if(livraison.isEmpty()) {
            et.rollback();
            throw new Exception("Pas de livraison");
        }
        et.commit();
        return livraison;
    }



}
