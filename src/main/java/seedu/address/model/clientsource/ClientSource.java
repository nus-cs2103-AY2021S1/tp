package seedu.address.model.clientsource;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ClientSource in the client list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidClientSourceName(String)}
 */
public class ClientSource {

    public static final String MESSAGE_CONSTRAINTS = "Client sources can take any values,"
            + " should not be more than 50 characters,"
            + " and should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].{0,49}";

    public final String clientSourceName;

    /**
     * Constructs a {@code ClientSource}.
     *
     * @param clientSourceName A valid clientsource name.
     */
    public ClientSource(String clientSourceName) {
        requireNonNull(clientSourceName);
        checkArgument(isValidClientSourceName(clientSourceName), MESSAGE_CONSTRAINTS);
        this.clientSourceName = clientSourceName;
    }

    /**
     * Returns true if a given string is a valid clientsource name.
     */
    public static boolean isValidClientSourceName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientSource // instanceof handles nulls
                && clientSourceName.equals(((ClientSource) other).clientSourceName)); // state check
    }

    @Override
    public int hashCode() {
        return clientSourceName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + clientSourceName + ']';
    }

}
