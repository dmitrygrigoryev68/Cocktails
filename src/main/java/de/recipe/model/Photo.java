package de.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
@Transactional
@Entity
@Data
@Table
@EqualsAndHashCode
public class Photo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(length = 5000,nullable = false)
    private String filename;



}
