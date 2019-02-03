package de.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
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
