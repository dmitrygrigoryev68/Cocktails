package App.model;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "name_ingredient")
    private String nameIngredient;
    @Column
    private String descriptions;
    // private weightsAndMeasures wam;


}
