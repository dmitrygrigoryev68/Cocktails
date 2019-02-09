package de.recipe.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@Transactional
@Entity
@Data
@EqualsAndHashCode
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
