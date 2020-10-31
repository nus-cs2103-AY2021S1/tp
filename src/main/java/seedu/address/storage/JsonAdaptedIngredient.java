package seedu.address.storage;

//import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Name;
//import seedu.address.model.recipe.Tag;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
class JsonAdaptedIngredient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Ingredient's %s field is missing!";

    private final String value;
    private final String quantity;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given ingredient details.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("value") String value,
                                 @JsonProperty("quantity") String quantity) {
        this.value = value;
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        value = source.getValue(); //.fullName;
        quantity = source.getQuantity();
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(value)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        //final Name modelName = new Name(value);

        return new Ingredient(value, quantity);
    }

}
