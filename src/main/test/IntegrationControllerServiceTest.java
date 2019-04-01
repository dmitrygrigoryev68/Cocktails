import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.Main;
import de.cocktail.controller.CocktailController;
import de.cocktail.model.Cocktail;
import de.cocktail.model.Ingredient;
import de.cocktail.model.Photo;
import de.cocktail.model.User;
import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
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

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IntegrationControllerServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CocktailController cocktailController;

    @MockBean
    private CocktailService cocktailService;
    private List<Ingredient> ingredientList = Arrays.asList(new Ingredient(1L, "Wodka", "4 cl Wodka"), new Ingredient(2L, "Pfirsich Likör", "4 cl Pfirsich Likör"), new Ingredient(3L, "cranberry juice or Cranberry Rectal", "8 cl cranberry juice or Cranberry Rectal"), new Ingredient(4L, "orange juice", "8 cl of orange juice"), new Ingredient(5L, "ice", "ice cubes"));


    private Photo photoList = new Photo(1L, "http//google.com", "", "");

    private Cocktail cocktail = new Cocktail(1L, "Sex on the Beach Cocktail Recipe", "So I have to say one of the best drink's I've drunk so far has become my favorite drink as well", null, 1, 1, new User(1L, "Iurie Railean"), photoList, ingredientList);

    private ModelMapper modelMapper = new ModelMapper();
    private CocktailWebOutput cocktailWebOutput = modelMapper.map(cocktail, CocktailWebOutput.class);
    private CocktailWeb cocktailWeb = modelMapper.map(cocktail, CocktailWeb.class);
    private List<CocktailWebOutput> cocktailWebOutputs = Arrays.asList(cocktailWebOutput);
    private ObjectMapper objectMapper = new ObjectMapper();

    private String json = objectMapper.writeValueAsString(cocktailWebOutput);
    private String jsonArr = objectMapper.writeValueAsString(cocktailWebOutputs);

    public IntegrationControllerServiceTest() throws JsonProcessingException {
    }

    @Test
    public void generalTestOne() throws Exception {
        assertThat(cocktailController).isNotNull();
    }

    @Test
    public void generalTestTwo() throws Exception {
        this.mockMvc.perform(get("/cocktails/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void getAllCocktailsTest() throws Exception {
        when(cocktailService.getAllCocktails()).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status()
                        .isOk())
                .andExpect(content().
                        contentType("application/json;charset=UTF-8")).
                andExpect(content()
                        .json(jsonArr));
        verify(cocktailService, Mockito.times(1)).getAllCocktails();
    }

    @Test
    public void createCocktailTest() throws Exception {
        mockMvc.perform(post("/cocktails/", cocktailWebOutput)
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isOk());
        verify(cocktailService, Mockito.times(1)).createCocktail(cocktailWeb);

    }

    @Test
    public void getCocktailByIdTest() throws Exception {
        when(cocktailService.getCocktailById(1L)).thenReturn(cocktailWebOutput);

        mockMvc.perform(get("/cocktails/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(content().json(json));
        verify(cocktailService, Mockito.times(1)).getCocktailById(1L);

    }

    @Test
    public void deleteCocktailByIdTest() throws Exception {
        mockMvc.perform(delete("/cocktails/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
        verify(cocktailService, Mockito.times(1)).deleteCocktailById(1L);
    }

    @Test
    public void getCocktailByIngredientTest() throws Exception {
        when(cocktailService.getCocktailByIngredient(cocktailWebOutput.getIngredients().get(0).getTitle())).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/ingredients/{name_ingredient}", cocktail.getIngredients().get(0).getTitle())
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(jsonArr));
        verify(cocktailService, Mockito.times(1)).getCocktailByIngredient(cocktail.getIngredients().get(0).getTitle());

    }

    @Test
    public void getCocktailByAuthorTest() throws Exception {
        when(cocktailService.getCocktailByAuthor(cocktail.getAuthor().getName())).thenReturn(cocktailWebOutputs);
        mockMvc.perform(get("/cocktails/author/{name_author}", "Iurie Railean").contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk()).
                andExpect(content()
                        .json(jsonArr));
        verify(cocktailService, Mockito.times(1)).getCocktailByAuthor(cocktail.getAuthor().getName());
    }

    @Test
    public void updateCocktailByIdTest() throws Exception {
        mockMvc.perform(put("/cocktails/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(json)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status()
                        .isOk());
        verify(cocktailService, Mockito.times(1)).updateCocktail(cocktailWebOutput, 1L);
    }
}
