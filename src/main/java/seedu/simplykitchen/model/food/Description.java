package seedu.simplykitchen.model.food;

import static java.lang.Character.compare;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food's description in the Food inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces and it should not be blank.";
    public static final String MESSAGE_EXCEED_LIMIT = "Description should not exceed 50 characters.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}' ]*";

    public final String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), generateErrorMessage(description));
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        if (test.length() > 50) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares the first character of the given {@code description} object's full description
     * to the first character of the description, by the following defined ordering:
     *
     * The case-insensitive first characters are first ordered lexicographically.
     * Then, if one character is of upper case and the other character is of lower case,
     * and both case-insensitive characters are the same,
     * then the character of upper case is ordered after the character of lower case.
     * (A possible valid ordering for first characters: 'a', 'A', 'b', 'c', 'C').
     *
     * @return the value 0 if the characters are of equal precedence, 1 if the given description's first character
     * is of lower precedence, and -1 otherwise.
     */
    public int compareFirstCharacterTo(Description description) {
        Character character1 = this.fullDescription.charAt(0);
        Character character2 = description.fullDescription.charAt(0);
        int comparedValue = compare(toLowerCase(character1), toLowerCase(character2));
        if (comparedValue > 0 || (comparedValue == 0 && isUpperCase(character1) && isLowerCase(character2))) {
            return 1;
        } else if (comparedValue < 0 || (comparedValue == 0 && isLowerCase(character1) && isUpperCase(character2))) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Generates an error message based on why the description is invalid.
     *
     * @param invalidDescription An invalid description.
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String invalidDescription) {
        if (invalidDescription.length() > 50) {
            return MESSAGE_EXCEED_LIMIT;
        }
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.toLowerCase().equals(((Description) other).fullDescription.toLowerCase()));
        // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
