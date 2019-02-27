import de.Main;
import de.recipe.controller.RecipeController;
import de.recipe.model.*;
import de.recipe.repository.RecipeRepository;
import de.recipe.service.RecipeService;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
@SpringBootTest( classes = RecipeController.class )
@AutoConfigureMockMvc
public class testServiceImpl {

    private List <Ingredient> ingredientList = Arrays.asList(new Ingredient("pepper", "salt and freshly ground pepper"));
    private List <RecepiStep> recepiSteps = Arrays.asList(new RecepiStep("1", "Heat the oil in a large", 1L));
    private List <Taxonomy> taxonomyList = Arrays.asList(new Taxonomy("Italia", 1L));
    private List <Photo> photoList = Arrays.asList(new Photo("1https://hips.hearstapps.com/del.h-cdn.co/assets/17/03/2560x1706/gallery-1484784941-white-cheddar-carbonaral1.jpg?resize=768:*"));
    private List <Video> videoList = Arrays.asList(new Video("1https://www.youtube.com/embed/D_2DBLAt57c"));

    private Recipe recipe = new Recipe("Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!", new Date(), new User("Iurie Railean1", 1L), ingredientList, recepiSteps, taxonomyList, 15, 112, photoList, videoList);

    private List <IngredientWeb> ingredientWebsList = Arrays.asList(new IngredientWeb("pepper", "salt and freshly ground pepper"));
    private List <RecepiStepWeb> recepiStepsWeb = Arrays.asList(new RecepiStepWeb("1", "Heat the oil in a large"));
    private List <TaxonomyWeb> taxonomyWebsList = Arrays.asList(new TaxonomyWeb("Italia"));
    private List <PhotoWeb> photoWebsList = Arrays.asList(new PhotoWeb("1https://hips.hearstapps.com/del.h-cdn.co/assets/17/03/2560x1706/gallery-1484784941-white-cheddar-carbonaral1.jpg?resize=768:*"));
    private List <VideoWeb> videoWebListList = Arrays.asList(new VideoWeb("1https://www.youtube.com/embed/D_2DBLAt57c"));
    private RecipeWebOutput recipeWebOutput = new RecipeWebOutput(1L, "Spaghetti BologneseTuna pasta sauce", "The easiest pasta dish you will ever make with just 5 ingredients in 15 min, loaded with Parmesan and crisp bacon goodness!",  new Userweb("Iurie Railean1"), ingredientWebsList, recepiStepsWeb, taxonomyWebsList, 15, 112, photoWebsList, videoWebListList);
    private List <RecipeWebOutput> recipeWebOutputs = Arrays.asList();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;
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
