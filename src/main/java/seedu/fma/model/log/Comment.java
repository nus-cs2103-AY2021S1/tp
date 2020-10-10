package seedu.fma.model.log;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Log's phone number in the log book.
 * Guarantees: immutable.
 */
public class Comment {
    public static final String MESSAGE_CONSTRAINTS =
            "Comment should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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

    //TODO: write this according to comments' constraints
    public static boolean isValidComment(String comment) {
        return comment.matches(VALIDATION_REGEX);
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
