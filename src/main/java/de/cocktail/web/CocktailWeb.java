package de.cocktail.web;

import lombok.Data;

import java.util.List;

@Data
public class CocktailWeb {

    private String title;

    private String announce;

    private Userweb author;

    private  List<IngredientWeb >ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private List <PhotoWeb> image;

    public CocktailWeb() {
    }



    public String getTitle() {
        return title;
    }


}

