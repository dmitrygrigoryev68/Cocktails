package de.recipe.model;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String announce;
    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;
    @ManyToOne( targetEntity = Person.class, cascade = CascadeType.ALL )
    private Person author;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Ingredient> ingredients;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <RecepiStep> instructions;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Taxonomy> tags;
    @ManyToOne( targetEntity = Comment.class,cascade = CascadeType.ALL )
    private Comment comment;
    private int prepTimeMinute;
    private int cookingTime;
    @OneToOne( targetEntity = Rate.class, cascade = CascadeType.ALL )
    private Rate rate;

    public Recipe(Long id,String title, String announce, Date publicationDate, Person author, List <Ingredient> ingredients, List <RecepiStep> instructions, List <Taxonomy> tags, Comment comment, int prepTimeMinute, int cookingTime, Rate rate) {
        this.title = title;
        this.announce = announce;
        this.publicationDate = publicationDate;
        this.author = author;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
        this.comment = comment;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.rate = rate;
        this.id=id;
    }

    public Recipe() {
    }
}
