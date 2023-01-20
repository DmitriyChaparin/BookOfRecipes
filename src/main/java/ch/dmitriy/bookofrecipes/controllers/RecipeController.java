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
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeServices.createRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        Recipe recipe = recipeServices.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public String getAllRecipe() {
        return recipeServices.getAllRecipe();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeServices.editRecipe(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeServices.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllRecipe() {
        recipeServices.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }
}
