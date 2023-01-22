package ch.dmitriy.bookofrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
public class Ingredient {
    @NotBlank(message = "Имя не заполнено")
    private String name;
    @Positive
    private int count;
    private String measureUnit;


}
