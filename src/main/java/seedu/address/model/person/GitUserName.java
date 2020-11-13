package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's name in the main catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitUserName(String)}
 */
public class GitUserName {

    public static final String MESSAGE_CONSTRAINTS =
        "Git User Name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * Validation criteria for a Git UserName
     */

    // @@author GeNiaaz-reused
    // Reused and adapted from https://github.com/shinnn/github-username-regex

    public static final String VALIDATION_REGEX = "^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}$";

    // @@author

    public final String fullGitUserName;

    /**
     * Constructs a {@code GitUserName}.
     *
     * @param gitUserName A valid User's unique Git Username.
     */
    public GitUserName(String gitUserName) {
        requireNonNull(gitUserName);
        checkArgument(isValidGitUserName(gitUserName), MESSAGE_CONSTRAINTS);
        fullGitUserName = gitUserName;
    }

    /**
     * Returns true if a given string is a valid gitUserName.
     */
    public static boolean isValidGitUserName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullGitUserName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof GitUserName // instanceof handles nulls
            && fullGitUserName.equals(((GitUserName) other).fullGitUserName)); // state check
    }

    @Override
    public int hashCode() {
        return fullGitUserName.hashCode();
    }

}
