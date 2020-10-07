package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's repoURL in the main catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidRepoUrl(String)}
 */
public class RepoUrl {

    public static final String MESSAGE_CONSTRAINTS = "The URL must start with a protocol and "
            + "then followed by :// or @ and "
            + "then it must contain hostname and "
            + "then followed by username and "
            + "then followed by a repo name and "
            + "last part contains .git.";
    public static final String VALIDATION_REGEX = "^(([A-Za-z0-9]+@|http"
            + "(|s)\\:\\/\\/)|(http(|s)\\:\\/\\/[A-Za-z0-9]+@))"
            + "([A-Za-z0-9.]+(:\\d+)?)(?::|\\/)([\\d\\/\\w.-]+?)(\\.git){1}$";

    public final String value;

    /**
     * Constructs an {@code RepoUrl}.
     *
     * @param repoUrl A valid repoUrl address.
     */
    public RepoUrl(String repoUrl) {
        requireNonNull(repoUrl);
        checkArgument(isValidRepoUrl(repoUrl), MESSAGE_CONSTRAINTS);
        value = repoUrl;
    }

    /**
     * Returns if a given string is a valid repoUrl.
     */
    public static boolean isValidRepoUrl(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepoUrl // instanceof handles nulls
                && value.equals(((RepoUrl) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
