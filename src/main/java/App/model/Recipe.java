package App.model;
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
    private List <RecepiSteps> instructions;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Taxonomy> tags;
    @ManyToOne( targetEntity = Comments.class,cascade = CascadeType.ALL )
    private Comments comments;
    private int prepTimeMinute;
    private int cookingTime;
    @OneToOne( targetEntity = Rate.class, cascade = CascadeType.ALL )
    private Rate rate;


}
