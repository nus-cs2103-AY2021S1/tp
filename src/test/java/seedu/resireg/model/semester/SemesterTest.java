package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_1;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_2;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.SemesterBuilder;

class SemesterTest {
    public static final int VALID_ACADEMIC_YEAR_2020 = 2020;
    public static final int VALID_ACADEMIC_YEAR_2021 = 2021;
    public static final int INVALID_ACADEMIC_YEAR = 1965;
    public static final int VALID_SEMESTER_NUMBER_ONE = 1;
    public static final int VALID_SEMESTER_NUMBER_TWO = 2;
    public static final int INVALID_SEMESTER_NUMBER = 0;

    @Test
    public void constructor_emptyConstructor_returnsCurrentYearFirstSemester() {
        Semester semester = new Semester();

        assertEquals(semester.getAcademicYear(), LocalDate.now().getYear());
        assertEquals(semester.getSemesterNumber(), VALID_SEMESTER_NUMBER_ONE);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(AY2020_SEM_1, AY2020_SEM_1);

        // not Semester instance -> returns false
        assertNotEquals(AY2020_SEM_1, "Not a semester");
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
        assertTrue(ay2020Sem1ShortRepresentation.startsWith("AY"));
        assertTrue(ay2020Sem1ShortRepresentation.endsWith("1"));

        String ay2020Sem2ShortRepresentation = AY2020_SEM_2.getShortRepresentation();
        assertEquals(ay2020Sem2ShortRepresentation, "AY2020S2");

        // not equals
        assertNotEquals(AY2020_SEM_1.getShortRepresentation(), "invalid string");
        assertNotEquals(AY2020_SEM_1.getShortRepresentation(), "invalid string");
    }

    @Test
    void getNextSemester() {
        Semester semester = new SemesterBuilder()
                .withAcademicYear(2021)
                .withSemesterNumber(1)
                .build();
        // should proceed to current year sem 2
        assertEquals(AY2020_SEM_1.getNextSemester(), AY2020_SEM_2);
        // should wrap over to next year sem 1
        assertEquals(AY2020_SEM_2.getNextSemester(), semester);

        // not equals
        assertNotEquals(AY2020_SEM_1.getNextSemester(), semester);
        assertNotEquals(AY2020_SEM_1.getNextSemester(), AY2020_SEM_1);
    }

    @Test
    void setAcademicYear() {
        Semester semester = new SemesterBuilder()
                .withAcademicYear(VALID_ACADEMIC_YEAR_2020)
                .withSemesterNumber(VALID_SEMESTER_NUMBER_ONE)
                .build();
        // Setting valid academic year should not throw
        assertDoesNotThrow(() -> semester.setAcademicYear(VALID_ACADEMIC_YEAR_2021));

        // Setting invalid academic year should throw Exception
        assertThrows(IllegalArgumentException.class, () -> semester.setAcademicYear(INVALID_ACADEMIC_YEAR));
    }

    @Test
    void setSemesterNumber() {
        Semester semester = new SemesterBuilder()
                .withAcademicYear(VALID_ACADEMIC_YEAR_2020)
                .withSemesterNumber(VALID_SEMESTER_NUMBER_ONE)
                .build();
        // Setting valid semester number should not throw
        assertDoesNotThrow(() -> semester.setSemesterNumber(VALID_SEMESTER_NUMBER_TWO));

        // Setting invalid semester number should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> semester.setSemesterNumber(INVALID_SEMESTER_NUMBER));
    }
}
