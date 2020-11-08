package seedu.address.logic.commands.gradetrackercommands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
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
import seedu.address.testutil.ModuleListBuilder;
import seedu.address.testutil.gradetracker.AssignmentBuilder;

public class AddAssignmentCommandTest {

    private static final Assignment ASSIGNMENT_QUIZ_2 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

    private static final Assignment ASSIGNMENT_ORAL_PRESENTATION_2 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2)
            .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
            .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

    @Test
    public void constructor_nullModuleToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null, ASSIGNMENT_QUIZ_2));
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
        ModuleList moduleList = new ModuleListBuilder().build();
        Module module = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();
        moduleList.addModule(module);
        Model model = new ModelManager(moduleList, new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        ModuleList expectedModuleList = new ModuleListBuilder().build();
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();

        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        expectedModuleList.addModule(expectedModule.addAssignment(expectedAssignment));
        Model expectedModel = new ModelManager(model.getModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        AddAssignmentCommand command = new AddAssignmentCommand(module.getName(), expectedAssignment);

        String expectedMessage = String.format(AddAssignmentCommand.MESSAGE_SUCCESS, expectedAssignment);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        AddAssignmentCommand command = new AddAssignmentCommand(moduleToAddName, ASSIGNMENT_QUIZ_2);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidModuleName_throwsCommandException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        ModuleName moduleNameNotInList = new ModuleName(VALID_MODULENAME_ES2660);
        AddAssignmentCommand command = new AddAssignmentCommand(moduleNameNotInList, ASSIGNMENT_QUIZ_2);

        assertCommandFailure(command, model, AddAssignmentCommand.MESSAGE_ASSIGNMENT_NOT_ADDED);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        Module moduleWithAddedAssignment = new ModuleBuilder(moduleToUpdate).build();
        moduleWithAddedAssignment.addAssignment(ASSIGNMENT_QUIZ_2);
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
                ASSIGNMENT_ORAL_PRESENTATION_2);

        // same index and details -> returns true
        Assignment duplicateAssignmentOralPresentation2 = new AssignmentBuilder()
                .withAssignmentName(VALID_ASSIGNMENT_NAME_2).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();
        AddAssignmentCommand commandWithSameValues = new AddAssignmentCommand(moduleToAddName,
                duplicateAssignmentOralPresentation2);

        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different type -> returns false
        assertNotEquals(8, standardCommand);

        // different command types -> returns false
        assertNotEquals(standardCommand, new ExitCommand());

        moduleToUpdate = model.getFilteredModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        ModuleName differentModuleName = moduleToUpdate.getName();
        // different index, same descriptor -> returns false
        assertNotEquals(standardCommand, new AddAssignmentCommand(differentModuleName, ASSIGNMENT_ORAL_PRESENTATION_2));

        // different details, same index -> returns false
        Assignment assignmentQuiz1 = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertNotEquals(standardCommand, new AddAssignmentCommand(moduleToAddName, assignmentQuiz1));
    }
}
