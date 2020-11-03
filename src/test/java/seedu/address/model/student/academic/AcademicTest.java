package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.StudentBuilder.DEFAULT_EXAM_FYE;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_MATH;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.academic.question.SolvedQuestion;
import seedu.address.model.student.academic.question.UnsolvedQuestion;
import seedu.address.testutil.StudentBuilder;

public class AcademicTest {

    @Test
    public void equals() {
        Academic aliceAcademic = ALICE.getAcademic();

        // same values should return true
        Academic academicCopy = new StudentBuilder(ALICE).build().getAcademic();
        assertTrue(aliceAcademic.equals(academicCopy));

        // same object return true
        assertTrue(aliceAcademic.equals(aliceAcademic));

        // null returns false
        assertFalse(aliceAcademic.equals(null));

        // different types should return false
        assertFalse(aliceAcademic.equals(6));

        // different attendance list returns false
        Academic editedAliceAcademic = new StudentBuilder(ALICE).withAttendances(new Attendance("12/02/2020",
                "present", new Feedback("sleeping"))).build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));

        // different exam list returns false
        editedAliceAcademic = new StudentBuilder(ALICE).withExams(DEFAULT_EXAM_FYE).build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));

        // different question list returns false
        editedAliceAcademic = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        assertFalse(aliceAcademic.equals(editedAliceAcademic));
    }

    @Test
    public void containsQuestion() {
        String[] questions = new String[] {DEFAULT_QUESTION_MATH, DEFAULT_QUESTION_NEWTON};
        Academic aliceAcademic = ALICE.getAcademic();
        for (String question : questions) {
            assertTrue(aliceAcademic.containsQuestion(new UnsolvedQuestion(question)));
            assertTrue(aliceAcademic.containsQuestion(new SolvedQuestion(question, DEFAULT_SOLUTION)));
        }
    }

    @Test
    public void addQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.addQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Academic expected = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }

    @Test
    public void setQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.setQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH),
                new SolvedQuestion(DEFAULT_QUESTION_MATH, DEFAULT_SOLUTION));
        Academic expected = new StudentBuilder(ALICE).withSolved(DEFAULT_SOLUTION, DEFAULT_QUESTION_MATH)
                .build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }

    @Test
    public void deleteQuestion() {
        Academic aliceAcademic = new StudentBuilder(ALICE).withQuestions(DEFAULT_QUESTION_MATH).build().getAcademic();
        Academic acadWithQuestion = aliceAcademic.deleteQuestion(new UnsolvedQuestion(DEFAULT_QUESTION_MATH));
        Academic expected = new StudentBuilder(ALICE).withQuestions().build().getAcademic();
        assertEquals(expected, acadWithQuestion);
    }
}
