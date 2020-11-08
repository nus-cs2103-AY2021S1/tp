package seedu.address.logic.commands.gradetrackercommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_2;
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
import seedu.address.model.module.grade.Grade;
import seedu.address.testutil.ModuleBuilder;


public class AddGradeCommandTest {

    private static final Grade GRADE_1 = new Grade(VALID_GRADE_1);

    private static final Grade GRADE_2 = new Grade(VALID_GRADE_2);

    @Test
    public void constructor_nullGradeToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGradeCommand(null, GRADE_1));
    }

    @Test
    public void constructor_nullGrade_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();
        assertThrows(NullPointerException.class, () -> new AddGradeCommand(moduleToAddName, null));
    }

    @Test
    public void executeValidGradeAddSuccess() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Model expectedModel = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module expectedModule = expectedModel.getFilteredModuleList().get(INDEX_SECOND_MODULE.getOneBased());
        Module expectedUpdatedModule = new ModuleBuilder(expectedModule).build();
        expectedModule.addGrade(GRADE_1);
        AddGradeCommand command = new AddGradeCommand(expectedModule.getName(), GRADE_1);
        String expectedMessage = String.format(AddGradeCommand.MESSAGE_SUCCESS, GRADE_1);

        expectedModel.setModule(expectedModule, expectedUpdatedModule);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        ModuleName moduleToAddName = moduleToUpdate.getName();

        AddGradeCommand command = new AddGradeCommand(moduleToAddName, GRADE_1);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_invalidModuleName_throwsCommandException() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        ModuleName moduleNameNotInList = new ModuleName(VALID_MODULENAME_ES2660);
        AddGradeCommand command = new AddGradeCommand(moduleNameNotInList, GRADE_1);

        assertCommandFailure(command, model, AddGradeCommand.MESSAGE_GRADE_NOT_ADDED);
    }

    @Test
    public void equals() {
        Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());

        Module moduleToUpdate = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        ModuleName moduleToAddName = moduleToUpdate.getName();

        final AddGradeCommand standardCommand = new AddGradeCommand(moduleToAddName,
                GRADE_1);

        // same index and descriptor -> returns true
        Grade duplicateGrade1 = new Grade(GRADE_1.gradeResult);
        AddGradeCommand commandWithSameValues = new AddGradeCommand(moduleToAddName,
                duplicateGrade1);

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
        assertNotEquals(standardCommand, new AddGradeCommand(differentModuleName, GRADE_1));

        assertNotEquals(standardCommand, new AddGradeCommand(moduleToAddName, GRADE_2));
    }

}
