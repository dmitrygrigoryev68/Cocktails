package de.recipe.service;

import de.recipe.web.IngredientWeb;
import de.recipe.web.RecipeWeb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecipeServiceWeb implements RecipeService {


    public RecipeWeb creatIngredientsTuString(RecipeWeb recipeWeb) {
        String[] name=recipeWeb.getIngredients().get(0).getNameIngredient().split(",");
        String[] description=recipeWeb.getIngredients().get(0).getDescriptions().split(",");
        List <IngredientWeb> list= new ArrayList <>();
        for (int i = 0; i < name.length; i++) {
            list.add(new IngredientWeb(name[i],description[i]));
        }
recipeWeb.setIngredients(list);
        return recipeWeb;
    }
}
