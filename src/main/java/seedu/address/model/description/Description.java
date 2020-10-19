package seedu.address.model.description;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^$|[\\p{Alnum}][\\p{Alnum} ]*";

    private String description;

    /**
     * Constructs a new Description for TagName.
     *
     * @param description Tag description
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns true if both description have the same identity and data fields.
     * This defines a stronger notion of equality between two descriptions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return otherDescription.getDescription().equals(getDescription());
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
