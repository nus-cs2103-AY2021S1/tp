package seedu.address.logic.commands.project;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.util.SampleDataUtil;

/**
 * Contains tests regarding AddTaskCommand
 */
public class AddTaskCommandTest {
    @Test
    public void execute_invalidTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            new AddTaskCommand(null);
        });
    }

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        AddTaskCommand addTaskCommand = new AddTaskCommand(SampleDataUtil.generateTask(SampleDataUtil.getTask1()));
        Model model = null;
        assertThrows(NullPointerException.class, () -> {
            addTaskCommand.execute(model);
        });
    }

}
