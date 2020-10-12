package seedu.fma.model.log;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Log's phone number in the log book.
 * Guarantees: immutable.
 */
public class Comment {
    public static final String MESSAGE_CONSTRAINTS = "Comment is not valid";

    public final String value;

    /**
     * Constructs a {@code Comment}.
     *
     * @param comment A comment about the exercise.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        value = comment;
    }

    //TODO: all Comments currently valid
    public static boolean isValidComment(String comment) {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && value.equals(((Comment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
