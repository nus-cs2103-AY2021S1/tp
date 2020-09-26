package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * An Immutable WishfulShrinking that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableWishfulShrinking {

    public static final String MESSAGE_DUPLICATE_RECIPE = "Recipes list contains duplicate recipe(s).";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "Fridge contains duplicate ingredient(s).";

    private final List<JsonAdaptedRecipe> recipes = new ArrayList<>();
    private final List<JsonAdaptedIngredient> ingredients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWishfulShrinking} with the given recipes.
     */
    @JsonCreator
    public JsonSerializableWishfulShrinking(@JsonProperty("recipes") List<JsonAdaptedRecipe> recipes) {
        this.recipes.addAll(recipes);
    }

    /*
     * Constructs a {@code JsonSerializableWishfulShrinking} with the given ingredients.
     */
    /*@JsonCreator
    public JsonSerializableWishfulShrinking(@JsonProperty("ingredients") List<JsonAdaptedIngredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }*/

    /**
     * Converts a given {@code ReadOnlyWishfulShrinking} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableWishfulShrinking}.
     */
    public JsonSerializableWishfulShrinking(ReadOnlyWishfulShrinking source) {
        recipes.addAll(source.getRecipeList().stream().map(JsonAdaptedRecipe::new).collect(Collectors.toList()));
        ingredients.addAll(source.getIngredientList().stream().map(JsonAdaptedIngredient::new).collect(Collectors
                .toList()));
    }

    /**
     * Converts this address book into the model's {@code WishfulShrinking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public WishfulShrinking toModelType() throws IllegalValueException {
        WishfulShrinking addressBook = new WishfulShrinking();
        for (JsonAdaptedRecipe jsonAdaptedRecipe : recipes) {
            Recipe recipe = jsonAdaptedRecipe.toModelType();
            if (addressBook.hasRecipe(recipe)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECIPE);
            }
            addressBook.addRecipe(recipe);
        }
        for (JsonAdaptedIngredient jsonAdaptedIngredient : ingredients) {
            Ingredient ingredient = jsonAdaptedIngredient.toModelType();
            if (addressBook.hasIngredient(ingredient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INGREDIENT);
            }
            addressBook.addIngredient(ingredient);
        }
        return addressBook;
    }

}
