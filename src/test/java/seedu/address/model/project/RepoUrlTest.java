package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RepoUrlTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RepoUrl(null));
    }

    @Test
    public void constructor_invalidRepoUrl_throwsIllegalArgumentException() {
        String invalidRepoUrl = "";
        assertThrows(IllegalArgumentException.class, () -> new RepoUrl(invalidRepoUrl));
    }

    @Test
    public void isValidRepoUrl() {
        // null repoUrl
        assertThrows(NullPointerException.class, () -> RepoUrl.isValidRepoUrl(null));

        // blank repoUrl
        assertFalse(RepoUrl.isValidRepoUrl("")); // empty string
        assertFalse(RepoUrl.isValidRepoUrl(" ")); // spaces only
        // missing hostname part
        assertFalse(RepoUrl.isValidRepoUrl("https://AY2021S1-CS2103T-W10-3/tp.git"));
        // missing .git part
        assertFalse(RepoUrl.isValidRepoUrl("https://github.com/AY2021S1-CS2103T-W10-3/tp"));
        // invalid protocol
        assertFalse(RepoUrl.isValidRepoUrl("www://google.com/AY2021S1-CS2103T-W10-3/tp.git"));
        // valid email
        assertTrue(RepoUrl.isValidRepoUrl("https://github.com/AY2021S1-CS2103T-W10-3/tp.git"));
        // hostname with @
        assertTrue(RepoUrl.isValidRepoUrl("https://username@bitbucket.org/otherusername/reponame.git"));
        // another url expression of git repo
        assertTrue(RepoUrl.isValidRepoUrl("git@github.com:username/reponame.git"));
    }
}
