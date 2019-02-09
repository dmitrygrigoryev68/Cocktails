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
public class Rate  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String rate;

    public Rate(String rate,long id) {
        this.rate = rate;
        this.id=id;
    }


    public Rate() {
    }
}
