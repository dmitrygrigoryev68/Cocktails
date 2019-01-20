package App.controler;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeControler {
    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping( "/recipes/" )
    public void getAllRecipe() {
        recipeService.getAllRecipe();
    }

    @PostMapping( "/recipes/" )
    public void creaitRecipeControler(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creaitRecipe(recipeWeb);
    }

    @PutMapping( "/recipes/{id}" )
    public Recipe getRecipeByIdControler(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }
}
