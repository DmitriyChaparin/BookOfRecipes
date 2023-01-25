package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.services.IngredientServices;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServicesImpl implements IngredientServices {
    private  Map<Long, Ingredient> ingredients = new HashMap<>();
    private Long ingredientId = 1L;

    @Override
    public Ingredient creatIngredient(Ingredient ingredient) {
        ingredients.put(ingredientId, ingredient);
        ingredientId++;
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Long ingredientId) {
        return ingredients.get(ingredientId);

    }

    @Override
    public Collection<Ingredient> getAllIngredient() {
        return ingredients.values();
    }



    @Override
    public Ingredient editIngredient(long ingredientId, Ingredient ingredient) {
        if (!ingredients.containsKey(ingredientId)) {
            throw new NotFoundException("Ингредиент с id" + ingredientId+ " не найден");
        }
        return ingredients.put(ingredientId, ingredient);
    }

    @Override
    public boolean deleteIngredient(long ingredientId) {
        if (!ingredients.containsKey(ingredientId)) {
            throw new NotFoundException("Ингредиент с id" + ingredientId+ " не найден");

        }
        ingredients.remove(ingredientId);
        return true;
    }

    @Override
    public void deleteAllIngredients() {
        ingredients = new HashMap<>();
    }
}

