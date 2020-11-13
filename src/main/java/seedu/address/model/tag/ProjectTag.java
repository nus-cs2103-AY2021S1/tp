package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ProjectTag in the main catalogue.
 * Guarantees: immutable; name is valid as declared in {@link #isValidProjectTagName(String)}
 */
public class ProjectTag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String projectTagName;

    /**
     * Constructs a {@code ProjectTag}.
     *
     * @param projectTagName A valid project tag name.
     */
    public ProjectTag(String projectTagName) {
        requireNonNull(projectTagName);
        checkArgument(isValidProjectTagName(projectTagName), MESSAGE_CONSTRAINTS);
        this.projectTagName = projectTagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidProjectTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectTag // instanceof handles nulls
                && projectTagName.equals(((ProjectTag) other).projectTagName)); // state check
    }

    @Override
    public int hashCode() {
        return projectTagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + projectTagName + ']';
    }

}
