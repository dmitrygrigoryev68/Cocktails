import de.Main;
import de.recipe.controller.RecipeController;
import de.recipe.model.*;
import de.recipe.repository.RecipeRepository;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class testServiceImpl {
    private Taxonomy taxonomy = new Taxonomy("ad", 1L);
    private static List <Ingredient> ingredients = Arrays.asList(new Ingredient("vfd", "gf",1L));
    private static List <RecepiStep> instructions = Arrays.asList(new RecepiStep("ds", "fd", 1L));
    private static List <Taxonomy> tags = Arrays.asList(new Taxonomy("ad", 1L));
    private static Date date = new Date();
    private static Recipe recipe = new Recipe(1L, "title", "String announce", date, new Person("fgd", 1L), ingredients, instructions, tags, new Comment("ds", 1L), 23, 23, new Rate("sdf", 1L));
    private static RecipeWeb recipeWeb = new RecipeWeb("title", "String announce", new Person("fgd", 1L), ingredients, instructions, tags, 23, 23, new Rate("sdf", 1L));
    private static List <Recipe> list = Arrays.asList(recipe);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private RecipeServiceImpl recipeService;
    @Autowired
    RecipeController recipeController;

    @Test
    public void testServiceGetAll() {
        List <RecipeWebOutput> listService = recipeController.getAllRecipe();
        List <Recipe> listrepo = recipeRepository.findAll();
        Assert.assertEquals(listService.size(), listrepo.size());
        Assert.assertEquals(listrepo, listService);


    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = recipeRepository.getOne(1L);
        mockMvc.perform(get("/recipes/{id}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk());
        Assertions.assertEquals(recipeService.getRecipeById(1L), recipe);


    }
}
