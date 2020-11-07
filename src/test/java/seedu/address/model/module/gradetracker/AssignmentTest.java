package seedu.address.model.module.gradetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.LAB_1;
import static seedu.address.testutil.TypicalAssignments.QUIZ_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        AssignmentBuilder assignmentBuilder = new AssignmentBuilder();

        // null AssignmentName -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> assignmentBuilder.withAssignmentName(null).build());
    }

    @Test
    public void constructorNameOnly_nullArguments_throwsNullPointerException() {
        // null AssignmentName -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> new Assignment(null));
    }

    @Test
    public void setAssignmentValues() {
        Assignment testAssignmentConstructor = new AssignmentBuilder().build();
        AssignmentName quizName = new AssignmentName(VALID_ASSIGNMENT_NAME_1);
        AssignmentPercentage quizPercentage = new AssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1);
        AssignmentResult quizResult = new AssignmentResult(VALID_ASSIGNMENT_RESULT_1);

        // check setAssignmentName
        testAssignmentConstructor.setAssignmentName(quizName);
        assertEquals(testAssignmentConstructor.getAssignmentName().get(), quizName);

        //check setAssignmentPercentage
        testAssignmentConstructor.setAssignmentPercentage(quizPercentage);
        assertEquals(testAssignmentConstructor.getAssignmentPercentage().get(), quizPercentage);

        //check setAssignmentResult
        testAssignmentConstructor.setAssignmentResult(quizResult);
        assertEquals(testAssignmentConstructor.getAssignmentResult().get(), quizResult);
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(QUIZ_2.isSameAssignment(QUIZ_2));

        // null -> returns false
        assertFalse(QUIZ_2.isSameAssignment(null));

        // different assignment name -> returns false
        Assignment editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentName(VALID_ASSIGNMENT_NAME_2).build();
        assertFalse(QUIZ_2.isSameAssignment(editedQuiz2));

        // same name but different assignment percentage -> returns true
        editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2).build();
        assertTrue(QUIZ_2.isSameAssignment(editedQuiz2));

        // same name but different assignment result -> returns true
        editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();
        assertTrue(QUIZ_2.isSameAssignment(editedQuiz2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment quiz2Copy = new AssignmentBuilder(QUIZ_2).build();
        assertEquals(QUIZ_2, quiz2Copy);

        // same object -> returns true
        assertEquals(QUIZ_2, QUIZ_2);

        // null -> returns false
        assertNotEquals(null, QUIZ_2);

        // different type -> returns false
        assertNotEquals(10, QUIZ_2);

        // different person -> returns false
        assertNotEquals(QUIZ_2, LAB_1);

        // different name -> returns false
        Assignment editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentName(VALID_ASSIGNMENT_NAME_2).build();
        assertNotEquals(QUIZ_2, editedQuiz2);

        // different email -> returns false
        editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2).build();
        assertNotEquals(QUIZ_2, editedQuiz2);

        // different telegram -> returns false
        editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();
        assertNotEquals(QUIZ_2, editedQuiz2);
    }
}
