package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.TutorialGroupId;

public class TutorialGroupIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialGroupId(null));
    }

    @Test
    public void constructor_invalidTutorialGroupId_throwsIllegalArgumentException() {
        String invalidName = "@";
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroupId(invalidName));
    }

    @Test
    public void isValidTutorialGroupId() {
        // null name
        assertThrows(NullPointerException.class, () -> TutorialGroupId.isValidTutorialGroupId(null));

        // invalid name
        assertFalse(TutorialGroupId.isValidTutorialGroupId("")); // empty string
        assertFalse(TutorialGroupId.isValidTutorialGroupId(" ")); // spaces only
        assertFalse(TutorialGroupId.isValidTutorialGroupId("^")); // only non-alphanumeric characters
        assertFalse(TutorialGroupId.isValidTutorialGroupId("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TutorialGroupId.isValidTutorialGroupId("tut")); // alphabets only
        assertTrue(TutorialGroupId.isValidTutorialGroupId("123")); // numbers only
        assertTrue(TutorialGroupId.isValidTutorialGroupId("2nd fav tut")); // alphanumeric characters
        assertTrue(TutorialGroupId.isValidTutorialGroupId("T04")); // with capital letters
        assertTrue(TutorialGroupId.isValidTutorialGroupId("Prof Mi Pan Zu Zu Tutorial")); // long names
    }
}
