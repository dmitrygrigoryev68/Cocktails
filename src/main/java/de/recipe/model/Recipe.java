package de.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@Table
public class Recipe implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    @Column(length = 3000)
    private String announce;
    @CreationTimestamp
    @Column( nullable = false, updatable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;
    @ManyToOne( fetch = FetchType.EAGER, targetEntity = Person.class, cascade = CascadeType.ALL )
    private Person author;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Ingredient> ingredients;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <RecepiStep> instructions;
    @OneToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Taxonomy> tags;
    @ManyToMany( cascade = CascadeType.ALL )
    @LazyCollection( LazyCollectionOption.FALSE )
    private Set <Comment> comment;
    private int prepTimeMinute;
    private int cookingTime;
    @OneToOne( targetEntity = Rate.class, cascade = CascadeType.ALL )
    private Rate rate;
    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection( LazyCollectionOption.FALSE )
    private List <Photo> image;
    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection( LazyCollectionOption.FALSE )
    private List<Video>  video;

    public Recipe() {
    }


}
