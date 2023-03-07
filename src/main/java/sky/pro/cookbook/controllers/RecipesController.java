package sky.pro.cookbook.controllers;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.cookbook.model.Recipe;
import sky.pro.cookbook.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipesController {

    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/show/{numericRec}")// метод получает рецепт из карты используя параметр из адреса URl
    public ResponseEntity<Recipe> showRecipe(@PathVariable int numericRec)
    {
        Recipe showNewRecipe=recipeService.showRecList ( numericRec );
        if (showNewRecipe==null){
            return ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok (showNewRecipe);
    }
    @PostMapping("/add") //метод добавляет рецепт
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe)
    {
        Recipe addNewRecipe= recipeService.addRecList ( recipe );
        return ResponseEntity.ok (addNewRecipe);
    }
    @PutMapping("/edit/{numericRec}")//метод редактирует рецепт
    public ResponseEntity<Recipe> editRecipe(@PathVariable int numericRec, @RequestBody Recipe recipe){
        Recipe editRec= recipeService.modifyRecipe ( numericRec,recipe);
        if ((recipe==null)){
            ResponseEntity.notFound ().build ();
        }
        return ResponseEntity.ok (editRec);
    }
    @DeleteMapping("/delete/{numericRec}")// удаляет рецепт
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int numericRec){
        if (recipeService.removeRecipe ( numericRec )){
            return ResponseEntity.ok ().build ();
        }
        return ResponseEntity.notFound ().build ();
    }

    @GetMapping("/showall")//выводит список всех рецептов
    public void showAll(){
        recipeService.showRecipeList ();
    }



}
