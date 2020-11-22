package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClientListCommand.
 */
public class ClientListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getTbmManager(), new UserPrefs());
    }

    @Test
    public void execute_listIsEmpty_displaysEmptyMessage() {
        Model newModel = new ModelManager();
        CommandResult expectedResult = new CommandResult(ClientListCommand.MESSAGE_SUCCESS_NO_CLIENTS,
                true, false, false);
        assertCommandSuccess(new ClientListCommand(), newModel, expectedResult, newModel);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedResult = new CommandResult(ClientListCommand.MESSAGE_SUCCESS, true, false, false);
        assertCommandSuccess(new ClientListCommand(), model, expectedResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        CommandResult expectedResult = new CommandResult(ClientListCommand.MESSAGE_SUCCESS, true, false, false);
        assertCommandSuccess(new ClientListCommand(), model, expectedResult, expectedModel);
    }

}
