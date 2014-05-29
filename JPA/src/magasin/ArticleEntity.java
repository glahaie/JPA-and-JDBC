package magasin;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guillaume on 25/04/14.
 *
 * Générer par IntelliJ: représente la table Article.
 */
@Entity
@Table(name = "ARTICLE", schema = "GUILLAUME", catalog = "")
public class ArticleEntity {
    private BigDecimal noarticle;
    private String description;
    private BigDecimal prixunitaire;
    private String image;
    private String url;
    private BigDecimal quantiteenstock;

    @Id
    @Column(name = "NOARTICLE")
    public BigDecimal getNoarticle() {
        return noarticle;
    }

    public void setNoarticle(BigDecimal noarticle) {
        this.noarticle = noarticle;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PRIXUNITAIRE")
    public BigDecimal getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(BigDecimal prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    @Basic
    @Column(name = "IMAGE")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "QUANTITEENSTOCK")
    public BigDecimal getQuantiteenstock() {
        return quantiteenstock;
    }

    public void setQuantiteenstock(BigDecimal quantiteenstock) {
        this.quantiteenstock = quantiteenstock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleEntity that = (ArticleEntity) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (noarticle != null ? !noarticle.equals(that.noarticle) : that.noarticle != null) return false;
        if (prixunitaire != null ? !prixunitaire.equals(that.prixunitaire) : that.prixunitaire != null) return false;
        if (quantiteenstock != null ? !quantiteenstock.equals(that.quantiteenstock) : that.quantiteenstock != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noarticle != null ? noarticle.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (prixunitaire != null ? prixunitaire.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (quantiteenstock != null ? quantiteenstock.hashCode() : 0);
        return result;
    }
}
