package de.recipe.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table( name = "recipistep" )
public class RecepiStep implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "recipestep_id", unique = true )
    private Long id;

    @Column
    private String stepTitle;

    @Column

    private String stepDescription;

    public RecepiStep(String stepTitle, String stepDescription, Long id) {
        this.stepTitle = stepTitle;
        this.stepDescription = stepDescription;
        this.id = id;
    }

    public RecepiStep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}

