package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL_DOCUMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTORIAL_DOCUMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.PROJECT_DOCUMENT;
import static seedu.address.testutil.TypicalLinks.PROJECT_MEETING;
import static seedu.address.testutil.TypicalLinks.TUTORIAL_DOCUMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CollaborativeLinkBuilder;

public class CollaborativeLinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CollaborativeLink(null, null));
    }

    @Test
    public void constructor_invalidUrl_throwsIllegalArgumentException() {
        String invalidUrl = "example.com";
        assertThrows(IllegalArgumentException.class, () -> new CollaborativeLink(VALID_DESCRIPTION_TUTORIAL_DOCUMENT,
                invalidUrl));
    }

    @Test
    public void equals() {
        // same values -> returns true
        CollaborativeLink projectMeetingCopy = new CollaborativeLinkBuilder(PROJECT_DOCUMENT).build();
        assertTrue(PROJECT_DOCUMENT.equals(projectMeetingCopy));

        // same object -> returns true
        assertTrue(PROJECT_DOCUMENT.equals(PROJECT_DOCUMENT));

        // null -> returns false
        assertFalse(PROJECT_DOCUMENT.equals(null));

        // different type -> returns false
        assertFalse(PROJECT_DOCUMENT.equals(5));

        assertFalse(PROJECT_DOCUMENT.equals(PROJECT_MEETING));

        // different person -> returns false
        assertFalse(PROJECT_DOCUMENT.equals(TUTORIAL_DOCUMENT));

        // different description -> returns false
        CollaborativeLink editedProjectMeeting = new CollaborativeLinkBuilder(PROJECT_DOCUMENT)
                .withDescription(VALID_DESCRIPTION_TUTORIAL_DOCUMENT).build();
        assertFalse(PROJECT_DOCUMENT.equals(editedProjectMeeting));


        // different URL -> returns false
        editedProjectMeeting = new CollaborativeLinkBuilder(PROJECT_DOCUMENT)
                .withUrl(VALID_URL_TUTORIAL_DOCUMENT).build();
        assertFalse(PROJECT_DOCUMENT.equals(editedProjectMeeting));
    }
}
