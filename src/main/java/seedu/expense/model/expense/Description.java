package seedu.expense.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.core.Messages.MESSAGE_INPUT_OVERLIMIT;
import static seedu.expense.commons.util.AppUtil.checkArgument;

/**
 * Represents an Expense's description in the expense book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final int MAX_CHARACTERS = 200;
    public static final String CLASS_NAME = "Description";
    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions can take any values, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(description), String.format(MESSAGE_INPUT_OVERLIMIT,
            CLASS_NAME, MAX_CHARACTERS));
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is of valid length.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_CHARACTERS;
    }


    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.equals(((Description) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
