package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.CS2103T_TUT;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.timetable.TimetableData;


public class ProductiveNusTest {

    private final ProductiveNus productiveNus = new ProductiveNus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), productiveNus.getAssignmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productiveNus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyProductiveNus_replacesData() {
        ProductiveNus newData = getTypicalProductiveNus();
        productiveNus.resetData(newData);
        assertEquals(newData, productiveNus);
    }

    @Test
    public void resetData_withDuplicateAssignments_throwsDuplicateAssignmentException() {
        // Two assignments with the same identity fields
        Assignment editedCs2103Lab = new AssignmentBuilder(CS2103T_TUT).withModuleCode(VALID_MODULE_CODE_HW).build();
        List<Assignment> newAssignments = Arrays.asList(CS2103T_TUT, editedCs2103Lab);
        List<Lesson> lessonList = Arrays.asList();
        ProductiveNusStub newData = new ProductiveNusStub(newAssignments, lessonList);

        assertThrows(DuplicateAssignmentException.class, () -> productiveNus.resetData(newData));
    }

    @Test
    public void hasAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productiveNus.hasAssignment(null));
    }

    @Test
    public void hasAssignment_assignmentNotInProductiveNus_returnsFalse() {
        assertFalse(productiveNus.hasAssignment(CS1231S_HW));
    }

    @Test
    public void hasAssignment_assignmentInProductiveNus_returnsTrue() {
        productiveNus.addAssignment(CS1231S_HW);
        assertTrue(productiveNus.hasAssignment(CS1231S_HW));
    }

    // test fail because module code is different
    @Test
    public void hasAssignment_assignmentWithDifferentIdentityFieldsInProductiveNusk_returnsFalse() {
        productiveNus.addAssignment(CS1231S_HW);
        Assignment editedCs1231sHw = new AssignmentBuilder(CS1231S_HW)
                .withModuleCode(VALID_MODULE_CODE_HW).build();
        assertFalse(productiveNus.hasAssignment(editedCs1231sHw));
    }

    @Test
    public void getAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> productiveNus.getAssignmentList().remove(0));
    }

    /**
     * A stub ReadOnlyProductiveNus whose assignments list can violate interface constraints.
     */
    private static class ProductiveNusStub implements ReadOnlyProductiveNus {
        private final ObservableList<Assignment> assignments = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        ProductiveNusStub(Collection<Assignment> assignments, Collection<Lesson> lessons) {
            this.assignments.setAll(assignments);
            this.lessons.setAll(lessons);
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return assignments;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }

        @Override
        public void importTimetable(TimetableData data) {
        }
    }

}
