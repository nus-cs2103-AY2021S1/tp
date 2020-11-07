package seedu.address.logic.commands.gradetrackercommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModulesWithAssignmentList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.AssignmentBuilder;

public class AddAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalModulesWithAssignmentList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());
    private Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

    private Assignment assignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
    private Module moduleWithAddedAssignment = moduleToUpdate.addAssignment(assignmentQuiz1);

    private Assignment assignmentOralPresentation2 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

    private ModuleName moduleToAddName = moduleToUpdate.getName();

    @Test
    public void constructor_nullModuleToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null, assignmentQuiz1));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(moduleToAddName, null));
    }

    @Test
    public void executeValidAssignmentAddSuccess() {
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, assignmentOralPresentation2);

        String expectedMessage = String.format(AddAssignmentCommand.MESSAGE_SUCCESS, assignmentOralPresentation2);

        Model expectedModel = new ModelManager(new ModuleList(model.getModuleList()), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setModule(moduleToUpdate, moduleWithAddedAssignment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, assignmentQuiz1);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidModuleName_throwsCommandException() {
        ModuleName moduleNameNotInList = new ModuleName(VALID_MODULENAME_ES2660);
        AddAssignmentCommand command = new AddAssignmentCommand(moduleNameNotInList, assignmentQuiz1);

        assertCommandFailure(command, model, AddAssignmentCommand.MESSAGE_ASSIGNMENT_NOT_ADDED);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment duplicateAssignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, duplicateAssignmentQuiz1);

        assertCommandFailure(command, model, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void equals() {

        final AddAssignmentCommand standardCommand = new AddAssignmentCommand(moduleToAddName,
                assignmentOralPresentation2);

        // same index and descriptor -> returns true
        Assignment duplicateAssignmentOralPresentation2 = new AssignmentBuilder()
                .withAssignmentName(VALID_ASSIGNMENT_NAME_2).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();
        AddAssignmentCommand commandWithSameValues = new AddAssignmentCommand(moduleToAddName,
                duplicateAssignmentOralPresentation2);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different type -> returns false
        assertFalse(standardCommand.equals(8));

        // different command types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        ModuleName differentModuleName = moduleToUpdate.getName();
        // different index, same descriptor -> returns false
        assertFalse(standardCommand.equals(new AddAssignmentCommand(differentModuleName, assignmentOralPresentation2)));

        // different descriptor, same index -> returns false
        Assignment assignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertFalse(standardCommand.equals(new AddAssignmentCommand(moduleToAddName, assignmentQuiz1)));
    }
}
