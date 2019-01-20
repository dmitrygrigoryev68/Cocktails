package App.controler;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.repository.RecipeRepository;
import App.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
.
@RestController
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping(value = "/recipes")
    public List<Recipe> getAllRecipe() {
     return    recipeService.getAllRecipe();
    }

    @PostMapping( "/recipes" )
    public void creatRecipeController(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creatRecipe(recipeWeb);
    }

    @RequestMapping( "/recipes/{id}" )
    public Recipe getRecipeByIdController(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }
}
