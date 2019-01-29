package de.recipe.service;

import de.exeption.NotFoundRecipeById;
import de.recipe.model.Ingredient;
import de.recipe.model.Recipe;
import de.recipe.repository.RecipeRepository;
import de.recipe.web.RecipeWeb;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();

    }

    public Recipe getRecipeById(Long id) {
        Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundRecipeById("This recipe does not exist");
        }
        else return optionalRecipe.get();
    }

    public void creatRecipe(RecipeWeb recipeWeb) {
        Recipe recipe = creatRecipeToRecipeWeb(recipeWeb);
        recipeRepository.save(recipe);
    }

    public void deleteRecipeById(long id) {
        if (!recipeRepository.existsById(id)) throw new NotFoundRecipeById("This recipe does not exist");
        recipeRepository.deleteById(id);
    }
public  void deleteRecipieByRecipie(Recipe recipe) {
        Long id=recipe.getId();
        recipeRepository.deleteById(id);
}
    public void deleteRecipeByIngredients(Ingredient ingredient) {
         recipeRepository.deleteByIngredientsIn(ingredient);
    }

   private Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb) {
        ModelMapper modelMapper = new ModelMapper();
       return modelMapper.map(recipeWeb, Recipe.class);
    }

    public List <Recipe> findByIngredientsContaining(String nameIngredient) {
        List <Recipe> byIngredients = recipeRepository.findByIngredientsNameIngredientIn(nameIngredient);
        if (byIngredients .isEmpty()) throw new NotFoundRecipeById("This recipe name does not exist");
        else return byIngredients;
    }

    public List <Recipe> findbyAuthor(String nameauthor) {
        List <Recipe> byAuthorNameaAuthor = recipeRepository.findByAuthorNameaAuthor(nameauthor);
        if (byAuthorNameaAuthor.isEmpty()) throw new NotFoundRecipeById("This  author does not exist");
        return byAuthorNameaAuthor;
    }

    public void updateRecipe(Recipe recipe, Long id) {
        Optional <Recipe> recipe1Optional = recipeRepository.findById(id);

        if (!recipe1Optional.isPresent()) {
           throw new NotFoundRecipeById("This  recipe does not exist");
        }
        recipe.setId(id);
       recipeRepository.save(recipe);

    }

}

