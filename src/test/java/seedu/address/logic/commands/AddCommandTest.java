package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ProductiveNus;
import seedu.address.model.ReadOnlyProductiveNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Task;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.timetable.TimetableData;

public class AddCommandTest {

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        Assignment validAssignment = new AssignmentBuilder().build();

        CommandResult commandResult = new AddCommand(validAssignment).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validAssignment), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignmentsAdded);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        AddCommand addCommand = new AddCommand(validAssignment);
        ModelStub modelStub = new ModelStubWithAssignment(validAssignment);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Assignment CS1231SHomework = new AssignmentBuilder().withName("CS1231S Homework").build();
        Assignment cs1231SHomework = new AssignmentBuilder().withName("cs1231s homework").build();
        Assignment CS2103TTutorial = new AssignmentBuilder().withName("CS2103T Tutorial").build();
        Assignment CS2030Homework = new AssignmentBuilder().withName("Homework").withModuleCode("CS2030").build();
        Assignment CS1010Homework = new AssignmentBuilder().withName("Homework").withModuleCode("CS1010").build();



        AddCommand addCS1231SHomeworkCommand = new AddCommand(CS1231SHomework);
        AddCommand addCs1231sHomeworkCommand = new AddCommand(cs1231SHomework);
        AddCommand addCS2103TTutorialCommand = new AddCommand(CS2103TTutorial);
        AddCommand addCS2030HomeworkCommand = new AddCommand(CS2030Homework);
        AddCommand addCS1010HomeworkCommand = new AddCommand(CS1010Homework);


        // same object -> returns true
        assertTrue(addCS1231SHomeworkCommand.equals(addCS1231SHomeworkCommand));

        // same values -> returns true
        AddCommand addCs1231SHomeworkCommandCopy = new AddCommand(CS1231SHomework);
        assertTrue(addCS1231SHomeworkCommand.equals(addCs1231SHomeworkCommandCopy));

        // same name different caps -> returns true
        assertTrue(addCS1231SHomeworkCommand.equals(addCs1231sHomeworkCommand));

        // same name different module -> returns false
        assertFalse(addCS2030HomeworkCommand.equals(addCS1010HomeworkCommand));

        // different types -> returns false
        assertFalse(addCS1231SHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(addCS1231SHomeworkCommand.equals(null));

        // different assignment -> returns false
        assertFalse(addCS1231SHomeworkCommand.equals(addCS2103TTutorialCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPreviousModel(Model previousModel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void goToPreviousModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Assignment> getFilteredAssignments() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Model getPreviousModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getProductiveNusFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProductiveNusFilePath(Path productiveNusFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void preUpdateModel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProductiveNus(ReadOnlyProductiveNus newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProductiveNus getProductiveNus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void importTimetable(TimetableData data) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Assignment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAssignment(Assignment target, Assignment editedAssignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getRemindedAssignmentsList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single assignment.
     */
    private class ModelStubWithAssignment extends ModelStub {
        private final Assignment assignment;

        ModelStubWithAssignment(Assignment assignment) {
            requireNonNull(assignment);
            this.assignment = assignment;
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return this.assignment.isSameAssignment(assignment);
        }
    }

    /**
     * A Model stub that always accept the assignment being added.
     */
    private class ModelStubAcceptingAssignmentAdded extends ModelStub {
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public void addAssignment(Assignment assignment) {
            requireNonNull(assignment);
            assignmentsAdded.add(assignment);
        }

        @Override
        public ReadOnlyProductiveNus getProductiveNus() {
            return new ProductiveNus();
        }
    }
}
