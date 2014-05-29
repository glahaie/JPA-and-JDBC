package magasin;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 */
@Entity
@Table(name = "LIGNECOMMANDE", schema = "GUILLAUME", catalog = "")
@IdClass(LignecommandeEntityPK.class)
public class LignecommandeEntity {
    private BigDecimal nocommande;
    private BigDecimal noarticle;
    private BigDecimal quantite;

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
    @Column(name = "QUANTITE")
    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LignecommandeEntity that = (LignecommandeEntity) o;

        if (noarticle != null ? !noarticle.equals(that.noarticle) : that.noarticle != null) return false;
        if (nocommande != null ? !nocommande.equals(that.nocommande) : that.nocommande != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nocommande != null ? nocommande.hashCode() : 0;
        result = 31 * result + (noarticle != null ? noarticle.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        return result;
    }
}
