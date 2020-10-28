package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_1;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_2;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.SemesterBuilder;

class SemesterTest {

    @Test
    public void isSameSemester() {
        // same object -> returns true
        assertTrue(AY2020_SEM_1.isSameSemester(AY2020_SEM_1));

        // null -> returns false
        assertFalse(AY2020_SEM_1.isSameSemester(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(AY2020_SEM_1.isSameSemester(AY2020_SEM_1));

        // null -> returns false
        assertFalse(AY2020_SEM_1.isSameSemester(null));
    }

    @Test
    public void testToString() {
        // different semesters -> different string representations
        assertNotEquals(AY2020_SEM_1.toString(), AY2020_SEM_2.toString());
    }

    @Test
    void getShortRepresentation() {
        // equals
        String ay2020Sem1ShortRepresentation = AY2020_SEM_1.getShortRepresentation();
        assertTrue(ay2020Sem1ShortRepresentation.substring(0, 2).equals("AY"));
        assertTrue(ay2020Sem1ShortRepresentation.endsWith("1"));

        String ay2020Sem2ShortRepresentation = AY2020_SEM_2.getShortRepresentation();
        assertEquals(ay2020Sem2ShortRepresentation, "AY2020S2");

        // not equals
        assertNotEquals(AY2020_SEM_1.getShortRepresentation(), "invalid string");
        assertNotEquals(AY2020_SEM_1.getShortRepresentation(), "invalid string");
    }

    @Test
    void getNextSemester() {
        Semester nextSemester = new SemesterBuilder()
            .withAcademicYear(2021)
            .withSemesterNumber(1)
            .build();
        // should proceed to current year sem 2
        assertEquals(AY2020_SEM_1.getNextSemester(), AY2020_SEM_2);
        // should wrap over to next year sem 1
        assertEquals(AY2020_SEM_2.getNextSemester(), nextSemester);

        // not equals
        assertNotEquals(AY2020_SEM_1.getNextSemester(), nextSemester);
        assertNotEquals(AY2020_SEM_1.getNextSemester(), AY2020_SEM_1);
    }
}
