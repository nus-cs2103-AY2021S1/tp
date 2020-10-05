package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

public class AdditionalDetails {
    public final String details;
    /**
     * Constructs a {@code Name}.
     *
     * @param details A valid details.
     */
    public AdditionalDetails(String details) {
        requireNonNull(details);
        this.details = details;
    }

    @Override
    public String toString() {
        return details;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdditionalDetails // instanceof handles nulls
                && details.equals(((AdditionalDetails) other).details)); // state check
    }

    @Override
    public int hashCode() {
        return details.hashCode();
    }
}
