package de.recipe.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class RecepiStep implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String stepTitle;
    @Column
    private String stepDescription;

    public RecepiStep(String stepTitle, String stepDescription,Long id) {
        this.stepTitle = stepTitle;
        this.stepDescription = stepDescription;
        this.id=id;
    }

    public RecepiStep() {
    }
}

