package App.controler;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {
    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping( value = "/recipes")
    public void getAllRecipe() {
        recipeService.getAllRecipe();
    }

    @PostMapping( "/recipes" )
    public void creatRecipeController(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creatRecipe(recipeWeb);
    }

    @GetMapping( "/recipes/{id}" )
    public Recipe getRecipeByIdController(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }
}
