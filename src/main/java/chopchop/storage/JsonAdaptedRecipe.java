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
import chopchop.util.Result;

public class JsonAdaptedRecipe {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String name;
    private final List<String> ingredientRefs;
    private final List<String> steps;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("name") String name,
                             @JsonProperty("ingredients") List<String> ingredients,
                             @JsonProperty("steps") List<String> steps) {
        this.name = name;
        this.ingredientRefs = new ArrayList<>();
        if (ingredients != null) {
            this.ingredientRefs.addAll(ingredients);
        }

        this.steps = new ArrayList<>();
        if (steps != null) {
            this.steps.addAll(steps);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        name = source.getName().fullName;
        ingredientRefs = new ArrayList<>();
        ingredientRefs.addAll(source.getIngredients().stream()
            .map(x->x.toString())
            .collect(Collectors.toList()));
        steps = new ArrayList<>();
        steps.addAll(source.getSteps().stream()
            .map(x->x.toString())
            .collect(Collectors.toList()));
    }



    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        final List<IngredientReference> modelIngredientRefs = new ArrayList<>();

        for (String ref : ingredientRefs) {
            Result<IngredientReference> result = IngredientReference.parse(ref);
            if (result.isError()) {
                throw new IllegalValueException(result.getError());
            }
            modelIngredientRefs.add(result.getValue());
        }

        final List<Step> modelSteps = new ArrayList<>();

        for (String step : steps) {
            modelSteps.add(new Step(step));
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        return new Recipe(modelName, modelIngredientRefs, modelSteps);
    }

}
