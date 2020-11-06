package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;
import static seedu.address.testutil.TypicalAssignments.HW;
import static seedu.address.testutil.TypicalAssignments.LAB;
import static seedu.address.testutil.TypicalAssignments.QUIZ;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(HW.isSameAssignment(HW));

        // null -> returns false
        assertFalse(HW.isSameAssignment(null));

        // different deadline -> returns false
        Assignment editedHw = new AssignmentBuilder(HW).withDeadline(VALID_DEADLINE_LAB).build();
        assertFalse(HW.isSameAssignment(editedHw));

        // different name -> returns false
        editedHw = new AssignmentBuilder(HW).withName(VALID_NAME_LAB).build();
        assertFalse(HW.isSameAssignment(editedHw));

        // same name, same deadline, different attributes -> returns true
        editedHw = new AssignmentBuilder(HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        assertTrue(HW.isSameAssignment(editedHw));

        // same name, different attributes -> returns false
        editedHw = new AssignmentBuilder(HW).withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_HW)
                .build();
        assertFalse(HW.isSameAssignment(editedHw));

        // same name, same deadline, different attributes -> returns true
        editedHw = new AssignmentBuilder(HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        assertTrue(HW.isSameAssignment(editedHw));
    }

    @Test
    public void isAlreadyReminded() {
        // new assignment created -> returns false
        Assignment newAssignment = new AssignmentBuilder().build();
        assertFalse(newAssignment.isReminded());

        // assignment set to have reminders -> returns true
        Assignment homeworkCopy = new AssignmentBuilder(HW).withRemindersSet().build();
        assertTrue(homeworkCopy.isReminded());

        // assignment already has reminders set -> returns true
        assertTrue(QUIZ.isReminded());

        // already has reminders set, different name -> returns true
        Assignment editedQuiz = new AssignmentBuilder(QUIZ).withName(VALID_NAME_LAB).build();
        assertTrue(editedQuiz.isReminded());

        // already has reminders set, different deadline -> returns true
        editedQuiz = new AssignmentBuilder(QUIZ).withDeadline(VALID_DEADLINE_LAB).build();
        assertTrue(editedQuiz.isReminded());

        // already has reminders set, different address -> returns true
        editedQuiz = new AssignmentBuilder(QUIZ).withModuleCode(VALID_MODULE_CODE_LAB).build();
        assertTrue(editedQuiz.isReminded());

    }
    @Test
    public void equals() {
        // same values -> returns true
        Assignment hwCopy = new AssignmentBuilder(HW).build();
        assertTrue(HW.equals(hwCopy));

        // same object -> returns true
        assertTrue(HW.equals(HW));

        // null -> returns false
        assertFalse(HW.equals(null));

        // different type -> returns false
        assertFalse(HW.equals(5));

        // different assignment -> returns false
        assertFalse(HW.equals(LAB));

        // different name -> returns false
        Assignment editedHw = new AssignmentBuilder(HW).withName(VALID_NAME_LAB).build();
        assertFalse(HW.equals(editedHw));

        // different deadline -> returns false
        editedHw = new AssignmentBuilder(HW).withDeadline(VALID_DEADLINE_LAB).build();
        assertFalse(HW.equals(editedHw));

        // different module code -> returns false
        editedHw = new AssignmentBuilder(HW).withModuleCode(VALID_MODULE_CODE_LAB).build();
        assertFalse(HW.equals(editedHw));
    }
}
