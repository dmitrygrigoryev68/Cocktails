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
public class Video {
    @Column( nullable = false )
    private String patch;
    @Id
    @Column( name = "video_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    public Video() {
    }

    public Video(String patch) {
        this.patch = patch;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
