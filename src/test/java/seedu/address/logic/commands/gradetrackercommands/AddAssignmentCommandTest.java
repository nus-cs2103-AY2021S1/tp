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
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

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
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.gradetracker.AssignmentBuilder;

public class AddAssignmentCommandTest {

    private Assignment assignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

    private Assignment assignmentOralPresentation2 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

    @Test
    public void constructor_nullModuleToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null, assignmentQuiz1));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(moduleToAddName, null));
    }

    @Test
    public void executeValidAssignmentAddSuccess() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_SECOND_MODULE.getOneBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();

        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, assignmentQuiz1);

        String expectedMessage = String.format(AddAssignmentCommand.MESSAGE_SUCCESS, assignmentQuiz1);

        Model expectedModel = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module expectedModule = expectedModel.getFilteredModuleList().get(INDEX_FIRST_MODULE.getOneBased());

        Module expectedUpdatedModule = expectedModule.addAssignment(assignmentQuiz1);

        expectedModel.setModule(expectedModule, expectedUpdatedModule);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, assignmentQuiz1);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidModuleName_throwsCommandException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        ModuleName moduleNameNotInList = new ModuleName(VALID_MODULENAME_ES2660);
        AddAssignmentCommand command = new AddAssignmentCommand(moduleNameNotInList, assignmentQuiz1);

        assertCommandFailure(command, model, AddAssignmentCommand.MESSAGE_ASSIGNMENT_NOT_ADDED);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        Module moduleWithAddedAssignment = new ModuleBuilder(moduleToUpdate).build();
        moduleWithAddedAssignment.addAssignment(assignmentQuiz1);
        model.setModule(moduleToUpdate, moduleWithAddedAssignment);

        Assignment duplicateAssignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, duplicateAssignmentQuiz1);

        assertCommandFailure(command, model, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void equals() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();

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

        moduleToUpdate = model.getFilteredModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
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
