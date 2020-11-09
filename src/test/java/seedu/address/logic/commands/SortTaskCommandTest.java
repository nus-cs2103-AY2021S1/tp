package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortTaskCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateComparator;

public class SortTaskCommandTest {
    private static final Predicate<Task> PREDICATE_SHOW_NO_TASKS = unused -> false;
    private static final TaskDateComparator TASKCOMPARATOR = new TaskDateComparator();
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void execute_unsortedTaskList_success() {
        SortTaskCommand sortTaskCommand = new SortTaskCommand();

        String expectedMessage = String.format(SortTaskCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        expectedModel.updateSortedTaskList(TASKCOMPARATOR);
        assertCommandSuccess(sortTaskCommand, model, expectedMessage, "TASK", expectedModel);
    }
    @Test
    public void execute_emptyTaskList_successWithPrompt() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        SortTaskCommand sortTaskCommand = new SortTaskCommand();

        String expectedMessage = String.format(SortTaskCommand.MESSAGE_EMPTY_TASK_LIST);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        expectedModel.updateSortedTaskList(TASKCOMPARATOR);
        assertCommandSuccess(sortTaskCommand, model, expectedMessage, "TASK", expectedModel);
    }
    @Test
    public void execute_emptyFilteredTaskList_successWithPrompt() {
        //Filter list using predicate to use it as an input for the test
        model.updateFilteredTaskList(PREDICATE_SHOW_NO_TASKS);
        SortTaskCommand sortTaskCommand = new SortTaskCommand();
        String expectedMessage = String.format(SortTaskCommand.MESSAGE_EMPTY_FILTERED_TASK_LIST);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        expectedModel.updateSortedTaskList(TASKCOMPARATOR);
        assertCommandSuccess(sortTaskCommand, model, expectedMessage, "TASK", expectedModel);
    }
}
