package seedu.address.model.client;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.time.format.DateTimeParseException;

/**
 * Represents the instant the client was modified.
 * Guarantees: immutable; is valid as declared in {@link #isValidInstant(String)}
 */
public class LastModifiedInstant implements Comparable<LastModifiedInstant> {

    public final Instant value;

    /**
     * Constructs an {@code LastModifiedInstant}.
     * This is used when client is added or edited.
     */
    public LastModifiedInstant() {
        this.value = Instant.now();
    }

    /**
     * Constructs an {@code LastModifiedInstant}.
     * This is mainly used to construct an instant from storage.
     *
     * @param instant A valid instant string.
     */
    public LastModifiedInstant(String instant) {
        requireNonNull(instant);
        if (!isValidInstant(instant)) {
            this.value = Instant.now();
        } else {
            this.value = Instant.parse(instant);
        }
    }

    /**
     * Returns true if the String is a valid instant.
     */
    public static boolean isValidInstant(String test) {
        try {
            Instant.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LastModifiedInstant // instanceof handles nulls
                && value.equals(((LastModifiedInstant) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return ISO_INSTANT.format(value);
    }

    @Override
    public int compareTo(LastModifiedInstant other) {
        return other.value.compareTo(value);
    }
}
