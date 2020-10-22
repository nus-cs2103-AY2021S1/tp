package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;

/**
 * An Immutable RecipeList that is serializable to JSON format.
 */
@JsonRootName(value = "recipelist")
class JsonSerializableRecipeList {

    public static final String MESSAGE_DUPLICATE_RECIPE = "Recipes list contains duplicate recipe(s).";

    private final List<JsonAdaptedRecipe> recipes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRecipeList} with the given recipes.
     */
    @JsonCreator
    public JsonSerializableRecipeList(@JsonProperty("recipes") List<JsonAdaptedRecipe> recipes) {
        this.recipes.addAll(recipes);
    }

    /**
     * Converts a given {@code ReadOnlyRecipeList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRecipeList}.
     */
    public JsonSerializableRecipeList(ReadOnlyRecipeList source) {
        recipes.addAll(source.getRecipeList().stream().map(JsonAdaptedRecipe::new).collect(Collectors.toList()));
    }

    /**
     * Converts this recipe list into the model's {@code RecipeList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RecipeList toModelType() throws IllegalValueException {
        RecipeList recipeList = new RecipeList();
        for (JsonAdaptedRecipe jsonAdaptedRecipe : recipes) {
            Recipe recipe = jsonAdaptedRecipe.toModelType();
            if (recipe.getId() > Recipe.getIdCounter()) {
                Recipe.setIdCounter(recipe.getId());
            }
            if (recipeList.hasRecipe(recipe)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECIPE);
            }
            recipeList.addRecipe(recipe);
        }
        return recipeList;
    }

}
