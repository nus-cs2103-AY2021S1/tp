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
                    "1. Ingredient quantity should only consist of alphanumeric characters, a single full stop "
                    + "or a single forward slash.\n"
                    + "2. Ingredient quantity should be in format -(NUMBER)(STRING) e.g. -54.0 kilograms or "
                    + "-STRING e.g. -a pinch where NUMBER only accept "
                    + "up to 10 numbers, including a single forward slash to represent fractions or a single "
                    + "full stop to represent decimal numbers or trailing whitespaces and STRING accepts "
                    + "alphabets. \n"
                    + "3. Ingredient quantity should be a number greater than 0.";
    public static final String HYPHEN_CONSTRAINTS = "Each ingredient has an optional field quantity that is "
            + "separated by a spaced followed by a hyphen.";

    /**
     * The first character of the ingredient must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ][\\p{Alnum} ]*";
    public static final String VALIDATION_REGEX_QUANTITY = "[\\p{Alnum}\\/\\. ]*";
    public static final String VALIDATION_REGEX_STRING = "[\\p{Alpha} ]*";
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
     * Returns true if both ingredients have the same name and quantity.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }
        return getValue().equals(otherIngredient.getValue()) && otherIngredient.getQuantity().equals
         (getQuantity());
    }

    /**
     * Returns true if both ingredients have the same name.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameIngredientName(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getValue().toLowerCase().equals(getValue().toLowerCase());
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
        int maxLengthOfDigits = 10;
        int fullStopIndex = quantity.indexOf(".");
        int forwardSlashIndex = quantity.indexOf("/");

        //Presence of 1 decimal point and 1 forward slash
        if (fullStopIndex != -1 && forwardSlashIndex != -1) {
            return false;
        }

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

        boolean isGreaterThanZero = StringUtil.isNonZeroUnsignedFloat(digits);
        boolean isWithinMaxLength = digits.length() <= maxLengthOfDigits;
        boolean isDigitValid = digits.equals("") || (isGreaterThanZero && isWithinMaxLength);

        return isDigitValid && units.matches(VALIDATION_REGEX_STRING);
    }

    /**
     * Removes leading zeroes from integer, decimals and fractions.
     * @param quantity
     * @return String trimmed and without leading zeroes
     */
    public static String removeLeadingZeroesFromNumber(String quantity) {
        String [] digitsAndUnits = getDigitsAndUnitsFromQuantity(quantity);
        assert digitsAndUnits != null;
        String digits = digitsAndUnits[0];
        String units = digitsAndUnits[1];
        int indexOfDecimalPoint = digits.indexOf(".");
        int indexOfDivide = digits.indexOf("/");
        int size = digits.length();
        if (indexOfDivide != -1) {
            String numerator = digits.substring(0, indexOfDivide);
            String denominator = digits.substring(indexOfDivide + 1);
            String trimmedDigits =
                    removeLeadingZeroesFromInteger(numerator) + "/" + removeLeadingZeroesFromInteger(denominator);
            return trimmedDigits + units;
        }
        int index = 0;
        for (int i = 0; i < size; i++) {
            char c = digits.charAt(i);
            if (c == '0') {
                index++;
            } else {
                break;
            }
        }
        boolean hasZeroOnLeftOfDecimalPoint = indexOfDecimalPoint != -1 && indexOfDecimalPoint == index;
        if (index == 0) {
            return quantity;
        } else if (hasZeroOnLeftOfDecimalPoint) {
            return digits.substring(index - 1) + units;
        } else {
            return digits.substring(index) + units;
        }
    }

    private static String removeLeadingZeroesFromInteger(String digits) {
        int size = digits.length();
        int index = 0;
        for (int i = 0; i < size; i++) {
            char c = digits.charAt(i);
            if (c == '0') {
                index++;
            } else {
                break;
            }
        }
        return digits.substring(index);
    }

    private static String[] getDigitsAndUnitsFromQuantity(String quantity) {
        StringBuilder value = new StringBuilder();
        StringBuilder units = new StringBuilder();
        boolean hasEncounteredUnits = false;
        for (int i = 0; i < quantity.length(); i++) {
            char c = quantity.charAt(i);
            boolean isStringFormat = !Character.isDigit(c) && !Character.isWhitespace(c) && c != '.' && c != '/';

            if (hasEncounteredUnits) {
                //Digit in STRING area
                if (Character.isDigit(c)) {
                    return null;
                }
                units.append(c);
                //Quantity is in STRING format
            } else if (i == 0 && isStringFormat) {
                units.append(quantity);
                break;

            //Quantity is in NUMBER STRING format
            } else if (isStringFormat) {
                hasEncounteredUnits = true;
                units.append(c);
            } else { //is NUMBER format
                value.append(c);
            }
        }
        return new String[]{value.toString(), units.toString()};
    }

    /**
     * Parses the Ingredient Object into string in the command format.
     * @return String
     */
    public String parseToString() {
        if (!quantity.equals("")) {
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
        String ingredientString = value.trim();
        if (!quantity.isBlank()) {
            ingredientString += " (Quantity: " + quantity.trim() + ")";
        }
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
