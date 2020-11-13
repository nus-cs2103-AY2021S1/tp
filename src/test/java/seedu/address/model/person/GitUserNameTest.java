package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitUserNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitUserName(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String projectDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new GitUserName(projectDescription));
    }

    @Test
    public void isValidAddress() {
        // null gitUserName
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid gitUserName
        assertFalse(GitUserName.isValidGitUserName("")); // empty string
        assertFalse(GitUserName.isValidGitUserName(" ")); // space only
        assertFalse(GitUserName.isValidGitUserName("-GeNiaaz")); // hyphen before
        assertFalse(GitUserName.isValidGitUserName("Lucas Ku")); // space in name
        assertFalse(GitUserName.isValidGitUserName("Lucas--de")); // consecutive hyphen
        assertFalse(GitUserName.isValidGitUserName("thisisthebestnameever1111111111111111111")); // long name > 40

        // valid gitUserName
        assertTrue(GitUserName.isValidGitUserName("D3stiny")); // alphanumeric name
        assertTrue(GitUserName.isValidGitUserName("Lucas-Tai-21")); // name with hyphens
        assertTrue(GitUserName.isValidGitUserName("thisisthebestnameever111111111111111111")); // 40 char name
    }


}
