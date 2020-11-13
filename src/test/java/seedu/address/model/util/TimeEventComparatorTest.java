package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDeliverables.HOME_PAGE;
import static seedu.address.testutil.TypicalDeliverables.NAVIGATION;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;

import org.junit.jupiter.api.Test;

class TimeEventComparatorTest {

    private TimeEventComparator timeEventComparator = new TimeEventComparator();

    @Test
    void compare_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timeEventComparator.compare(MEETING_A, null));
    }

    @Test
    void compare_nullDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> timeEventComparator.compare(HOME_PAGE, null));
    }

    @Test
    void compare_success() {
        // MEETING_A starts after MEETING_B
        assertTrue(timeEventComparator.compare(MEETING_A, MEETING_B) > 0);
        assertTrue(timeEventComparator.compare(MEETING_B, MEETING_A) < 0);

        // NAVIGATION is due after HOME_PAGE
        assertTrue(timeEventComparator.compare(NAVIGATION, HOME_PAGE) > 0);
        assertTrue(timeEventComparator.compare(HOME_PAGE, NAVIGATION) < 0);

        // Equal priority
        assertEquals(timeEventComparator.compare(MEETING_A, MEETING_A), 0);
        assertEquals(timeEventComparator.compare(HOME_PAGE, HOME_PAGE), 0);
    }
}
