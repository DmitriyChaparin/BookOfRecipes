package ch.dmitriy.bookofrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {

    private String name;
    private Integer cookingTime;

    private Ingredient ingredients;

    private List<String> cookingInstruction;
}
