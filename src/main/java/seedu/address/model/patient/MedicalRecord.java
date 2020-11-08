package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's Medical Record in Hospify.
 * Guarantees: immutable; is valid as declared in {@link #isValidUrl(String)}
 */
public class MedicalRecord {

    public static final String MESSAGE_CONSTRAINTS = "Medical Record URL may start with http:// or https:// "
            + "or www. or the domain name (must be between 2 to 256 characters inclusive), "
            + "followed by the top level domain (must be between 2 to 6 characters inclusive), "
            + "followed by any path (if applicable)\n"
            + "Sample format: (http:// or https://)(www.)domain_name.com/path123";
    public static final String VALIDATION_REGEX = "((http|https)://)?(www.)?"
            + "[a-zA-Z0-9@:%._+~#?&=/]{2,256}\\.[a-z]{2,6}([-a-zA-Z0-9@:%._+~#?&=/]*)";

    public final String value;

    /**
     * Constructs an {@code MedicalRecord}.
     *
     * @param url A valid url.
     */
    public MedicalRecord(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        this.value = url;
    }

    /**
     * Returns true if a given string is a valid url.
     */
    public static boolean isValidUrl(String test) {
        return test.trim().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalRecord // instanceof handles nulls
                && value.equals(((MedicalRecord) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
