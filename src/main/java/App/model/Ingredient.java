package App.model;
import lombok.Data;
import javax.persistence.*;

@Embeddable
@Entity
@Data
public class Ingredient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String descriptions;
    // private weightsAndMeasures wam;

}
