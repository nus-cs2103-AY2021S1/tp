package seedu.fma.logic.commands;

import static seedu.fma.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.UserPrefs;
import seedu.fma.model.log.Log;
import seedu.fma.testutil.LogBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLogBook(), new UserPrefs());
    }

    @Test
    public void execute_newLog_success() {
        Log validLog = new LogBuilder().withComment("Hello, it's me").withDateTime(LocalDateTime.now()).build();

        Model expectedModel = new ModelManager(model.getLogBook(), new UserPrefs());
        expectedModel.addLog(validLog);

        assertCommandSuccess(new AddCommand(validLog), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validLog), expectedModel);
    }

    @Test
    public void execute_duplicateLog_throwsCommandException() {
        Log logInList = model.getLogBook().getLogList().get(0);
        assertCommandFailure(new AddCommand(logInList), model, AddCommand.MESSAGE_DUPLICATE_LOG);
    }
}
