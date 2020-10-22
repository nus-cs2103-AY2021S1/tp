package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.ToDo;
import seedu.address.testutil.ToDoBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        ToDo validTodo = new ToDoBuilder().build();

        Model expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        expectedModel.addTask(validTodo);

        assertCommandSuccess(new AddTodoCommand(validTodo), model,
                String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskManager().getTaskList().get(0);

        // First task in list is of Event Type
        assertCommandFailure(new AddEventCommand((Event) taskInList), model, AddEventCommand.MESSAGE_DUPLICATE_TASK);
    }

}
