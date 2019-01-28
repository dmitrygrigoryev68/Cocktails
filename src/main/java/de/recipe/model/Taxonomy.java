package de.recipe.model;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table
public class Taxonomy  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameTaxonomy;
    //private Recipe recipe;


    public Taxonomy(String nameTaxonomy,Long id) {
        this.nameTaxonomy = nameTaxonomy;
        this.id=id;
    }

    public Taxonomy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTaxonomy() {
        return nameTaxonomy;
    }

    public void setNameTaxonomy(String nameTaxonomy) {
        this.nameTaxonomy = nameTaxonomy;
    }
}
