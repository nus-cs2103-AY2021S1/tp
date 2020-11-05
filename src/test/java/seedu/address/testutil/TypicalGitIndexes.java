package seedu.address.testutil;

import static seedu.address.logic.commands.TeammateTestUtil.INVALID_TEAMMATE_GIT_USERNAME;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_B;

import seedu.address.commons.core.index.GitUserIndex;

/**
 * A utility class containing a list of {@code GitUserIndex} objects to be used in tests.
 */
public class TypicalGitIndexes {
    public static final GitUserIndex GIT_USERINDEX_FIRST_TEAMMATE = new GitUserIndex(
        VALID_TEAMMATE_GIT_USERNAME_A);
    public static final GitUserIndex GIT_USERINDEX_SECOND_TEAMMATE = new GitUserIndex(
        VALID_TEAMMATE_GIT_USERNAME_B);
    public static final GitUserIndex GIT_USERINDEX_INVALID_TEAMMATE = new GitUserIndex(
        INVALID_TEAMMATE_GIT_USERNAME);
}
