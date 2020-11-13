package seedu.jarvis.model.login;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.commons.util.AppUtil.checkArgument;

/**
 * Represents a UserLogin's username in the login.json.
 * Guarantees: immutable
 */
public class Username {
    public static final String MESSAGE_CONSTRAINTS =
            "Username invalid, it has to be the same as your Luminus user login i.d. with the format 'nusstu' "
                    + "followed by a backslash and your NusNetId 'e1234567'";

    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]+\\\\[a-zA-Z0-9]+$";

    private final String username;

    /**
     * Constructs an empty username object.
     */
    public Username() {
        this.username = "";
    }

    /**
     * Constructs a {@code Name}.
     *
     * @param username A valid name.
     */
    public Username(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_CONSTRAINTS);
        this.username = username;
    }

    /**
     * Returns true if a given string is a valid username.
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return username.isEmpty();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Username // instanceof handles nulls
                && username.equals(((Username) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
