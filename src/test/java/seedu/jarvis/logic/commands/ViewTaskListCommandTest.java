package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.getTypicalAddressBook;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_FOUR;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_TWO;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_FOUR;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_TWO;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_FOUR;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_TWO;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.commands.view.ViewTaskListCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;
import seedu.jarvis.testutil.TypicalTasks;

public class ViewTaskListCommandTest {
    private final String taskType = "-t";
    private final String todoType = "-tt";
    private final String eventType = "-te";
    private final String deadlineType = "-td";
    private final String randomType = "-random";
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
    }

    @Test
    public void executeFilters_success() {
        Command commandTask = new ViewTaskListCommand(taskType);
        String expectedMessageTask = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.TASK_LIST);
        assertCommandSuccess(commandTask, model, expectedMessageTask, expectedModel);
        Command commandTodo = new ViewTaskListCommand(todoType);
        String expectedMessageTodo = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.TODO_LIST);
        assertCommandSuccess(commandTodo, model, expectedMessageTodo, expectedModel);
        Command commandEvent = new ViewTaskListCommand(eventType);
        String expectedMessageEvent = String.format(ViewTaskListCommand.MESSAGE_SUCCESS,
                ViewTaskListCommand.EVENT_LIST);
        assertCommandSuccess(commandEvent, model, expectedMessageEvent, expectedModel);
        Command commandDeadline = new ViewTaskListCommand(deadlineType);
        String expectedMessageDeadline = String.format(ViewTaskListCommand.MESSAGE_SUCCESS,
                ViewTaskListCommand.DEADLINE_LIST);
        assertCommandSuccess(commandDeadline, model, expectedMessageDeadline, expectedModel);
        Command commandRandom = new ViewTaskListCommand(randomType);
        String expectedMessageRandom = Messages.MESSAGE_VIEW_TYPE_NOT_FOUND;
        assertCommandFailure(commandRandom, model, expectedMessageRandom);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewTaskListCommand viewTaskListCommand = new ViewTaskListCommand(taskType);
        assertThrows(NullPointerException.class, () -> viewTaskListCommand.execute(emptyModel));
        ViewTaskListCommand viewTodoListCommand = new ViewTaskListCommand(todoType);
        assertThrows(NullPointerException.class, () -> viewTodoListCommand.execute(emptyModel));
        ViewTaskListCommand viewEventListCommand = new ViewTaskListCommand(eventType);
        assertThrows(NullPointerException.class, () -> viewEventListCommand.execute(emptyModel));
        ViewTaskListCommand viewDeadlineListCommand = new ViewTaskListCommand(deadlineType);
        assertThrows(NullPointerException.class, () -> viewDeadlineListCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewTaskCommand_taskListFiltered() {
        String expectedMessage = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.TASK_LIST);
        ViewTaskListCommand command = new ViewTaskListCommand(taskType);
        expectedModel.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TEST_TODO_TWO, TEST_DEADLINE_TWO, TEST_EVENT_FOUR, TEST_DEADLINE_FOUR,
                TEST_EVENT_TWO, TEST_TODO_FOUR), TypicalTasks.getTypicalTasks());
    }

    @Test
    public void execute_viewTaskCommand_todoListFiltered() {
        String expectedMessage = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.TODO_LIST);
        ViewTaskListCommand command = new ViewTaskListCommand(todoType);
        expectedModel.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TODOS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TEST_TODO_TWO, TEST_TODO_FOUR),
                TypicalTasks.getTypicalTodos());
    }

    @Test
    public void execute_viewTaskCommand_eventListFiltered() {
        String expectedMessage = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.EVENT_LIST);
        ViewTaskListCommand command = new ViewTaskListCommand(eventType);
        expectedModel.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_EVENTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TEST_EVENT_FOUR, TEST_EVENT_TWO),
                TypicalTasks.getTypicalEvents());
    }

    @Test
    public void execute_viewTaskCommand_deadlineListFiltered() {
        String expectedMessage = String.format(ViewTaskListCommand.MESSAGE_SUCCESS, ViewTaskListCommand.DEADLINE_LIST);
        ViewTaskListCommand command = new ViewTaskListCommand(deadlineType);
        expectedModel.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_DEADLINES);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TEST_DEADLINE_TWO, TEST_DEADLINE_FOUR),
                TypicalTasks.getTypicalDeadlines());
    }

    @Test
    public void execute_viewTaskCommand_commandTargetFeatureAccurate() throws CommandException {
        ViewTaskListCommand viewTaskListCommand = new ViewTaskListCommand(deadlineType);
        CommandResult commandResult = viewTaskListCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Tasks, actualCommandTargetFeature);
    }

}
