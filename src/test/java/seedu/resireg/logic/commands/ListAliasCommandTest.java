package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalCommandWordAliases.getTypicalUserPrefs;
import static seedu.resireg.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.model.AddressBook;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAliasCommandTest {

    private Model fullModel;
    private Model expectedFullModel;

    private Model emptyModel;
    private Model expectedEmptyModel;

    @BeforeEach
    public void setUp() {
        fullModel = new ModelManager(getTypicalAddressBook(), getTypicalUserPrefs());
        expectedFullModel = new ModelManager(fullModel.getAddressBook(), fullModel.getUserPrefs());

        emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedEmptyModel = new ModelManager(emptyModel.getAddressBook(), emptyModel.getUserPrefs());
    }

    @Test
    public void execute_listExists_showsSameList() {
        assertCommandSuccess(new ListAliasCommand(), fullModel,
            String.format(ListAliasCommand.MESSAGE_SUCCESS, fullModel.getCommandWordAliasesAsString()),
            expectedFullModel);
    }

    @Test
    public void execute_listIsEmpty_showsEmptyMessage() {
        assertCommandSuccess(new ListAliasCommand(), emptyModel,
            ListAliasCommand.MESSAGE_EMPTY_ALIAS, expectedEmptyModel);
    }
}
