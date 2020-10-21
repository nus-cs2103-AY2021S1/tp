package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Patient's profile picture in the CliniCal application.
 * Guarantees: immutable; is valid as declared in {@link #isValidFilePath(String)}
 */
public class ProfilePicture {
    public static final String MESSAGE_CONSTRAINTS = "File path should not be blank or null.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code profile picture}.
     *
     * @param filePath A valid file path that leads to the profile picture.
     */
    public ProfilePicture(String filePath) {
        assert filePath != "" : "File Path cannot be blank";
        requireNonNull(filePath);
        this.value = filePath;
    }

    /**
     * Returns true if a given file path is valid.
     */
    public static boolean isValidFilePath(String test) {
        assert test != "" : "File Path cannot be blank";
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ProfilePicture
                && value.equals(((ProfilePicture) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
