package seedu.studybananas.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.studybananas.commons.util.AppUtil.checkArgument;

public class Title {
    public static final String MESSAGE_CONSTRAINTS =
            "Task title should only contain alphanumeric characters and spaces, and it should not be blank"
                    + "\n(The length of the title has to be less than 40 characters.";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Graph}][\\p{Graph} ]*";

    public final String title;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        requireNonNull(title);
        assert title != null;
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 40;
    }

    public boolean rigorousEquals(Title other) {
        return this.title.toLowerCase().equals(other.title.toLowerCase());
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && title.equals(((Title) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
