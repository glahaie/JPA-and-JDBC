package magasin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by guillaume on 25/04/14.
 *
 * Générer par IntelliJ: Entité représentant la table Commande.
 */
@Entity
@Table(name = "COMMANDE", schema = "GUILLAUME", catalog = "")
public class CommandeEntity {
    private BigDecimal nocommande;
    private String datecommande;
    private String statutcommande;
    private BigDecimal noclient;

    @Column(name = "NOCLIENT")
    public BigDecimal getNoclient() {
        return noclient;
    }

    public void setNoclient(BigDecimal noclient) {
        this.noclient = noclient;
    }

    @Id
    @Column(name = "NOCOMMANDE")
    public BigDecimal getNocommande() {
        return nocommande;
    }

    public void setNocommande(BigDecimal nocommande) {
        this.nocommande = nocommande;
    }

    @Basic
    @Column(name = "DATECOMMANDE")
    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    @Basic
    @Column(name = "STATUTCOMMANDE")
    public String getStatutcommande() {
        return statutcommande;
    }

    public void setStatutcommande(String statutcommande) {
        this.statutcommande = statutcommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandeEntity that = (CommandeEntity) o;

        if (datecommande != null ? !datecommande.equals(that.datecommande) : that.datecommande != null) return false;
        if (nocommande != null ? !nocommande.equals(that.nocommande) : that.nocommande != null) return false;
        if (statutcommande != null ? !statutcommande.equals(that.statutcommande) : that.statutcommande != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nocommande != null ? nocommande.hashCode() : 0;
        result = 31 * result + (datecommande != null ? datecommande.hashCode() : 0);
        result = 31 * result + (statutcommande != null ? statutcommande.hashCode() : 0);
        return result;
    }
}
