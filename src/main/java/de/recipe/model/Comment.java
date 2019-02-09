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
public class Comment implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column
    private String comment;

    public Comment(String comment,Long id) {
        this.comment = comment;
        this.id=id;
    }
    public Comment() {
    }
//facebook realization
}
