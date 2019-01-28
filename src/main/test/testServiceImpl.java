import de.Main;
import de.recipe.model.*;
import de.recipe.repository.RecipeRepository;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.RecipeWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class testServiceImpl {

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
    RecipeRepository recipeRepository;

    @MockBean
    private RecipeServiceImpl recipeService;

    @Test
    public void testServiceGetAll() {
        when(recipeRepository.findAll()).thenReturn(list);
        assertEquals(recipeService.getAllRecipe(), list);
        verify(recipeRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void getRecipeByIdTest() {
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);
        assertEquals(recipeService.getRecipeById(1L), recipe);
        verify(recipeService, Mockito.times(1)).getRecipeById(1L);

    }
}
