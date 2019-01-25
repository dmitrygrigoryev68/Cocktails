package de.service;
import de.model.Ingredient;
import de.model.Recipe;
import de.repository.RecipeRepository;
import exeption.NotFoundRecipeById;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.RecipeWeb;
import java.util.List;
import java.util.Optional;

;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository  recipeRepository;

    public List <Recipe> getAllRecipe() {
        return recipeRepository.findAll();

    }

    public Recipe getRecipeById(Long id) {
        Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundRecipeById("This recipe does not exist");
        } else return optionalRecipe.get();
    }

    public void creatRecipe(RecipeWeb recipeWeb) {
        Recipe recipe = creatRecipeToRecipeWeb(recipeWeb);
        recipeRepository.save(recipe);
    }

    public void deleteRecipeById(long id) {
        if (!recipeRepository.existsById(id)) throw new NotFoundRecipeById("This recipe does not exist");
        recipeRepository.deleteById(id);
    }

    public void deleteRecipeByRecipe(Ingredient ingredient) {
        recipeRepository.deleteByIngredientsIn(ingredient);
    }

    private Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb) {
        ModelMapper modelMapper = new ModelMapper();
        Recipe recipe = modelMapper.map(recipeWeb, Recipe.class);

        return recipe;
    }
   public List <Recipe> findByIngredientsContaining(String nameIngredient) {
        List <Recipe> byIngredients = recipeRepository.findByIngredientsNameIngredientIn(nameIngredient);
        if (byIngredients==null)throw new NotFoundRecipeById("This recipe name does not exist");
        else return byIngredients;
    }
    public List<Recipe> findbyAuthor(String nameauthor) {
        List <Recipe> byAuthorNameaAuthor = recipeRepository.findByAuthorNameaAuthor(nameauthor);
        if (byAuthorNameaAuthor.isEmpty())throw new NotFoundRecipeById("This recipe name does not exist");
        return byAuthorNameaAuthor;
    }

}

