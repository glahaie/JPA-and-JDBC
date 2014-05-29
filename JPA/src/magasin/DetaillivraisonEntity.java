package magasin;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 */
@Entity
@Table(name = "DETAILLIVRAISON", schema = "GUILLAUME", catalog = "")
@IdClass(DetaillivraisonEntityPK.class)
public class DetaillivraisonEntity {
    private BigDecimal nolivraison;
    private BigDecimal nocommande;
    private BigDecimal noarticle;
    private BigDecimal quantitelivree;

    @Id
    @Column(name = "NOLIVRAISON")
    public BigDecimal getNolivraison() {
        return nolivraison;
    }

    public void setNolivraison(BigDecimal nolivraison) {
        this.nolivraison = nolivraison;
    }

    @Id
    @Column(name = "NOCOMMANDE")
    public BigDecimal getNocommande() {
        return nocommande;
    }

    public void setNocommande(BigDecimal nocommande) {
        this.nocommande = nocommande;
    }

    @Id
    @Column(name = "NOARTICLE")
    public BigDecimal getNoarticle() {
        return noarticle;
    }

    public void setNoarticle(BigDecimal noarticle) {
        this.noarticle = noarticle;
    }

    @Basic
    @Column(name = "QUANTITELIVREE")
    public BigDecimal getQuantitelivree() {
        return quantitelivree;
    }

    public void setQuantitelivree(BigDecimal quantitelivree) {
        this.quantitelivree = quantitelivree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetaillivraisonEntity that = (DetaillivraisonEntity) o;

        if (noarticle != null ? !noarticle.equals(that.noarticle) : that.noarticle != null) return false;
        if (nocommande != null ? !nocommande.equals(that.nocommande) : that.nocommande != null) return false;
        if (nolivraison != null ? !nolivraison.equals(that.nolivraison) : that.nolivraison != null) return false;
        if (quantitelivree != null ? !quantitelivree.equals(that.quantitelivree) : that.quantitelivree != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nolivraison != null ? nolivraison.hashCode() : 0;
        result = 31 * result + (nocommande != null ? nocommande.hashCode() : 0);
        result = 31 * result + (noarticle != null ? noarticle.hashCode() : 0);
        result = 31 * result + (quantitelivree != null ? quantitelivree.hashCode() : 0);
        return result;
    }
}
