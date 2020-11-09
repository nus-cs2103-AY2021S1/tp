package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalResiReg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListStudentsCommand.
 */
public class ListBinCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory history = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResiReg(), new UserPrefs());
        expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListBinCommand(), model, history, ListBinCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
