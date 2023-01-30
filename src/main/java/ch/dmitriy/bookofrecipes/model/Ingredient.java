package ch.dmitriy.bookofrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Ingredient {
    @NotBlank(message = "Имя не заполнено")
    private String name;
    @Positive
    private int count;
    private String measureUnit;



}
