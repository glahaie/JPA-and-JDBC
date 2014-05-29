package magasin;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 */
@Entity
@Table(name = "CATEGORIE", schema = "GUILLAUME", catalog = "")
public class CategorieEntity {
    private BigDecimal code;
    private String libelle;
    private BigDecimal codeparent;

    @Id
    @Column(name = "CODE")
    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }

    @Basic
    @Column(name = "LIBELLE")
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Basic
    @Column(name = "CODEPARENT")
    public BigDecimal getCodeparent() {
        return codeparent;
    }

    public void setCodeparent(BigDecimal codeparent) {
        this.codeparent = codeparent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategorieEntity that = (CategorieEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (codeparent != null ? !codeparent.equals(that.codeparent) : that.codeparent != null) return false;
        if (libelle != null ? !libelle.equals(that.libelle) : that.libelle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        result = 31 * result + (codeparent != null ? codeparent.hashCode() : 0);
        return result;
    }
}
