package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's ingredients in the address book.
 */
public class Ingredient {
    public final String value;

    /**
     * Constructs a {@code Ingredient}.
     *
     * @param ingredient A valid ingredients number.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        value = ingredient;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns true if both ingredients have the same name.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.toString().equals(toString());
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
