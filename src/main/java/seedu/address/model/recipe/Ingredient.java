package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Objects;

import seedu.address.logic.commands.EditIngredientCommand;

/**
 * Represents a Recipe's ingredients in the Wishful Shrinking.
 */
public class Ingredient {
    public static final String MESSAGE_CONSTRAINTS =
            "1. Ingredient name is compulsory and should consist of only alphanumeric characters.\n"
                    + "2. Each ingredient is separated by a comma.\n"
                    + "3. Each ingredient has an optional field quantity that is separated by a spaced followed "
                    + "by a hyphen.\n"
                    + "4. Ingredient quantity should only consist of alphanumeric, full stop and forward slashes"
                    + ". ";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ][\\p{Alnum} ]*";
    public static final String VALIDATION_REGEX_QUANTITY = "[\\p{Alnum}\\/\\. ]*";
    private String value;
    private String quantity;

    /**
     * Constructs a {@code Ingredient} with quantity.
     *
     * @param ingredient A valid ingredients number.
     */
    public Ingredient(String ingredient, String quantity) {
        requireNonNull(ingredient, quantity);
        value = ingredient;
        this.quantity = quantity;
    }

    /**
     * Constructs a {@code Ingredient} without quantity.
     *
     * @param ingredient A valid ingredients number.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        value = ingredient;
        this.quantity = "";
    }

    public Ingredient() {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value.trim();
    }

    public String getQuantity() {
        return quantity.trim();
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
                && otherIngredient.getValue().equals(getValue())
                && otherIngredient.getQuantity().equals(getQuantity());
    }

    /**
     * Returns true if a given string is a valid Ingredient.
     */
    public static boolean isValidIngredient(Ingredient test) {
        return test.getValue().matches(VALIDATION_REGEX)
                && test.getQuantity().matches(VALIDATION_REGEX_QUANTITY);
    }

    /**
     * Parses the Ingredient Object into string in the command format.
     * @return String
     */
    public String parseToString() {
        if (quantity != "") {
            return value + " -" + quantity;
        } else {
            return value;
        }
    }

    /**
     * Get the command format of an Ingredient object.
     * @param position index of ingredient in the ingredient list.
     * @return String
     */
    public String stringify(int position) {
        String commandWord = EditIngredientCommand.COMMAND_WORD;
        String ingredientValue = PREFIX_INGREDIENT + getValue();
        String quantity = PREFIX_QUANTITY + getQuantity();
        return commandWord + " " + position + " " + ingredientValue + " " + quantity;
    }

    @Override
    public String toString() {
        String ingredientString = "";
        if (quantity != "") {
            ingredientString = quantity + " ";
        }
        ingredientString += value;
        return ingredientString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && value.equals(((Ingredient) other).getValue()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, quantity);
    }

}
