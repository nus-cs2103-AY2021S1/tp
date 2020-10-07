package seedu.address.model.log;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Log's phone number in the log book.
 * Guarantees: immutable.
 */
public class Comment {
    public final String value;

    //TODO: Find a stricter constraint for comments
    public static final String MESSAGE_CONSTRAINTS =
            "Comment should not be black";

    /**
     * Constructs a {@code Comment}.
     *
     * @param comment A comment about the exercise.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        value = comment;
    }

    //TODO: write this according to comments' constraints
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
