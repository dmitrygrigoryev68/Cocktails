package de.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table
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
