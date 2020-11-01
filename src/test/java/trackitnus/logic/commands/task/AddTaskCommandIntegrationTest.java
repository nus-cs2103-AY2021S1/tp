package trackitnus.logic.commands.task;

import static trackitnus.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackitnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackitnus.testutil.typical.TypicalTrackIter.getTypicalTrackIter;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.model.Model;
import trackitnus.model.ModelManager;
import trackitnus.model.UserPrefs;
import trackitnus.model.commons.Name;
import trackitnus.model.task.Task;

public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackIter(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new Task(new Name("Testing task"), LocalDate.parse("12/12/2020", Task.FORMATTER),
            null, "sample remarks");

        Model expectedModel = new ModelManager(model.getTrackIter(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(new AddTaskCommand(validTask), model,
            String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTrackIter().getTaskList().get(0);
        assertCommandFailure(new AddTaskCommand(taskInList), model, Messages.MESSAGE_DUPLICATE_TASK);
    }
}
