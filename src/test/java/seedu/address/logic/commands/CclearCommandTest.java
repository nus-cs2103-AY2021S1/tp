package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CclearCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.clearContacts();
    }

    @Test
    public void execute_emptyList() {
        model.clearContacts();
        assertCommandFailure(new CclearCommand(), model, CclearCommand.MESSAGE_FAIL);
    }

    @Test
    public void execute_notEmptyList() {
        assertCommandSuccess(new CclearCommand(), model, CclearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
