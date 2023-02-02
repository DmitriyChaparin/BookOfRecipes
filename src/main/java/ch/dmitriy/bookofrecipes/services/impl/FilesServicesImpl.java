package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.services.FilesServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServicesImpl implements FilesServices {

    @Value("${path.to.recipes.file}")
    private String recipesFilePath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;

    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    @Override
    public boolean saveRecipeToFile(String json) {
        try {
            cleanFileRecipe();
            Files.writeString(Path.of(recipesFilePath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean saveIngredientToFile(String json) {
        try {
            cleanFileIngredient();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFileRecipes() {
        try {
            return Files.readString(Path.of(recipesFilePath, recipesFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFileIngredients() {
        try {
            return Files.readString(Path.of(ingredientsFilePath, ingredientsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean cleanFileRecipe() {
        try {
            Path path = Path.of(recipesFilePath, recipesFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cleanFileIngredient() {
        try {
            Path path = Path.of(ingredientsFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getRecipesFile() {
        return new File(recipesFilePath + "/" + recipesFileName);
    }

    @Override
    public File getIngredientsFile() {
        return new File(ingredientsFilePath + "/" + ingredientsFileName);
    }


}
