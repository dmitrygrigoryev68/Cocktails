package de.recipe.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class Taxonomy  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameTaxonomy;
    //private Recipe recipe;


    public Taxonomy(String nameTaxonomy ,Long id) {
        this.nameTaxonomy = nameTaxonomy;
        this.id=id;

    }

    public Taxonomy() {
    }

    public Taxonomy(String ad, long l) {
    }
}
