package de.cocktail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "photo" )
public class Photo {

    @Id
    @Column( name = "photo_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "photo_path" )
    private String patch;

    @Column( name = "photo_title" )
    private String title;

    @Column( name = "photo_alt" )
    private String alt;
}