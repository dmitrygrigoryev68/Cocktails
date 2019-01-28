package de.recipe.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
   @Column
    private String nameIngredient;
    @Column
    private String descriptions;
    // private weightsAndMeasures wam;


    public Ingredient(String nameIngredient, String descriptions,Long id) {
        this.nameIngredient = nameIngredient;
        this.descriptions = descriptions;
        this.id=id;
    }

    public Ingredient() {
    }
}
