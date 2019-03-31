import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.Main;
import de.cocktail.controller.CocktailController;
import de.cocktail.model.Cocktail;
import de.cocktail.model.Ingredient;
import de.cocktail.model.Photo;
import de.cocktail.model.User;
import de.cocktail.repository.CocktailRepository;
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


    private Photo photoList = new Photo(1L, "http//google.com", "", "");
    private List<Ingredient> ingredientList = Arrays.asList(new Ingredient(1L, "Wodka", "4 cl Wodka"), new Ingredient(2L, "Pfirsich Likör", "4 cl Pfirsich Likör"), new Ingredient(3L, "cranberry juice or Cranberry Rectal", "8 cl cranberry juice or Cranberry Rectal"), new Ingredient(4L, "orange juice", "8 cl of orange juice"), new Ingredient(5L, "ice", "ice cubes"));
    private Cocktail cocktail = new Cocktail(1L, "Sex on the Beach Cocktail Recipe", "So I have to say one of the best drink's I've drunk so far has become my favorite drink as well", null, 1, 1, new User(1L, "Iurie Railean"), photoList, ingredientList);
    private ModelMapper modelMapper = new ModelMapper();
    private CocktailWebOutput cocktailWebOutput = modelMapper.map(cocktail, CocktailWebOutput.class);
    private List<CocktailWebOutput> cocktailWebOutputs = Arrays.asList(cocktailWebOutput);
    private List<Cocktail> cocktailList = Arrays.asList(cocktail);
    private ObjectMapper objectMapper = new ObjectMapper();
    private String json = objectMapper.writeValueAsString(cocktailWebOutput);
    private String jsonArr = objectMapper.writeValueAsString(cocktailWebOutputs);

    @Autowired

    CocktailController cocktailController;

    public CocktailControllerTest() throws JsonProcessingException {
    }


    @Test
    public void findCocktailsByIngredients_TitleTest() throws Exception {
        when(cocktailRepository.findByIngredients_Title(cocktailWebOutput
                .getIngredients()
                .get(0).getTitle()))
                .thenReturn(cocktailList);

        mockMvc.perform(get("/cocktails/ingredients/{name_ingredient}",
                cocktail.getIngredients().get(0).getTitle()).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().json(jsonArr));

        verify(cocktailRepository, Mockito.times(1))
                .findByIngredients_Title(cocktail.getIngredients().get(0).getTitle());
    }
    @Test
    public void findByIdTest() throws Exception {
        when(cocktailRepository
                .findById(1L)).thenReturn(Optional.ofNullable(cocktail));

        mockMvc.perform(get("/cocktails/{id}",1L)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().json(json));

        verify(cocktailRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findByAuthor_NameTest() throws Exception {
        when(cocktailRepository.findByAuthor_Name(cocktailWebOutput
                .getAuthor().getName())).thenReturn(cocktailList);


        mockMvc.perform(get("/cocktails/author/{name_author}",
                cocktail.getAuthor().getName()).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().json(jsonArr));

        verify(cocktailRepository, Mockito.times(1))
                .findByAuthor_Name(cocktail.getAuthor().getName());
    }

    @Test
    public void findByTitleTest() throws Exception {
        when(cocktailRepository.findByTitle(cocktailWebOutput
                .getTitle())).thenReturn(cocktailList);

        mockMvc.perform(get("/cocktails/title/{title}",
                cocktail.getTitle()).contentType("application/json;charset=UTF-8")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(jsonArr));

        verify(cocktailRepository, Mockito.times(1))
                .findByTitle(cocktail.getTitle());
    }
}