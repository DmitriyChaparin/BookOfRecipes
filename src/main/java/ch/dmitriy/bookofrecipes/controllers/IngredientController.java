package ch.dmitriy.bookofrecipes.controllers;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.services.IngredientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientServices ingredientServices;

    public IngredientController(IngredientServices ingredientServices) {
        this.ingredientServices = ingredientServices;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientServices.creatIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        Ingredient ingredient = ingredientServices.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    public String getAllIngredients() {
        return ingredientServices.getAllIngredient();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientServices.editIngredient(id, ingredient);
            if (ingredient1 == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingredient1);
        }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        if (ingredientServices.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions() {
        ingredientServices.deleteAllIngredients();
        return ResponseEntity.ok().build();
    }
}
