package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Calories;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

public class JsonAdaptedConsumption {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consumption's %s field is missing!";

    private final String name;
    private final ArrayList<Instruction> instruction;
    private final String recipeImage;
    private final ArrayList<Ingredient> ingredients;
    private final Integer calories;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedConsumption(@JsonProperty("name") String name,
                             @JsonProperty("instruction") ArrayList<Instruction> instruction,
                             @JsonProperty("recipeImage") String recipeImage,
                             @JsonProperty("ingredients") ArrayList<Ingredient> ingredients,
                             @JsonProperty("calories") Integer calories,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.instruction = instruction;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
        this.calories = calories;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedConsumption(Recipe source) {
        name = source.getName().fullName;
        instruction = source.getInstruction();
        recipeImage = source.getRecipeImage();
        ingredients = source.getIngredient();
        calories = source.getCalories().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Consumption} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Consumption.
     */
    public Consumption toModelType() throws IllegalValueException {
        final List<Tag> recipeTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            recipeTags.add(tag.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (instruction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    String.class.getSimpleName()));
        }
        for (Instruction instr : instruction) {
            if (!Instruction.isValidInstruction(instr)) {
                throw new IllegalValueException(Instruction.MESSAGE_CONSTRAINTS);
            }
        }
        final ArrayList<Instruction> modelInstruction = new ArrayList<>(instruction);

        if (recipeImage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    String.class.getSimpleName()));
        }
        final String modelRecipeImage = recipeImage;

        if (ingredients == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Ingredient.class.getSimpleName()));
        }
        for (Ingredient ing: ingredients) {
            if (!Ingredient.isValidIngredient(ing)) {
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

        final Set<Tag> modelTags = new HashSet<>(recipeTags);

        return new Consumption(new Recipe(modelName, modelInstruction, modelRecipeImage,
                modelIngredients, modelCalories, modelTags));
    }
}
