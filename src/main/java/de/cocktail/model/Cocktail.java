package de.cocktail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Transactional
@Entity
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "cocktail" )
public class Cocktail implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "cocktail_id" )
    private Long id;

    @Column( name = "cocktail_title" )
    private String title;

    @Column(name = "cocktail_announce")
    private String announce;

    @CreationTimestamp
    @Column(name = "cocktail_publication_date")
    @Temporal( TemporalType.TIMESTAMP )
    private Date publicationDate;

    @Column(name = "cocktail_prep_time_minute")
    private int prepTimeMinute;

    @Column(name = "cocktail_cooking_time")
    private int cookingTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_photo_id", referencedColumnName = "photo_id")
    private Photo image;

    @ManyToMany( cascade = CascadeType.ALL, targetEntity = Ingredient.class )
    @LazyCollection( LazyCollectionOption.FALSE )
    @JoinTable(
            name = "cocktail_ingredients",
            joinColumns = {@JoinColumn(name = "fk_cocktail_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_ingredient_id")}
    )
    private List <Ingredient> ingredients;
}
