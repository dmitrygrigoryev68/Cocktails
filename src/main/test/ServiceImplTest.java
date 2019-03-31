/*import com.sun.org.glassfish.external.arc.Taxonomy;
import de.Main;
import de.cocktail.controller.CocktailController;
import de.cocktail.model.*;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.service.CocktailService;
import de.cocktail.service.CocktailServiceImpl;
import de.cocktail.web.*;
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
import org.telegram.telegrambots.meta.api.objects.Video;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = CocktailController.class )
@AutoConfigureMockMvc
public class testServiceImpl {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CocktailRepository recipeRepository;

    @Autowired
    private CocktailService cocktailService;
    @Autowired
    CocktailController recipeController;

    @Test
    public void testServiceGetAll() {
        List <CocktailWebOutput> listService = recipeController.getAllCocktails();
        List <Cocktail> listrepo = recipeRepository.findAll();
        Assert.assertEquals(listService.size(), listrepo.size());
        Assert.assertEquals(listrepo, listService);


    }

    @Test
    public void getCocktailsByIdTest() throws Exception {
        Cocktail cocktail = recipeRepository.getOne(1L);
        mockMvc.perform(get("/recipes/{id}", 1L).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk());
        Assertions.assertEquals(cocktailService.getCocktailById(1L), cocktail);


    }
}
*/