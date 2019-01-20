package App.service;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.repository.RecipeRepository;
import exeption.NotFoundReceptionById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
   @Autowired
    RecipeRepository recipeRepository;

   public List <Recipe>getAllRecipe() {
      return recipeRepository.findAll();
   }
   public Recipe getRecipeById(Long id) {
       Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
       if (!optionalRecipe.isPresent()){
           throw new NotFoundReceptionById("This recept does not exist");
       }
       return optionalRecipe.get();
   }
   public void creaitRecipe(RecipeWeb recipeWeb) {
       Recipe recipe=new Recipe();
       recipe.setAnnounce(recipeWeb.getAnnounce());
       recipe.setAuthor(recipeWeb.getAuthor());
       recipe.setCookingTime(recipeWeb.getCookingTime());
       recipe.setIngredients(recipe.getIngredients());
       recipe.setInstructions(recipeWeb.getInstructions());
       recipe.setPrepTimeMinute(recipeWeb.getPrepTimeMinute());
       recipeRepository.save(recipe);
   }
}
