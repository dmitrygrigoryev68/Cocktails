package App.service;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.repository.RecipeRepository;
import exeption.NotFoundRecipeById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public List <Recipe> getAllRecipe() {
        List <Recipe> all = recipeRepository.findAll();
        return all;
    }
    public Recipe getRecipeById(Long id) {
        Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            throw new NotFoundRecipeById("This recept does not exist");
        } else return optionalRecipe.get();
    }
    public void creatRecipe(RecipeWeb recipeWeb) {

        Recipe recipe = new Recipe();
        recipe.setAnnounce(recipeWeb.getAnnounce());
        recipe.setAuthor(recipeWeb.getAuthor());
        recipe.setCookingTime(recipeWeb.getCookingTime());
        recipe.setIngredients(recipe.getIngredients());
        recipe.setInstructions(recipeWeb.getInstructions());
        recipe.setPrepTimeMinute(recipeWeb.getPrepTimeMinute());
        recipeRepository.save(recipe);
    }
}
