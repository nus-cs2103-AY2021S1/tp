package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.Objects;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;

/**
 * Represents a Recipe's ingredients in the Wishful Shrinking.
 */
public class Ingredient {
    public static final String MESSAGE_CONSTRAINTS =
            "1. Ingredient name is compulsory and should consist of only alphanumeric characters.\n"
                    + "2. Each ingredient is separated by a comma.\n"
                    + "3. Each ingredient has an optional field quantity that is separated by a spaced followed "
                    + "by a hyphen.\n"
                    + "4. Ingredient quantity should only consist of alphanumeric characters, a single full stop"
                    + " or a single forward slash.\n"
                    + "5. Ingredient quantity should be a number greater than 0.";
    public static final String QUANTITY_CONSTRAINTS =
            "1. Ingredient quantity should be in format -NUMBER UNITS\n"
                    + "2. Ingredient quantity should only consist of alphanumeric characters, a single full stop\n"
                    + "or a single forward slash.\n"
                    + "3. Ingredient quantity should be a number greater than 0 and is only accurate up to 45 "
                    + "decimal places.";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ][\\p{Alnum} ]*";
    public static final String VALIDATION_REGEX_QUANTITY = "[\\p{Alnum}\\/\\. ]*";
    public static final String VALIDATION_REGEX_UNITS = "[\\p{Alnum} ]*";
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
                && test.getQuantity().matches(VALIDATION_REGEX_QUANTITY)
                && isValidQuantity(test.getQuantity());
    }

    /**
     * Returns true if a given string is a valid quantity.
     */
    public static boolean isValidQuantity(String quantity) {
        int fullStopIndex = quantity.indexOf(".");
        int forwardSlashIndex = quantity.indexOf("/");

        //More than one occurence of decimal point or forward slash
        if (fullStopIndex != quantity.lastIndexOf(".")
            || forwardSlashIndex != quantity.lastIndexOf("/")) {
            return false;
        }

        String [] digitsAndUnits = getDigitsAndUnitsFromQuantity(quantity);
        if (digitsAndUnits == null) {
            return false;
        }
        String digits = digitsAndUnits[0];
        String units = digitsAndUnits[1];
        return ((!digits.equals("") && StringUtil.isNonZeroUnsignedFloat(digits)) || digits.equals(""))
                && units.matches(VALIDATION_REGEX_UNITS);
    }

    private static String[] getDigitsAndUnitsFromQuantity(String quantity) {
        StringBuilder value = new StringBuilder();
        StringBuilder units = new StringBuilder();
        boolean hasEncounteredUnits = false;
        for (int i = 0; i < quantity.length(); i++) {
            char c = quantity.charAt(i);
            if (hasEncounteredUnits) {
                units.append(c);
                //Quantity is in STRING format
            } else if (i == 0 && !Character.isDigit(c) && !Character.isWhitespace(c)) {
                units.append(quantity);
                break;

            //Quantity is in NUMBER UNITS format
            } else if (!Character.isDigit(c) && !Character.isWhitespace(c) && c != '.' && c != '/') {
                hasEncounteredUnits = true;
                units.append(c);
            } else if (Character.isDigit(c) || Character.isWhitespace(c) || c == '.' || c == '/') {
                value.append(c);
            } else {
                //should never reach here
                return null;
            }
        }
        return new String[]{value.toString().trim(), units.toString().trim()};
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
        String ingredient = PREFIX_INGREDIENT + parseToString();
        return commandWord + " " + position + " " + ingredient;
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
