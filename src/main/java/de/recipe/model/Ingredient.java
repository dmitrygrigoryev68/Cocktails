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
    @Column( name = "ingredient_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( length = 3000, nullable = false )
    private String name;

    @Column( length = 3000, nullable = false )
    private String description;

    public Ingredient(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
