package ch.dmitriy.bookofrecipes.controllers;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.services.IngredientServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientServices ingredientServices;

    public IngredientController(IngredientServices ingredientServices) {
        this.ingredientServices = ingredientServices;
    }

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientServices.creatIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping("/id")
    public ResponseEntity getIngredient(@RequestParam Long ingredientId) {
        Ingredient ingredient = ingredientServices.getIngredient(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
}
