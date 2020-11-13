package seedu.jarvis.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's telegram username in the jarvis book.
 */
public class Telegram {
    public final String username;

    /**
     * Constructs a {@code Phone}.
     *
     * @param username A valid phone number.
     */
    public Telegram(String username) {
        requireNonNull(username);
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && username.equals(((Telegram) other).username)); // state check
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

}
