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
public class Person implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nameaAuthor;

    public Person(String nameaAuthor,Long id) {
        this.nameaAuthor = nameaAuthor;
        this.id=id;
    }

    public Person() {
    }
}
