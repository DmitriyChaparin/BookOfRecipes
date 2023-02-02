package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.services.FilesServices;
import ch.dmitriy.bookofrecipes.services.IngredientServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServicesImpl implements IngredientServices {
    public static Map<Long, Ingredient> ingredients = new HashMap<>();

    public static Long ingredientId = 1L;


    final private FilesServicesImpl filesServices;

    public IngredientServicesImpl(FilesServices filesServices) {
        this.filesServices = (FilesServicesImpl) filesServices;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient creatIngredient(Ingredient ingredient) {
        for (Ingredient ingr : ingredients.values()) {
            if (ingr.equals(ingredient)) {
                return ingr;
            }
        }
        ingredients.put(ingredientId, ingredient);
        ingredientId++;
        saveToFile();
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
            throw new NotFoundException("Ингредиент с id" + ingredientId + " не найден");
        }
        ingredients.put(ingredientId, ingredient);
        saveToFile();
        return ingredient;

    }

    @Override
    public boolean deleteIngredient(long ingredientId) {
        if (!ingredients.containsKey(ingredientId)) {
            throw new NotFoundException("Ингредиент с id" + ingredientId + " не найден");

        }
        ingredients.remove(ingredientId);
        return true;
    }

    @Override
    public void deleteAllIngredients() {
        ingredients = new HashMap<>();
    }

    public void saveToFile() {
        try {
            IngredientDataFile ingredientDataFile = new IngredientDataFile(ingredientId, ingredients);
            String json = new ObjectMapper().writeValueAsString(ingredientDataFile);
            filesServices.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesServices.readFromFileIngredients();
            IngredientDataFile ingredientDataFile = new ObjectMapper().readValue(json, new TypeReference<IngredientDataFile>() {
            });
            ingredientId = ingredientDataFile.getIngredientId();
            ingredients = ingredientDataFile.getIngredients();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }//не  правильно добовляются ингредиенты с рецептов
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class IngredientDataFile {
        private Long ingredientId;
        private Map<Long, Ingredient> ingredients;

    }
}

