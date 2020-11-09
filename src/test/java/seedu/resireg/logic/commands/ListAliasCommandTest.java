package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalCommandWordAliases.getTypicalUserPrefs;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListStudentsCommand.
 */
public class ListAliasCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model fullModel;
    private Model expectedFullModel;

    private Model emptyModel;
    private Model expectedEmptyModel;

    @BeforeEach
    public void setUp() {
        fullModel = new ModelManager(getTypicalResiReg(), getTypicalUserPrefs());
        expectedFullModel = new ModelManager(fullModel.getResiReg(), fullModel.getUserPrefs());

        emptyModel = new ModelManager(new ResiReg(), new UserPrefs());
        expectedEmptyModel = new ModelManager(emptyModel.getResiReg(), emptyModel.getUserPrefs());
    }

    @Test
    public void execute_listExists_showsSameList() {
        assertCommandSuccess(new ListAliasCommand(), fullModel, history,
            String.format(ListAliasCommand.MESSAGE_SUCCESS, fullModel.getCommandWordAliasesAsString()),
            expectedFullModel);
    }

    @Test
    public void execute_listIsEmpty_showsEmptyMessage() {
        assertCommandSuccess(new ListAliasCommand(), emptyModel, history,
            ListAliasCommand.MESSAGE_EMPTY_ALIAS, expectedEmptyModel);
    }
}
