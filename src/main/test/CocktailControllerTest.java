import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.Main;
import de.cocktail.controller.CocktailController;
import de.cocktail.model.Cocktail;
import de.cocktail.model.Ingredient;
import de.cocktail.model.Photo;
import de.cocktail.model.User;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWebOutput;
import de.cocktail.web.IngredientWeb;
import de.cocktail.web.UserWeb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)

public class CocktailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CocktailRepository cocktailRepository;

    @MockBean
    private CocktailService cocktailService;


    private Photo photoList = new Photo(1L, "http//google.com", "", "");
    private List<Ingredient> ingredientList = Arrays.asList(new Ingredient(1L, "Wodka", "4 cl Wodka"), new Ingredient(2L, "Pfirsich Likör", "4 cl Pfirsich Likör"), new Ingredient(3L, "cranberry juice or Cranberry Rectal", "8 cl cranberry juice or Cranberry Rectal"), new Ingredient(4L, "orange juice", "8 cl of orange juice"), new Ingredient(5L, "ice", "ice cubes"));
    private Cocktail cocktail = new Cocktail(1L, "Sex on the Beach Cocktail Recipe", "So I have to say one of the best drink's I've drunk so far has become my favorite drink as well", null, 1, 1, new User(1L, "Iurie Railean"), photoList, ingredientList);

    private CocktailWebOutput cocktailWebOutput = new CocktailWebOutput(1l, COCKTAIL_NAME, "", null, new UserWeb(COCKTAIL_AUTHOR_NAME), Arrays.asList(new IngredientWeb(INGRDIENT_NAME, "some description")), 1, 1, null);
    private List<CocktailWebOutput> cocktailWebOutputList = Arrays.asList(cocktailWebOutput);

    private List<Cocktail> cocktailList = Arrays.asList(cocktail);

    private final static String COCKTAIL_NAME = "Sex on the Beach Cocktail Recipe";
    private final static String COCKTAIL_AUTHOR_NAME = "Iurie Railean";
    private final static String INGRDIENT_NAME = "Wodka";

    private final static String COCKTAIL_WEB_ARRAY_JSON = "[{\"id\":1,\"title\":\"Sex on the Beach Cocktail Recipe\",\"announce\":\"So I have to say one of the best drink's I've drunk so far has become my favorite drink as well\",\"publicationDate\":null,\"prepTime\":1,\"cookingTime\":1,\"author\":{\"id\":1,\"name\":\"Iurie Railean\"},\"image\":{\"id\":1,\"path\":\"http//google.com\",\"title\":\"\",\"alt\":\"\"},\"ingredients\":[{\"id\":1,\"title\":\"Wodka\",\"description\":\"4 cl Wodka\"},{\"id\":2,\"title\":\"Pfirsich Likör\",\"description\":\"4 cl Pfirsich Likör\"},{\"id\":3,\"title\":\"cranberry juice or Cranberry Rectal\",\"description\":\"8 cl cranberry juice or Cranberry Rectal\"},{\"id\":4,\"title\":\"orange juice\",\"description\":\"8 cl of orange juice\"},{\"id\":5,\"title\":\"ice\",\"description\":\"ice cubes\"}]}]";

    private final static String COCKTAIL_SEX_ARRAY_JSON = "[{\"id\":1,\"title\":\"Sex on the Beach Cocktail Recipe\",\"announce\":\"So I have to say one of the best drink's I've drunk so far has become my favorite drink as well\",\"publicationDate\":null,\"author\":{\"name\":\"Iurie Railean\"},\"ingredients\":[{\"title\":\"Wodka\",\"description\":\"4 cl Wodka\"},{\"title\":\"Pfirsich Likör\",\"description\":\"4 cl Pfirsich Likör\"},{\"title\":\"cranberry juice or Cranberry Rectal\",\"description\":\"8 cl cranberry juice or Cranberry Rectal\"},{\"title\":\"orange juice\",\"description\":\"8 cl of orange juice\"},{\"title\":\"ice\",\"description\":\"ice cubes\"}],\"prepTimeMinute\":0,\"cookingTime\":1,\"image\":{\"path\":\"http//google.com\",\"title\":\"\",\"alt\":\"\"}}]";
    private final static String COCKTAIL_SEX_JSON = "{\"id\":1,\"title\":\"Sex on the Beach Cocktail Recipe\",\"announce\":\"So I have to say one of the best drink's I've drunk so far has become my favorite drink as well\",\"publicationDate\":null,\"author\":{\"name\":\"Iurie Railean\"},\"ingredients\":[{\"title\":\"Wodka\",\"description\":\"4 cl Wodka\"},{\"title\":\"Pfirsich Likör\",\"description\":\"4 cl Pfirsich Likör\"},{\"title\":\"cranberry juice or Cranberry Rectal\",\"description\":\"8 cl cranberry juice or Cranberry Rectal\"},{\"title\":\"orange juice\",\"description\":\"8 cl of orange juice\"},{\"title\":\"ice\",\"description\":\"ice cubes\"}],\"prepTimeMinute\":0,\"cookingTime\":1,\"image\":{\"path\":\"http//google.com\",\"title\":\"\",\"alt\":\"\"}}";

    @Test
    public void findCocktailsByIngredientsTitleTest() throws Exception {
        when(cocktailService.getCocktailByIngredient(INGRDIENT_NAME))
                .thenReturn(cocktailList);

        mockMvc.perform(get("/cocktails/ingredients/{name_ingredient}", INGRDIENT_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(COCKTAIL_WEB_ARRAY_JSON));

        verify(cocktailService, Mockito.times(1))
                .getCocktailByIngredient(INGRDIENT_NAME);
    }

    @Test
    public void findCocktailsByIngredients_TitleTest() throws Exception {
        when(cocktailRepository.findByIngredients_Title(INGRDIENT_NAME))
                .thenReturn(cocktailList);

        mockMvc.perform(get("/cocktails/ingredients/{name_ingredient}", INGRDIENT_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(COCKTAIL_SEX_ARRAY_JSON));

        verify(cocktailRepository, Mockito.times(1))
                .findByIngredients_Title(INGRDIENT_NAME);
    }

    @Test
    public void findByIdTest() throws Exception {
        when(cocktailRepository
                .findById(1L)).thenReturn(Optional.ofNullable(cocktail));

        mockMvc.perform(get("/cocktails/1")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(COCKTAIL_SEX_JSON));

        verify(cocktailRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findByAuthor_NameTest() throws Exception {
        when(cocktailRepository.findByAuthor_Name(COCKTAIL_AUTHOR_NAME)).thenReturn(cocktailList);


        mockMvc.perform(get("/cocktails/author/{name_author}", COCKTAIL_AUTHOR_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(COCKTAIL_SEX_ARRAY_JSON));

        verify(cocktailRepository, Mockito.times(1))
                .findByAuthor_Name(COCKTAIL_AUTHOR_NAME);
    }

    @Test
    public void findByTitleTest() throws Exception {
        when(cocktailRepository.findByTitle(COCKTAIL_NAME)).thenReturn(cocktailList);

        mockMvc.perform(get("/cocktails/title/{title}",
                COCKTAIL_NAME)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(COCKTAIL_SEX_ARRAY_JSON));

        verify(cocktailRepository, Mockito.times(1))
                .findByTitle(COCKTAIL_NAME);
    }
}