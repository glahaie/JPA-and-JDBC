package magasin;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 */
@Entity
@Table(name = "CLIENT", schema = "GUILLAUME", catalog = "")
public class ClientEntity {
    private BigDecimal noclient;
    private String nom;
    private String prenom;
    private String adresselivraison;
    private String dateinscription;
    private String heureinscription;
    private String courriel;
    private String motdepasse;

    @Id
    @Column(name = "NOCLIENT")
    public BigDecimal getNoclient() {
        return noclient;
    }

    public void setNoclient(BigDecimal noclient) {
        this.noclient = noclient;
    }

    @Basic
    @Column(name = "NOM")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "PRENOM")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "ADRESSELIVRAISON")
    public String getAdresselivraison() {
        return adresselivraison;
    }

    public void setAdresselivraison(String adresselivraison) {
        this.adresselivraison = adresselivraison;
    }

    @Basic
    @Column(name = "DATEINSCRIPTION")
    public String getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(String dateinscription) {
        this.dateinscription = dateinscription;
    }

    @Basic
    @Column(name = "HEUREINSCRIPTION")
    public String getHeureinscription() {
        return heureinscription;
    }

    public void setHeureinscription(String heureinscription) {
        this.heureinscription = heureinscription;
    }

    @Basic
    @Column(name = "COURRIEL")
    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    @Basic
    @Column(name = "MOTDEPASSE")
    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (adresselivraison != null ? !adresselivraison.equals(that.adresselivraison) : that.adresselivraison != null)
            return false;
        if (courriel != null ? !courriel.equals(that.courriel) : that.courriel != null) return false;
        if (dateinscription != null ? !dateinscription.equals(that.dateinscription) : that.dateinscription != null)
            return false;
        if (heureinscription != null ? !heureinscription.equals(that.heureinscription) : that.heureinscription != null)
            return false;
        if (motdepasse != null ? !motdepasse.equals(that.motdepasse) : that.motdepasse != null) return false;
        if (noclient != null ? !noclient.equals(that.noclient) : that.noclient != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noclient != null ? noclient.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (adresselivraison != null ? adresselivraison.hashCode() : 0);
        result = 31 * result + (dateinscription != null ? dateinscription.hashCode() : 0);
        result = 31 * result + (heureinscription != null ? heureinscription.hashCode() : 0);
        result = 31 * result + (courriel != null ? courriel.hashCode() : 0);
        result = 31 * result + (motdepasse != null ? motdepasse.hashCode() : 0);
        return result;
    }
}
