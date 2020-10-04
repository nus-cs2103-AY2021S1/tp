package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's Description in the main catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidProjectDescription(String)}
 */
public class ProjectDescription {

    public static final String MESSAGE_CONSTRAINTS = "Project Descriptions can take any values, and it should not be " +
        "blank";

    /*
     * The first character of the Project Description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ProjectDescription}.
     *
     * @param projectDescription A valid projectDescription.
     */
    public ProjectDescription(String projectDescription) {
        requireNonNull(projectDescription);
        checkArgument(isValidProjectDescription(projectDescription), MESSAGE_CONSTRAINTS);
        value = projectDescription;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidProjectDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectDescription // instanceof handles nulls
                && value.equals(((ProjectDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
