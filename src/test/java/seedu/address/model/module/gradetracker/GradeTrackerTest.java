package seedu.address.model.module.gradetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.ContactList;
import seedu.address.model.ContactListTest;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.module.grade.*;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.ContactBuilder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.LAB_1;
import static seedu.address.testutil.TypicalAssignments.QUIZ_2;

public class GradeTrackerTest {

    @Test
    public void constructor() {
        GradeTracker gradeTracker = new GradeTracker();
        assertEquals(Collections.emptyList(), gradeTracker.getAssignments());
    }

    @Test
    public void containsDuplicateAssignment_nullAssignment_throwsNullPointerException() {
        GradeTracker gradeTracker = new GradeTracker();
        assertThrows(NullPointerException.class, () -> gradeTracker.containsDuplicateAssignment(null));
    }

    @Test
    public void containsDuplicateAssignment_Assignment_returnsFalse() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertFalse(gradeTracker.containsDuplicateAssignment(LAB_1));
    }

    @Test
    public void containsDuplicateAssignment_sameAssignment_returnsTrue() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertTrue(gradeTracker.containsDuplicateAssignment(QUIZ_2));
    }

    @Test
    public void isValidGradeTracker_nullGradeTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> GradeTracker.isValidGradeTracker(null));
    }

    @Test
    public void isValidGradeTracker_validGradeTrackerWithAssignment_returnsTrue() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertTrue(GradeTracker.isValidGradeTracker(gradeTracker));
    }

    @Test
    public void isValidGradeTracker_validGradeTrackerWithGrade_returnsTrue() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.setGrade(new Grade(VALID_GRADE_1));
        assertTrue(GradeTracker.isValidGradeTracker(gradeTracker));
    }

    @Test
    public void setGrade() {
        GradeTracker gradeTracker = new GradeTracker();
        Grade newGrade = new Grade(VALID_GRADE_1);
        gradeTracker.setGrade(newGrade);
        assertEquals(gradeTracker.getGrade(), newGrade);
    }

    @Test
    public void setGradePoint() {
        GradeTracker gradeTracker = new GradeTracker();
        GradePoint newGradePoint = new GradePoint(VALID_GRADEPOINT_4);
        gradeTracker.setGradePoint(newGradePoint);
        assertEquals(gradeTracker.getGradePoint().get(), newGradePoint);
    }

    @Test
    public void addAssignment() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        assertEquals(gradeTracker.getAssignments().get(0), QUIZ_2);
    }

    @Test
    public void removeAssignment() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        gradeTracker.removeAssignment(QUIZ_2);
        assertThrows(IndexOutOfBoundsException.class, () -> gradeTracker.getAssignments().get(0));
    }

    @Test
    public void getSortedAssignments() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        gradeTracker.addAssignment(LAB_1);
        List<Assignment> expectedSortedAssignments = new ArrayList<>();
        expectedSortedAssignments.add(LAB_1);
        expectedSortedAssignments.add(QUIZ_2);
        assertEquals(gradeTracker.getSortedAssignments(), expectedSortedAssignments);
    }

    @Test
    public void setAssignments() {
        GradeTracker gradeTracker = new GradeTracker();
        gradeTracker.addAssignment(QUIZ_2);
        Assignment editedQuiz2 = new AssignmentBuilder(QUIZ_2).withAssignmentName(VALID_ASSIGNMENT_NAME_2).build();
        gradeTracker.setAssignment(QUIZ_2, editedQuiz2);
        assertTrue(gradeTracker.getAssignments().get(0).equals(editedQuiz2));
        assertFalse(gradeTracker.getAssignments().get(0).equals(QUIZ_2));
    }

    @Test
    public void getAssignments_modifyList_throwsUnsupportedOperationException() {
        GradeTracker gradeTracker = new GradeTracker();
        assertThrows(UnsupportedOperationException.class, () -> gradeTracker.getAssignments().remove(0));
    }

}
