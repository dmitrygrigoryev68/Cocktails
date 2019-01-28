package de.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
public class Person implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column
    private String nameaAuthor;

    public Person(String nameaAuthor,Long id) {
        this.nameaAuthor = nameaAuthor;
        this.id=id;
    }

    public Person() {
    }
}
