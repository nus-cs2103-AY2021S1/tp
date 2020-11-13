package seedu.jarvis.model.masterycheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.jarvis.testutil.StudentBuilder;


public class MasteryCheckTest {

    private String studentName = new StudentBuilder().build().getName().fullName;
    private String studentName2 = "StudentName";

    private MasteryCheck normalMasteryCheck = MasteryCheck.createFullMarkMC(studentName, LocalDateTime.of(2020,
            1, 8, 13, 10));
    private MasteryCheck masteryCheckVariant1 = MasteryCheck.createFullMarkMC(studentName2, LocalDateTime.of(2020,
            1, 8, 13, 10));
    private MasteryCheck masteryCheckVariant2 = MasteryCheck.createFullMarkMC(studentName, LocalDateTime.of(2020,
            1, 9, 13, 10));
    private MasteryCheck masteryCheckVariant3 = MasteryCheck.createZeroMarkMC(studentName, LocalDateTime.of(2020,
            1, 8, 13, 10));
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(normalMasteryCheck.equals(normalMasteryCheck));

        // null -> returns false
        assertFalse(normalMasteryCheck.equals(null));

        // different date -> returns false
        assertFalse(normalMasteryCheck.equals(masteryCheckVariant2));

        // different name -> returns false
        assertFalse(normalMasteryCheck.equals(masteryCheckVariant1));

        // different pass status -> returns true (still equal)
        assertTrue(normalMasteryCheck.equals(masteryCheckVariant3));
    }

    @Test
    public void conflictsWith() {
        // same dateandtime -> returns true
        assertTrue(normalMasteryCheck.conflictsWith(masteryCheckVariant1));

        // different dateandtime -> returns false
        assertFalse(normalMasteryCheck.conflictsWith(masteryCheckVariant2));

        // different pass status -> returns true
        assertTrue(normalMasteryCheck.conflictsWith(masteryCheckVariant3));
    }
}
