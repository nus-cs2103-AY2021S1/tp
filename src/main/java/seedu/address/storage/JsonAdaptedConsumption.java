package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Calories;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;

public class JsonAdaptedConsumption {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consumption's %s field is missing!";

    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private final Integer calories;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedConsumption(@JsonProperty("name") String name,
                             @JsonProperty("ingredients") ArrayList<Ingredient> ingredients,
                             @JsonProperty("calories") Integer calories) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedConsumption(Recipe source) {
        name = source.getName().fullName;
        ingredients = source.getIngredient();
        calories = source.getCalories().value;
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Consumption} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Consumption.
     */
    public Consumption toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (ingredients == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Ingredient.class.getSimpleName()));
        }
        for (Ingredient ing: ingredients) {
            if (!Ingredient.isValidIngredient(ing.toString())) {
                throw new IllegalValueException(Ingredient.MESSAGE_CONSTRAINTS);
            }
        }
        final ArrayList<Ingredient> modelIngredients = new ArrayList<>(ingredients);

        if (calories == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Calories.class.getSimpleName()));
        }
        if (!Calories.isValidCalories(calories)) {
            throw new IllegalValueException(Calories.MESSAGE_CONSTRAINTS);
        }
        final Calories modelCalories = new Calories(calories);

        return new Consumption(new Recipe(modelName, modelIngredients, modelCalories));
    }
}
