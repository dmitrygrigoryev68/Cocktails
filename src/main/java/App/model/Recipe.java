package App.model;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    private String announce;
    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;
    @ManyToOne( targetEntity = Person.class )
    private Person author;
    @ElementCollection
    private List <Ingredient> ingredients;
    @ElementCollection()
    private List <RecepiSteps> instructions;
    @ElementCollection
    private List <Taxonomy> tags;
    // private Comments comments;
    private int prepTimeMinute;
    private int cookingTime;
    //private Rate rate;


}
