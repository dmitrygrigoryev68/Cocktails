package App.controler;
import App.model.Ingredient;
import App.model.Recipe;
import App.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.RecipeWeb;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping( value = "/recipes" )
    public List <Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @PostMapping( value = "/recipes", consumes = "application/json" )
    public void creatRecipeController(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creatRecipe(recipeWeb);
    }

    @RequestMapping( "/recipes/{id}" )
    public Recipe getRecipeByIdController(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping( value = "/delete/{id}" )
    public void delletRecipeByID(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
    }

    @DeleteMapping( value = "delete/" )
    public void deleteRecipeByRecipe(@RequestBody Ingredient ingredient) {
        recipeService.deleteRecipeByRecipe(ingredient);
    }

    @GetMapping( "/searhc/ingredient/{name_ingredient}" )
    public List <Recipe> findByIngredientController(@PathVariable String name_ingredient) {
        return recipeService.findByIngredientsContaining(name_ingredient);
    }

    @RequestMapping( "search/byAuthor/{string}" )
    public List <Recipe> findBYAuthor(@PathVariable String string) {
        return recipeService.findbyAuthor(string);
    }
}
