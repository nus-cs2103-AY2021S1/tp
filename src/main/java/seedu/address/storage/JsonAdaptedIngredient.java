package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
class JsonAdaptedIngredient {

    private final Integer itemId;
    private final Integer itemQuantity;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the
     * given {@code itemId} and {@code itemQuantity}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("itemId") int itemId,
                                 @JsonProperty("itemQuantity") int itemQuantity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        itemId = source.getKey();
        itemQuantity = source.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        //TODO: check if exception needed here
        return new Ingredient(itemId, itemQuantity);
    }

}
