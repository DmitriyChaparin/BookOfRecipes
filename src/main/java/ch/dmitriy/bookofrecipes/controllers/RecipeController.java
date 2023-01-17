package ch.dmitriy.bookofrecipes.controllers;

import ch.dmitriy.bookofrecipes.model.Recipe;
import ch.dmitriy.bookofrecipes.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeServices recipeServices;

    public RecipeController(RecipeServices recipeServices) {

        this.recipeServices = recipeServices;
    }

    @PostMapping
    public ResponseEntity createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeServices.createRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/id")
    public ResponseEntity getRecipe(@RequestParam Long recipeId) {
        Recipe recipe = recipeServices.getRecipe(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }


}
