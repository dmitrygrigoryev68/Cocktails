package de.cocktail.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoWeb {

    private String path;


    private String title;


    private String alt;
}