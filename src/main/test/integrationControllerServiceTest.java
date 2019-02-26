/*
@RunWith( SpringRunner.class )
@SpringBootTest( classes = Main.class )
@AutoConfigureMockMvc

public class integrationControllerServiceTest {
    private Taxonomy taxonomy = new Taxonomy("ad", 1L);
    private static List <Ingredient> ingredients = Arrays.asList(new Ingredient("morcovca", "gf",1L),new Ingredient("cartofca","gf",2L));
    private static List <RecepiStep> instructions = Arrays.asList(new RecepiStep("ds", "fd", 1L));
   private static Comment comment= new Comment("ds", 1L);
    private static List<Comment> commentList=Arrays.asList(comment);
    private static List <Taxonomy> tags = Arrays.asList(new Taxonomy("ad", 1L));
    private static Date date = new Date();
    private static String patch="http/photo";
    static List<Photo> photoList=Arrays.asList(new Photo( patch));
    private static String patch="http:/video";
    private static List<Video> videoList=Arrays.asList(new Video( patch));
    private static Recipe recipe = new Recipe(1L, "title", "String announce", date, new User("vasea", 1L), ingredients, instructions, tags,commentList , 23, 23, new Rate("sdf", 1L), photoList, videoList);
    private static Recipe recipesecond = new Recipe(2L, "title", "String announce", date, new User("grisa", 1L), ingredients, instructions, tags, commentList, 23, 23, new Rate("sdf", 1L),photoList, videoList);
    private static RecipeWeb recipeWeb = new RecipeWeb("title", "String announce", new Userweb("fgd"), ingredients, instructions, tags, 23, 23, new Rate("sdf", 1L), photoList, videoList);
    private static List <Recipe> list = Arrays.asList(recipe,recipesecond);
    private  static List<RecipeWeb> recipeWebList=Arrays.asList(recipeWeb);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    RecipeController recipeController;
    @MockBean
    RecipeServiceImpl recipeService;
    @Test
    public void testGetAllRecipe() throws Exception {
        when (recipeService.getAllRecipe()).thenReturn((List <RecipeWebOutput>) recipeWeb);
        mockMvc.perform(get("/recipes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("[{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"name\":\"vasea\"},\"ingredients\":[{\"id\":1,\"name\":\"morcovca\",\"description\":\"gf\"},{\"id\":2,\"name\":\"cartofca\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}},{\"id\":2,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"name\":\"grisa\"},\"ingredients\":[{\"id\":1,\"name\":\"morcovca\",\"description\":\"gf\"},{\"id\":2,\"name\":\"cartofca\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}]\n"));
        verify(recipeService, Mockito.times(1)).getAllRecipe();
    }
    @Test
    public void creatRecipeControllerTest( ) throws Exception {
        String jason=objectMapper.writeValueAsString(recipeWeb);
        mockMvc.perform(post("/recipes/",recipeWeb)
        .contentType("application/json;charset=UTF-8")
                .content(jason))
                .andDo(print())
                .andExpect(status()
                .isOk());
        verify(recipeService,Mockito.times(1) ).creatRecipe(recipeWeb);

    }
    @Test
    public void getRecipeByIdTest() throws Exception {
        when(recipeService.getRecipeById(1L)).thenReturn(recipeWeb);

        mockMvc.perform(get("/recipes/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"id\":1,\"title\":\"title\",\"announce\":\"String announce\",\"author\":{\"id\":1,\"name\":\"vasea\"},\"ingredients\":[{\"id\":1,\"name\":\"morcovca\",\"description\":\"gf\"},{\"id\":2,\"name\":\"cartofca\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}},{\"id\":2,\"title\":\"title\",\"announce\":\"String announce\",\"publicationDate\":\"2019-01-29T14:31:57.370+0000\",\"author\":{\"id\":1,\"name\":\"grisa\"},\"ingredients\":[{\"id\":1,\"name\":\"morcovca\",\"description\":\"gf\"},{\"id\":2,\"name\":\"cartofca\",\"description\":\"gf\"}],\"instructions\":[{\"id\":1,\"stepTitle\":\"ds\",\"stepDescription\":\"fd\"}],\"tags\":[{\"id\":1,\"nameTaxonomy\":\"ad\"}],\"comment\":{\"id\":1,\"comment\":\"ds\"},\"prepTimeMinute\":23,\"cookingTime\":23,\"rate\":{\"id\":1,\"rate\":\"sdf\"}}\n"));
        verify(recipeService, Mockito.times(1)).getRecipeById(1L);

    }
    @Test
    public void delletRecipeByIDTest() throws Exception {
        mockMvc.perform(delete("/delete/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status()
                        .isOk());
        verify(recipeService, Mockito.times(1)).deleteRecipeById(1L);
    }
    @Test
    public void findByIngretientTest() throws Exception {
        mockMvc.perform(get("/searhc/ingredient/{name_ingredient}",recipe.getIngredients().get(0).getName())
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
        verify(recipeService,Mockito.times(1) ).findByIngredientsContaining(recipe.getIngredients().get(0).getName());

    }
    @Test
    public void deletByIngretientTest() throws Exception {
        String jason=objectMapper.writeValueAsString(recipe.getIngredients().get(0));
        mockMvc.perform(delete("/delete/",recipe.getIngredients().get(0))
                .contentType("application/json;charset=UTF-8")
                 .content(jason))
                .andDo(print())
                .andExpect(status()
                        .isOk());
        verify(recipeService,Mockito.times(1) ).deleteRecipeByIngredients(recipe.getIngredients().get(0));

    }
    @Test
    public void findBYAuthorTest() throws Exception {
        when(recipeService.findbyAuthor(recipe.getAuthor().getName())).thenReturn(list);
        mockMvc.perform(get("/search/byAuthor/{string}",recipe.getAuthor().getName())
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status()
                        .isOk());
        verify(recipeService,Mockito.times(1) ).findbyAuthor(recipe.getAuthor().getName());
    }
    @Test
    public void updateRecipeByIdTest() throws Exception {
        String jason=objectMapper.writeValueAsString(recipe);
        when(recipeService.findbyAuthor(recipe.getAuthor().getName())).thenReturn(list);
        mockMvc.perform(put("/recipe/update/{id}",1)
                .contentType("application/json;charset=UTF-8")
                .content(jason))
                .andDo(print())
                .andExpect(status()
                        .isOk());
        verify(recipeService,Mockito.times(1) ).updateRecipe(recipe,1L);
    }
}
*/