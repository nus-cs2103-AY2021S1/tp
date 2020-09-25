package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's ingredients in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class IngredientString {
    public static final String MESSAGE_CONSTRAINTS =
            "Ingredients should be separated by commas, "
                    + "and each ingredient should be consisted "
                    + "of alphanumeric characters only";
    public static final String VALIDATION_REGEX = "[\\w\\s]+(,\\s*[\\w\\s]*)*";
    public final String value;

    /**
     * Constructs a {@code IngredientString}.
     *
     * @param ingredients A valid ingredients string.
     */
    public IngredientString(String ingredients) {
        requireNonNull(ingredients);
        ingredients = ingredients.strip();
        checkArgument(isValidIngredient(ingredients), MESSAGE_CONSTRAINTS);
        value = ingredients;
    }

    /**
     * Returns true if a given string is a valid ingredients number.
     */
    public static boolean isValidIngredient(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && value.equals(((Ingredient) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
