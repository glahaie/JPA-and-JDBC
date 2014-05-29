package magasin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by guillaume on 25/04/14.
 */
@Entity
@Table(name = "LIVRAISON", schema = "GUILLAUME", catalog = "")
public class LivraisonEntity {
    private BigDecimal nolivraison;
    private String datelivraison;

    @Id
    @Column(name = "NOLIVRAISON")
    public BigDecimal getNolivraison() {
        return nolivraison;
    }

    public void setNolivraison(BigDecimal nolivraison) {
        this.nolivraison = nolivraison;
    }

    @Basic
    @Column(name = "DATELIVRAISON")
    public String getDatelivraison() {
        return datelivraison;
    }

    public void setDatelivraison(String datelivraison) {
        this.datelivraison = datelivraison;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LivraisonEntity that = (LivraisonEntity) o;

        if (datelivraison != null ? !datelivraison.equals(that.datelivraison) : that.datelivraison != null)
            return false;
        if (nolivraison != null ? !nolivraison.equals(that.nolivraison) : that.nolivraison != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nolivraison != null ? nolivraison.hashCode() : 0;
        result = 31 * result + (datelivraison != null ? datelivraison.hashCode() : 0);
        return result;
    }
}
