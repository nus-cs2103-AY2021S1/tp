package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.testutil.TypicalEntries;

public class DeleteAccountCommandTest {
    private Model model = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Account secondAccount = new Account(new Name("Second Account"));
        model.addAccount(secondAccount);

        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand(INDEX_SECOND_ENTRY);

        String expectedMessage = String.format(DeleteAccountCommand.MESSAGE_DELETE_ACCOUNT_SUCCESS, secondAccount);

        ModelManager expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());
        expectedModel.deleteAccount(secondAccount);

        assertCommandSuccess(deleteAccountCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexOutOfRange_throwCommandException() {
        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand(INDEX_SECOND_ENTRY);

        String expectedMessage = Messages.MESSAGE_INVALID_DISPLAYED_INDEX;

        assertCommandFailure(deleteAccountCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteOnlyAccountLeft_throwCommandException() {
        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = DeleteAccountCommand.MESSAGE_ONE_ACCOUNT_LEFT;

        assertCommandFailure(deleteAccountCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteCurrentActiveAccount_throwCommandException() {
        Account secondAccount = new Account(new Name("Second Account"));
        model.addAccount(secondAccount);
        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = DeleteAccountCommand.MESSAGE_ACTIVE_ACCOUNT;

        assertCommandFailure(deleteAccountCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Account secondAccount = new Account(new Name("Second Account"));
        model.addAccount(secondAccount);

        DeleteAccountCommand deleteFirstAccountCommand = new DeleteAccountCommand(INDEX_FIRST_ENTRY);
        DeleteAccountCommand deleteSecondAccountCommand = new DeleteAccountCommand(INDEX_SECOND_ENTRY);

        // same object -> returns true
        assertTrue(deleteFirstAccountCommand.equals(deleteFirstAccountCommand));

        // same values -> returns true
        DeleteAccountCommand deleteFirstAccountCommandCopy = new DeleteAccountCommand(INDEX_FIRST_ENTRY);
        assertTrue(deleteFirstAccountCommand.equals(deleteFirstAccountCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstAccountCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstAccountCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstAccountCommand.equals(deleteSecondAccountCommand));
    }

}
