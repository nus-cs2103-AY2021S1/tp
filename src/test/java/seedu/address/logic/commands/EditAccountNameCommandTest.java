package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;

public class EditAccountNameCommandTest {
    private static final Account GENERAL_ACCOUNT = new Account(new Name("General account"));
    private Model model = new ModelManager();

    @BeforeEach
    public void addAccount() {
        model.addAccount(GENERAL_ACCOUNT);
    }

    @Test
    public void constructor_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditAccountNameCommand(null));
    }

    @Test
    public void execute_editNameOfFirstAccount_success() {
        Name currentName = GENERAL_ACCOUNT.getName();
        Name newName = new Name("new Name");
        EditAccountNameCommand editAccountNameCommand = new EditAccountNameCommand(newName);

        Model expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());
        expectedModel.setAccount(GENERAL_ACCOUNT, new Account(newName));

        String expectedMessage = String.format(EditAccountNameCommand.MESSAGE_SUCCESS, currentName.toString(),
                newName.toString());

        assertCommandSuccess(editAccountNameCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Name nameOne = new Name("Name 1");
        Name nameTwo = new Name("Name 2");
        EditAccountNameCommand editAccountNameOneCommand = new EditAccountNameCommand(nameOne);
        EditAccountNameCommand editAccountNameTwoCommand = new EditAccountNameCommand(nameTwo);

        // same object -> returns true
        assertTrue(editAccountNameOneCommand.equals(editAccountNameOneCommand));

        // same values -> returns true
        EditAccountNameCommand addAccountNameOneCommandCopy = new EditAccountNameCommand(nameOne);
        assertTrue(editAccountNameOneCommand.equals(addAccountNameOneCommandCopy));

        // different types -> returns false
        assertFalse(editAccountNameOneCommand.equals(1));

        // null -> returns false
        assertFalse(editAccountNameOneCommand.equals(null));

        // different person -> returns false
        assertFalse(editAccountNameOneCommand.equals(editAccountNameTwoCommand));
    }

}
