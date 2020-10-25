package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;
import seedu.address.model.account.Name;
import seedu.address.testutil.TypicalEntries;



public class SwitchAccountCommandTest {
    private Model model = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
    private ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @BeforeEach
    public void addAccount() {
        model.addAccount(new Account(new Name("Account 2")));
    }

    @Test
    public void execute_validIndex_success() {
        Account accountToSwitch = model.getFilteredAccountList().get(INDEX_SECOND_ENTRY.getZeroBased());

        SwitchAccountCommand switchAccountCommand = new SwitchAccountCommand(INDEX_SECOND_ENTRY);

        String expectedMessage = String.format(SwitchAccountCommand.MESSAGE_SWITCH_ACCOUNT_SUCCESS, accountToSwitch);

        ModelManager expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());

        assertCommandSuccess(switchAccountCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexOutOfRange_throwsCommandException() {
        SwitchAccountCommand switchAccountCommand = new SwitchAccountCommand(INDEX_THIRD_ENTRY);

        String expectedMessage = Messages.MESSAGE_INVALID_DISPLAYED_INDEX;

        assertCommandFailure(switchAccountCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexSameAccount_throwsCommandException() {
        SwitchAccountCommand switchAccountCommand = new SwitchAccountCommand(INDEX_FIRST_ENTRY);

        String expectedMessage = SwitchAccountCommand.MESSAGE_ACTIVE_ACCOUNT;

        assertCommandFailure(switchAccountCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        SwitchAccountCommand switchFirstAccountCommand = new SwitchAccountCommand(INDEX_FIRST_ENTRY);
        SwitchAccountCommand switchSecondAccountCommand = new SwitchAccountCommand(INDEX_SECOND_ENTRY);
        // same object -> returns true
        assertTrue(switchFirstAccountCommand.equals(switchFirstAccountCommand));

        // same values -> returns true
        SwitchAccountCommand switchFirstAccountCommandCopy = new SwitchAccountCommand(INDEX_FIRST_ENTRY);
        assertTrue(switchFirstAccountCommand.equals(switchFirstAccountCommandCopy));

        // different types -> returns false
        assertFalse(switchFirstAccountCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstAccountCommand.equals(null));

        // different person -> returns false
        assertFalse(switchFirstAccountCommand.equals(switchSecondAccountCommand));
    }

}
