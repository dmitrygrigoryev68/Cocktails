import de.Main;
import de.recipe.controller.RecipeController;
import de.recipe.model.*;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.RecipeWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class testRecipeContrroler {

    Taxonomy taxonomy = new Taxonomy("ad", 1L);
    private static List <Ingredient> ingredients = Arrays.asList(new Ingredient("vfd", "gf", 1L));
    private static List <RecepiStep> instructions = Arrays.asList(new RecepiStep("ds", "fd", 1L));
    private static List <Taxonomy> tags = Arrays.asList(new Taxonomy("ad", 1L));
    private static Date date = new Date();
    private static Recipe recipe = new Recipe(1L, "title", "String announce", date, new Person("fgd", 1L), ingredients, instructions, tags, new Comment("ds", 1L), 23, 23, new Rate("sdf", 1L));
    private static RecipeWeb recipeWeb = new RecipeWeb("title", "String announce", new Person("fgd", 1L), ingredients, instructions, tags, 23, 23, new Rate("sdf", 1L));
    private static List <Recipe> list = Arrays.asList(recipe);
    @Autowired
    private MockMvc mockMvc;

    @MockBean
   private RecipeController recipeController;
     @MockBean
  private   RecipeServiceImpl recipeService;

    @Test
    public void testGetAllRecipe() throws Exception {

        when(recipeController.getAllRecipe()).thenReturn(list);

        mockMvc.perform(get("/recipes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(content().json("[{\"title\":\"title\"}]"))
        .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"nameaAuthor\":\"fgd\"},\"ingredients\":[{\"id\":1,\"nameIngredient\":\"vfd\",\"descriptions\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).getAllRecipe();

    }
    @Test
    public void getRecipeByIdTest() throws Exception {
when(recipeController.getRecipeByIdController(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("{\"title\":\"title\"}"))
                .andExpect(content().json("{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"nameaAuthor\":\"fgd\"},\"ingredients\":[{\"id\":1,\"nameIngredient\":\"vfd\",\"descriptions\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}\n"));
        Mockito.verify(recipeController, Mockito.times(1)).getRecipeByIdController(1L);

    }
    @Test
    public void testSearcIngredient() throws Exception {
        when(recipeController.findByIngredientController("vfd")).thenReturn(list);

        mockMvc.perform(get("/searhc/ingredient/{name_ingredient}","vfd")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("[{\"title\":\"title\"}]"))
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"nameaAuthor\":\"fgd\"},\"ingredients\":[{\"id\":1,\"nameIngredient\":\"vfd\",\"descriptions\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).findByIngredientController("vfd");

    }
    @Test
    public void testSearcbyAuthor() throws Exception {
        when(recipeController.findBYAuthor("fgd")).thenReturn(list);

        mockMvc.perform(get("/search/byAuthor/{string}","fgd")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().json("[{\"title\":\"title\"}]"))
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"nameaAuthor\":\"fgd\"},\"ingredients\":[{\"id\":1,\"nameIngredient\":\"vfd\",\"descriptions\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        Mockito.verify(recipeController, Mockito.times(1)).findBYAuthor("fgd");

    }



}
