package seedu.address.model.log;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Log's phone number in the address book.
 * Guarantees: immutable.
 */
public class Comment {
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
