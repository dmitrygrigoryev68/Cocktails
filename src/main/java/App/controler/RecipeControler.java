package App.controler;

import App.model.Recipe;
import App.model.RecipeWeb;
import App.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;

@Controller
public class RecipeControler {
    @Autowired
    RecipeServiceImpl recipeService;
@RequestMapping("/recipes/")
    public void getAllRecipe() {
    recipeService.getAllRecipe();
}
@PutMapping("/recipes/creayt")
    public  void creaitRecipeControler(@RequestBody RecipeWeb recipeWeb) {
    recipeService.creaitRecipe(recipeWeb);
}
@GetMapping("/recipes/{id}")
    public Recipe getRecipeByIdControler(@PathVariable Long id) {
    return recipeService.getRecipeById(id);
}
}
