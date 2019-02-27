import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.Main;
import de.recipe.controller.RecipeController;
import de.recipe.model.*;
import de.recipe.service.RecipeService;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.*;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc
public class integrationControllerServiceTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    RecipeController recipeController;
    @MockBean
    RecipeService recipeService;


    private List <Ingredient> ingredientList = Arrays.asList(new Ingredient("pepper", "salt and freshly ground pepper"));
    private List <RecepiStep> recepiSteps = Arrays.asList(new RecepiStep("1", "Heat the oil in a large", 1L));
    private List <Taxonomy> taxonomyList = Arrays.asList(new Taxonomy("Italia", 1L));
    private List <Photo> photoList = Arrays.asList(new Photo("1https//hips.hearstapps.com//del.h-cdn.co//assets//17/03//2560x1706//gallery-1484784941-white-cheddar-carbonaral1.jpgresize=768"));
    private List <Video> videoList = Arrays.asList(new Video("1https//www.youtube.com//embed//D_2DBLAt57c"));
    private Recipe recipe = new Recipe("Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!", new Date(), new User("Iurie Railean1", 1L), ingredientList, recepiSteps, taxonomyList, 15, 112, photoList, videoList);
    private List <IngredientWeb> ingredientWebsList = Arrays.asList(new IngredientWeb("pepper", "salt and freshly ground pepper"));
    private List <RecepiStepWeb> recepiStepsWeb = Arrays.asList(new RecepiStepWeb("1", "Heat the oil in a large"));
    private List <TaxonomyWeb> taxonomyWebsList = Arrays.asList(new TaxonomyWeb("Italia"));
    private List <PhotoWeb> photoWebsList = Arrays.asList(new PhotoWeb("1https//hips.hearstapps.com//del.h-cdn.co//assets//17/03//2560x1706//gallery-1484784941-white-cheddar-carbonaral1.jpgresize=768"));
    private List <VideoWeb> videoWebListList = Arrays.asList(new VideoWeb("//1https//www.youtube.com//embed//D_2DBLAt57c"));
    private RecipeWebOutput recipeWebOutput = new RecipeWebOutput(1L, "Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!", new Userweb("Iurie Railean1"), ingredientWebsList, recepiStepsWeb, taxonomyWebsList, 15, 112, photoWebsList, videoWebListList);
    private List <RecipeWebOutput> recipeWebOutputs = Arrays.asList(recipeWebOutput);
    private RecipeWeb recipeWeb = new RecipeWeb("Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!", new Userweb("Iurie Railean1"), ingredientWebsList, recepiStepsWeb, taxonomyWebsList, 15, 112, photoWebsList, videoWebListList);
    private ObjectMapper objectMapper = new ObjectMapper();
    private String jason = objectMapper.writeValueAsString(recipeWebOutput);
    private String jasonArr = objectMapper.writeValueAsString(recipeWebOutputs);

    public integrationControllerServiceTest() throws JsonProcessingException {
    }

    @Test
    public void testGetAllRecipe() throws Exception {
        when(recipeService.getAllRecipe()).thenReturn(recipeWebOutputs);
        mockMvc.perform(get("/getAllRecipes/").contentType(MediaType.APPLICATION_JSON)).
                andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(content().json(jasonArr));
        verify(recipeService, Mockito.times(1)).getAllRecipe();
    }


    @Test
    public void creatRecipeControllerTest() throws Exception {
        mockMvc.perform(post("/addNewRecipes", recipeWebOutput)
                .contentType("application/json;charset=UTF-8")
                .content(jason)).andDo(print())
                .andExpect(status().isOk());
        verify(recipeService, Mockito.times(1)).creatRecipe(recipeWeb);

    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        when(recipeService.getRecipeById(1L)).thenReturn(recipeWebOutput);

        mockMvc.perform(get("/recipes/{id}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(jason));
        verify(recipeService, Mockito.times(1)).getRecipeById(1L);

    }

    @Test
    public void delletRecipeByIDTest() throws Exception {
        mockMvc.perform(delete("/recipes/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().isOk());
        verify(recipeService, Mockito.times(1)).deleteRecipeById(1L);
    }

    @Test
    public void findByIngretientTest() throws Exception {
        when(recipeService.findByIngredientsContaining(recipeWebOutput.getIngredients().get(0).getName())).thenReturn(recipeWebOutputs);
        mockMvc.perform(get("/recipes/ingredients/{name_ingredient}", recipe.getIngredients().get(0).getName()).contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(jasonArr));
        verify(recipeService, Mockito.times(1)).findByIngredientsContaining(recipe.getIngredients().get(0).getName());

    }


    @Test
    public void findBYAuthorTest() throws Exception {
        when(recipeService.findbyAuthor(recipe.getAuthor().getName())).thenReturn(recipeWebOutputs);
        mockMvc.perform(get("/recipes/author/{nameAuthor}", recipe.getAuthor().getName()).contentType("application/json;charset=UTF-8")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().json(jasonArr));
        verify(recipeService, Mockito.times(1)).findbyAuthor(recipe.getAuthor().getName());
    }

    @Test
    public void updateRecipeByIdTest() throws Exception {


        mockMvc.perform(put("/recipes/{id}",1).contentType(MediaType.APPLICATION_JSON).content(jason)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
       verify(recipeService, Mockito.times(1)).updateRecipe(recipeWebOutput,1L);
    }
}
