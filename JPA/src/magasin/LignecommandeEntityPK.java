package magasin;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 *
 * Générer par Intellij - classe de la clé primaire de LigneCommande.
 */
public class LignecommandeEntityPK implements Serializable {
    private BigDecimal nocommande;
    private BigDecimal noarticle;

    @Column(name = "NOCOMMANDE")
    @Id
    public BigDecimal getNocommande() {
        return nocommande;
    }

    public void setNocommande(BigDecimal nocommande) {
        this.nocommande = nocommande;
    }

    @Column(name = "NOARTICLE")
    @Id
    public BigDecimal getNoarticle() {
        return noarticle;
    }

    public void setNoarticle(BigDecimal noarticle) {
        this.noarticle = noarticle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LignecommandeEntityPK that = (LignecommandeEntityPK) o;

        if (noarticle != null ? !noarticle.equals(that.noarticle) : that.noarticle != null) return false;
        if (nocommande != null ? !nocommande.equals(that.nocommande) : that.nocommande != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nocommande != null ? nocommande.hashCode() : 0;
        result = 31 * result + (noarticle != null ? noarticle.hashCode() : 0);
        return result;
    }
}
