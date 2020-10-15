package chopchop.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import chopchop.commons.exceptions.IllegalValueException;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Quantity;
import chopchop.model.ingredient.Ingredient;
import chopchop.util.Result;

public class JsonAdaptedIngredient {
    public static final String INGREDIENT_MISSING_FIELD_MESSAGE_FORMAT = "Ingredient's %s field is missing!";

    private final String name;
    private final String quantity;
    private final String expiryDate;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given ingredient details.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("name") String name, @JsonProperty("quantity") String quantity,
                                 @JsonProperty("expiryDate") String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        this.name = source.getName();
        this.quantity = source.getQuantity().toString();
        this.expiryDate = source.getExpiryDate().map(ExpiryDate::toString).orElse("");
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        if (this.name == null) {
            throw new IllegalValueException(String.format(INGREDIENT_MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        if (this.quantity == null) {
            throw new IllegalValueException(String.format(INGREDIENT_MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        Result<Quantity> modelQuantity = Quantity.parse(this.quantity);
        if (modelQuantity.isError()) {
            throw new IllegalValueException(modelQuantity.getError());
        }

        if (this.expiryDate == null) {
            throw new IllegalValueException(String.format(INGREDIENT_MISSING_FIELD_MESSAGE_FORMAT,
                ExpiryDate.class.getSimpleName()));
        }
        if (this.expiryDate.isBlank()) {
            return new Ingredient(this.name, modelQuantity.getValue());
        } else {
            if (!ExpiryDate.isValidDate(this.expiryDate)) {
                throw new IllegalValueException(ExpiryDate.MESSAGE_CONSTRAINTS);
            }

            ExpiryDate modelExpiry = new ExpiryDate(this.expiryDate);
            return new Ingredient(this.name, modelQuantity.getValue(), modelExpiry);
        }
    }
}
