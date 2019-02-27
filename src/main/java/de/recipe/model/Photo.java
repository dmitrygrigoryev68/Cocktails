package de.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class Photo {

    @Id
    @Column( name = "photo_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( length = 5000, nullable = false )
    private String patch;

    public Photo() {
    }

    public Photo(String patch) {
        this.patch = patch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
}
