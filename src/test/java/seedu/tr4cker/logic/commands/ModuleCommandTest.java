package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_CODE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_NAME_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tr4cker.commons.core.GuiSettings;
import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.ReadOnlyUserPrefs;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.task.Task;


/**
 * Contains integration tests (interaction with the Model) for {@code ModuleCommand}.
 */
class ModuleCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    private final Module testModule = new Module(VALID_MODULE_NAME_1, new ModuleCode(VALID_MODULE_CODE_1));

    @Test
    public void execute_switchPlannerTab_success() throws CommandException {
        CommandResult commandResult = new ModuleCommand().execute(model);
        CommandResult expectedCommandResult =
                CommandResult.createModuleTabSwitchCommandResult(ModuleCommand.MESSAGE_SWITCH_TAB_SUCCESS);
        assertEquals(commandResult, expectedCommandResult);
    }

    @Test
    public void execute_invalidDeleteIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        ModuleCommand moduleCommand = new ModuleCommand(outOfBoundIndex);

        assertCommandFailure(moduleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_unableToDeleteModule_throwsCommandException() {
        ModelStubWithTaskModulePaired modelStub = new ModelStubWithTaskModulePaired();
        ModuleCommand moduleCommand = new ModuleCommand(Index.fromOneBased(1));

        assertThrows(CommandException.class,
                ModuleCommand.MESSAGE_STILL_HAS_TASKS, () -> moduleCommand.execute(modelStub));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModuleCommandTest.ModelStubAcceptingModule modelStub = new ModuleCommandTest.ModelStubAcceptingModule();
        Module validModule = testModule;

        CommandResult commandResult = new ModuleCommand(validModule).execute(modelStub);

        assertEquals(String.format(ModuleCommand.MESSAGE_MODULE_ADD_SUCCESS, validModule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_moduleDeletedFromModel_deleteSuccessful() throws Exception {
        ModuleCommandTest.ModelStubAllowingDelete modelStub = new ModuleCommandTest.ModelStubAllowingDelete();

        CommandResult commandResult = new ModuleCommand(Index.fromOneBased(1)).execute(modelStub);

        assertEquals(String.format(ModuleCommand.MESSAGE_MODULE_DELETE_SUCCESS, testModule),
                commandResult.getFeedbackToUser());
        assertEquals(new ArrayList<>(), modelStub.modulesAdded);
    }

    @Test
    public void execute_moduleAddedtoModel_addFailure() throws Exception {
        ModuleCommandTest.ModelStubRejectingModule modelStub = new ModuleCommandTest.ModelStubRejectingModule();

        ModuleCommand moduleCommand = new ModuleCommand(testModule);

        assertThrows(CommandException.class,
                ModuleCommand.MESSAGE_DUPLICATE_MODULE, () -> moduleCommand.execute(modelStub));
    }

    @Test
    void testEquals() {
        ModuleCommand addCommand1 = new ModuleCommand(testModule);
        ModuleCommand addCommand2 = new ModuleCommand(testModule);
        ModuleCommand switchCommand = new ModuleCommand();
        ModuleCommand deleteCommand = new ModuleCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same command
        assertTrue(addCommand1.equals(addCommand2));

        // null -> false
        assertFalse(addCommand1.equals(null));

        // different -> false
        assertFalse(addCommand1.equals(switchCommand));
        assertFalse(addCommand1.equals(deleteCommand));
        assertFalse(switchCommand.equals(deleteCommand));
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
        public Path getTr4ckerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTr4ckerFilePath(Path tr4ckerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTr4cker(ReadOnlyTr4cker tr4cker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTr4cker getTr4cker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRelatedIncompleteTasks(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasValidModuleField(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTodo(Todo target, Todo editedTodo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredPendingTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredExpiredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredCompletedTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getPlannerFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Todo> getFilteredTodoList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPendingTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpiredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompletedTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePlannerFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTodoList(Predicate<Todo> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that has a single module and always reports module has related tasks.
     */
    class ModelStubWithTaskModulePaired extends ModelStub {

        @Override
        public boolean hasRelatedIncompleteTasks(Module module) {
            return true;
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            ObservableList<Module> list = FXCollections.observableArrayList();
            list.add(new Module("name", new ModuleCode("code")));
            return list;
        }
    }

    /**
     * A Model stub that always accepts a module.
     */
    class ModelStubAcceptingModule extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            return false;
        }

        @Override
        public void addModule(Module module) {
            modulesAdded.add(module);
        }
    }

    /**
     * A Model stub that always allows deleting a module.
     */
    class ModelStubAllowingDelete extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>(Arrays.asList(testModule));

        @Override
        public boolean hasRelatedIncompleteTasks(Module module) {
            return false;
        }

        @Override
        public void deleteModule(Module target) {
            modulesAdded.remove(target);
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return FXCollections.observableList(modulesAdded);
        }
    }

    /**
     * A Model stub that always rejects adding a module.
     */
    class ModelStubRejectingModule extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            return true;
        }
    }
}
