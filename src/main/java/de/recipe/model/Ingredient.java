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
   @Column(name = "name_ingredient")
    private String nameIngredient;
    @Column
    private String descriptions;
    // private weightsAndMeasures wam;


    public Ingredient() {
    }
    @JsonCreator
    public Ingredient(@JsonProperty("nameIngredient")String nameIngredient,@JsonProperty ("descriptions")String descriptions) {
        this.nameIngredient = nameIngredient;
        this.descriptions = descriptions;
    }
}
