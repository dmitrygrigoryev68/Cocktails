package de.recipe.web;

import de.recipe.model.Photo;
import de.recipe.model.Video;
import lombok.Data;
import org.glassfish.jersey.server.Uri;

import java.util.List;
import java.util.Set;

@Data
public class RecipeWeb {
    private String title;
    private String announce;
    private Personweb author;
    private List <IngredientWeb> ingredients;
    private List <RecepiStepWeb> instructions;
    private List <TaxonomyWeb> tags;
    private int prepTimeMinute;
    private int cookingTime;
    private RateWeb rate;
    private List <Photo> image;
    private List<Video>  video;


    public RecipeWeb() {
    }
}

