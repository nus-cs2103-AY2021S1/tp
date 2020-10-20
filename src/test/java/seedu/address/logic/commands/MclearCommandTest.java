package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MclearCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.clearMod();
    }

    @Test
    public void execute_emptyList() {
        model.clearMod();
        assertCommandFailure(new MclearCommand(), model, MclearCommand.MESSAGE_FAIL);
    }

    @Test
    public void execute_notEmptyList() {
        assertCommandSuccess(new MclearCommand(), model, MclearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
