package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.setInvalidSemester;
import static seedu.address.logic.commands.CommandTestUtil.setValidCorrectSemester;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.semester.SemesterManager;

public class DoneCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_invalidSemester_throwsCommandException() {
        setInvalidSemester();
        DoneCommand doneCommand = new DoneCommand();
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_DONE_COMMAND, () -> doneCommand.execute(model));
    }

    @Test
    public void execute_validSemester_success() {
        SemesterManager semesterManager = SemesterManager.getInstance();
        setValidCorrectSemester();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(DoneCommand.MESSAGE_DONE_SEMESTER_SUCCESS, semesterManager.getCurrentSemester()),
                false, false, false, false, false);
        assertCommandSuccess(new DoneCommand(), model, expectedCommandResult, expectedModel);
    }
}
