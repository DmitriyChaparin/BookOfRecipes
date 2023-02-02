package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.model.Recipe;
import ch.dmitriy.bookofrecipes.services.FilesServices;
import ch.dmitriy.bookofrecipes.services.RecipeServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;


@Service
public class RecipeServicesImpl implements RecipeServices {

    private static Map<Long, Recipe> recipes = new HashMap<>();


    private Long recipeId = 1L;
    final private FilesServices filesServices;


    public RecipeServicesImpl(FilesServices filesServices) {
        this.filesServices = filesServices;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //      ошибка при первом запуске???


    @Override
    public Recipe createRecipe(Recipe recipe) {
        for (Recipe rec : recipes.values()) {
            if (rec.equals(recipe)) {
                return recipe;
            }
        }
        Collection<Ingredient> ingredients = IngredientServicesImpl.ingredients.values();
        label:
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            for (Ingredient ingredient : ingredients) {
                if (recipe.getIngredients().get(i).equals(ingredient)) {
                    recipe.getIngredients().add(ingredient);
                    Collections.swap(recipe.getIngredients(), i, recipe.getIngredients().size() - 1);
                    recipe.getIngredients().remove(recipe.getIngredients().size() - 1);
                    continue label;
                }
            }
            IngredientServicesImpl.ingredientId++;
            IngredientServicesImpl.ingredients.put(IngredientServicesImpl.ingredientId, recipe.getIngredients().get(i));
            try {
                String json = new ObjectMapper().writeValueAsString(ingredients);
                filesServices.saveIngredientToFile(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        recipes.put(recipeId, recipe);
        recipeId++;
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe getRecipe(Long recipeId) {
        return recipes.get(recipeId);
    }

    @Override
    public Collection<Recipe> getAllRecipe() {
        return recipes.values();
    }

    @Override
    public Recipe editRecipe(long recipeId, Recipe recipe) {
        if (recipes.containsKey(recipeId)) {
            recipes.put(recipeId, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeId) {
        if (recipes.containsKey(recipeId)) {
            recipes.remove(recipeId);

            return true;
        }
        return false;
    }

    @Override
    public void deleteAllRecipe() {
        recipes = new HashMap<>();
    }

    @Override
    public Path downloadRecipesTxt() throws IOException {
        recipes.getOrDefault(recipeId, null);
        Path recipesTxt = filesServices.creatTempFile("рецепты");
        try (Writer writer = Files.newBufferedWriter(recipesTxt, StandardOpenOption.APPEND)) {
            for (Recipe recipes : recipes.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder instructions = new StringBuilder();
                for (Ingredient ingredient : recipes.getIngredients()) {
                    ingredients.append(ingredient).append("\n");
                }
                for (String instr : recipes.getCookingInstruction()) {
                    instructions.append("\n").append(instr);
                }
                writer.append(recipes.getName()).append("\n").append("Время приготовления: ")
                        .append(String.valueOf(recipes.getCookingTime())).append(" минут").append("\nИнгридиенты: \n")
                        .append(ingredients.toString()).append("Инструкция приготовления: ")
                        .append(instructions.toString());
                writer.append("\n\n");
            }
        }
        return recipesTxt;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesServices.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesServices.readFromFileRecipes();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
