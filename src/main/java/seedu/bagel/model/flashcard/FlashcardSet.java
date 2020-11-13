package seedu.bagel.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.commons.util.AppUtil.checkArgument;

/**
 * Represents a Flashcard's set in Bagel.
 * Guarantees: immutable; is valid as declared in {@link #isValidSetNumber(String)}
 */
public class FlashcardSet {
    public static final String MESSAGE_CONSTRAINTS = "Set number should only contain numbers between 1 to 20.";
    public static final String VALIDATION_REGEX = "2[0]|1[0-9]|[1-9]";

    public final String value;

    /**
     * Constructs a {@code FlashcardSet}.
     *
     * @param setNumber A valid set number.
     */
    public FlashcardSet(String setNumber) {
        requireNonNull(setNumber);
        checkArgument(isValidSetNumber(setNumber), MESSAGE_CONSTRAINTS);
        value = setNumber;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidSetNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardSet // instanceof handles nulls
                && value.equals(((FlashcardSet) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
