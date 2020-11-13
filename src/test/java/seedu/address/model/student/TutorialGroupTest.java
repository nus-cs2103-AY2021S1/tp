package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;
import seedu.address.testutil.TutorialGroupBuilder;
import seedu.address.testutil.TypicalStudents;

public class TutorialGroupTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialGroup(null, null,
            null, null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidTutorialGroupId = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup(
            new TutorialGroupId(invalidTutorialGroupId), new DayOfWeek("MON"), new TimeOfDay("16:00"),
            new TimeOfDay("18:00")));
    }

    @Test
    public void constructor_invalidTutorialTimes_throwsIllegalArgumentException() {
        TimeOfDay startTime = new TimeOfDay("19:00");
        TimeOfDay endTime = new TimeOfDay("17:00"); //endTime earler than startTime
        assertThrows(AssertionError.class, () -> new TutorialGroup(
            new TutorialGroupId("T04"), new DayOfWeek("MON"), startTime,
            endTime));
    }

    @Test
    public void getTotalStudents_validTotalStudents_success() {
        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
        tutorialGroup.addStudent(TypicalStudents.ALEX);
        tutorialGroup.addStudent(TypicalStudents.BENG);
        assertTrue(tutorialGroup.getTotalStudents() == 2);
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> TutorialGroupId.isValidTutorialGroupId(null));

        // invalid addresses
        assertFalse(TutorialGroupId.isValidTutorialGroupId("")); // empty string
        assertFalse(TutorialGroupId.isValidTutorialGroupId(" ")); // spaces only

        // valid addresses
        assertTrue(TutorialGroupId.isValidTutorialGroupId("T04"));
        assertFalse(TutorialGroupId.isValidTutorialGroupId("-")); // one character
        assertTrue(TutorialGroupId.isValidTutorialGroupId("T000005")); // long address
    }
}
