package chopchop.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import chopchop.commons.exceptions.IllegalValueException;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Step;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;

public class JsonAdaptedRecipe {
    public static final String RECIPE_MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedIngredientReference> ingredients;
    private final List<String> steps;
    private final JsonAdaptedTagSet tags;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("name") String name,
                             @JsonProperty("ingredients") List<JsonAdaptedIngredientReference> ingredients,
                             @JsonProperty("steps") List<String> steps,
                             @JsonProperty("tags") JsonAdaptedTagSet tags) {
        this.name = name;
        this.ingredients = ingredients == null ? null : new ArrayList<>(ingredients);
        this.steps = steps == null ? null : new ArrayList<>(steps);
        this.tags = tags;
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        this.name = source.getName();
        this.ingredients = source.getIngredients().stream().map(JsonAdaptedIngredientReference::new)
                .collect(Collectors.toList());
        this.steps = source.getSteps().stream().map(Step::toString).collect(Collectors.toList());
        this.tags = new JsonAdaptedTagSet(source.getTags());
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        if (this.name == null) {
            throw new IllegalValueException(String.format(RECIPE_MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        if (this.ingredients == null) {
            throw new IllegalValueException(String.format(RECIPE_MISSING_FIELD_MESSAGE_FORMAT,
                IngredientReference.class.getSimpleName()));
        }
        List<IngredientReference> modelIngredients = new ArrayList<>();
        for (JsonAdaptedIngredientReference ingredient : this.ingredients) {
            modelIngredients.add(ingredient.toModelType());
        }

        if (this.steps == null) {
            throw new IllegalValueException(String.format(RECIPE_MISSING_FIELD_MESSAGE_FORMAT,
                Step.class.getSimpleName()));
        }
        List<Step> modelSteps = new ArrayList<>();
        for (String step : this.steps) {
            modelSteps.add(new Step(step));
        }

        return new Recipe(this.name, modelIngredients, modelSteps, this.tags.toModelType());
    }
}
