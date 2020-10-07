package seedu.address.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a MCQ choice.
 */
public class Choice {

    public static final String MESSAGE_CONSTRAINTS =
            "Choices should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String choice;

    public Choice(String choice) {
        requireNonNull(choice);
        checkArgument(isValidChoice(choice), MESSAGE_CONSTRAINTS);
        this.choice = choice;
    }

    public String getContent() {
        return choice;
    }

    public static boolean isValidChoice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return choice;
    }

    /**
     * Compares this choice to the specified object.
     * The result is true if and only if the argument is not null and is a
     * Choice object that has the same contents as this object
     * @param o the object to compare this Choice against
     * @return {@code true} if the given object represents a Choice equivalent to this choice, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Choice) {
            Choice other = (Choice) o;
            return choice.equals(other.choice);
        } else {
            return false;
        }
    }
}
