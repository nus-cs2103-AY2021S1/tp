package seedu.address.model.student.academic.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExamTest {
    static final String VALID_EXAM_NAME = "CA2";
    static final String VALID_EXAM_DATE_DEF = "13/03/2020";
    static final String VALID_EXAM_DATE_ALT = "13/3/20";
    static final String INVALID_EXAM_DATE_FORMAT = "2020-02-12";
    static final String INVALID_EXAM_DATE = "31/02/2020";
    static final String VALID_SCORE = "25/50";
    static final Exam VALID_EXAM = new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_DEF,
            new Score(VALID_SCORE));

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All fields null
        assertThrows(NullPointerException.class, () -> new Exam(null, null,
                new Score(null)));

        // Only one non-null
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, null,
                new Score(null)));
        assertThrows(NullPointerException.class, () -> new Exam(null, VALID_EXAM_DATE_DEF,
                new Score(null)));
        assertThrows(NullPointerException.class, () -> new Exam(null, null,
                new Score(VALID_SCORE)));

        // Only one null
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_DEF,
                new Score(null)));
        assertThrows(NullPointerException.class, () -> new Exam(null, VALID_EXAM_DATE_DEF,
                new Score(VALID_SCORE)));
        assertThrows(NullPointerException.class, () -> new Exam(VALID_EXAM_NAME, null,
                new Score(VALID_SCORE)));
    }

    @Test
    public void isValidDate_test() {
        // null Date should throw exception
        assertThrows(NullPointerException.class, () -> Exam.isValidDate(null));

        // emptyDate is invalid
        assertFalse(Exam.isValidDate(" "));

        // Invalid format
        assertFalse(Exam.isValidDate(INVALID_EXAM_DATE_FORMAT));
        assertFalse(Exam.isValidDate(INVALID_EXAM_DATE));

        // valid format date
        assertTrue(Exam.isValidDate(VALID_EXAM_DATE_DEF));
        assertTrue(Exam.isValidDate(VALID_EXAM_DATE_ALT));
    }

    @Test
    public void equals_test() {
        // different object
        assertNotEquals(VALID_EXAM, "not an exam");

        // diff fields - name is the only field that differentiates exams
        assertNotEquals(VALID_EXAM, new Exam("Mid Terms 19/20", VALID_EXAM_DATE_DEF,
                new Score(VALID_SCORE)));

        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, "25/12/2020",
                new Score(VALID_SCORE)));
        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_ALT,
                new Score("10/100")));

        // same object
        assertEquals(VALID_EXAM, VALID_EXAM);

        // same fields
        assertEquals(VALID_EXAM, new Exam(VALID_EXAM_NAME, VALID_EXAM_DATE_ALT,
                new Score(VALID_SCORE)));
    }
}
