package de.recipe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table( name = "user" )
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column
    String userName;
    String userFamyli;
    String userEmail;
    String userPasword;
    String userRol;
    String header;
}
