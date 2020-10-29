package seedu.taskmaster.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a session conducted by a teaching assistant.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class SessionName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String SESSION_NOT_FOUND_NAME = "SESSION_NOT_FOUND";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code SessionName}.
     *
     * @param name A valid name.
     */
    public SessionName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static SessionName getSessionNotFoundName() {
        return new SessionName(SESSION_NOT_FOUND_NAME);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionName // instanceof handles nulls
                && name.equals(((SessionName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
