package seedu.address.model.patient;

import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProfilePictureTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProfilePicture(null));
    }

    @Test
    public void isValidFilePath() {
        // null file path
        assertThrows(NullPointerException.class, () -> ProfilePicture.isValidFilePath(null));
    }

    @Test
    public void toStringTest() {
        ProfilePicture profilePicture = new ProfilePicture("data/pictures");
        String filePath = profilePicture.toString();
        assertEquals("data/pictures", filePath);
    }
}

