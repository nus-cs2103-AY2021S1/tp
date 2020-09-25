package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.Recipe;

/**
 * Jackson-friendly version of {@link Recipe}.
 */
class JsonAdaptedRecipe {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    // Identity fields
    private final int id;

    // Data fields
    private final List<JsonAdaptedIngredient> ingredients = new ArrayList<>();
    private final int productId;
    private final String productName;
    private final String productQuantity;
    private final String description;
    private final boolean isDeleted;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("id") int id,
                             @JsonProperty("ingredients") List<JsonAdaptedIngredient> ingredients,
                             @JsonProperty("productId") int productId,
                             @JsonProperty("productName") String productName,
                             @JsonProperty("productQuantity") String productQuantity,
                             @JsonProperty("description") String description,
                             @JsonProperty("isDeleted") boolean isDeleted) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.description = description;
        this.isDeleted = isDeleted;
        if (ingredients != null) {
            this.ingredients.addAll(ingredients);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        id = source.getId();
        productId = source.getProductId();
        productName = source.getProductName();
        productQuantity = source.getProductQuantity().value;
        description = source.getDescription();
        isDeleted = source.isDeleted();
        List<Ingredient> ingredientList = source.getIngredients().asUnmodifiableObservableList();
        ingredients.addAll(ingredientList.stream()
                .map(JsonAdaptedIngredient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        final List<Ingredient> ingredientList = new ArrayList<>();
        for (JsonAdaptedIngredient ingredient : ingredients) {
            ingredientList.add(ingredient.toModelType());
        }

        IngredientList modelIngredients = new IngredientList();
        modelIngredients.setItems(ingredientList);

        if (productQuantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!ProductQuantity.isValidQuantity(productQuantity)) {
            throw new IllegalValueException(ProductQuantity.MESSAGE_CONSTRAINTS);
        }
        final ProductQuantity modelQuantity = new ProductQuantity(productQuantity);

        return new Recipe(id, modelIngredients, productId, productName, modelQuantity, description, isDeleted);
    }

}
