package seedu.cc.logic.commands.accountlevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;
import seedu.cc.testutil.ActiveAccountStub;
import seedu.cc.testutil.ModelStub;
import seedu.cc.testutil.TypicalEntries;


public class AddAccountCommandTest {
    @Test
    public void constructor_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAccountCommand(null));
    }

    @Test
    public void execute_accountAcceptedByModel_addSuccessful() throws Exception {
        ModelStub modelStub = new ModelStub();
        ActiveAccountStub activeAccountStub = new ActiveAccountStub();
        Account validAccount = TypicalEntries.getTypicalAccount();

        CommandResult commandResult = new AddAccountCommand(validAccount).execute(modelStub, activeAccountStub);

        assertEquals(String.format(AddAccountCommand.MESSAGE_SUCCESS, validAccount), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAccount), modelStub.getAccountList());

    }

    @Test
    public void execute_duplicateAccount_throwsCommandException() {
        Account validAccount = TypicalEntries.getTypicalAccount();
        AddAccountCommand addAccountCommand = new AddAccountCommand(validAccount);
        ModelStub modelStub = new ModelStub();
        modelStub.addAccount(validAccount);
        ActiveAccountStub activeAccountStub = new ActiveAccountStub();

        assertThrows(CommandException.class, AddAccountCommand.MESSAGE_DUPLICATE_ACCOUNT, ()
            -> addAccountCommand.execute(modelStub, activeAccountStub));
    }

    @Test
    public void equals() {
        Account accountOne = new Account(new Name("Account 1"));
        Account accountTwo = new Account(new Name("Account 2"));
        AddAccountCommand addAccountOneCommand = new AddAccountCommand(accountOne);
        AddAccountCommand addAccountTwoCommand = new AddAccountCommand(accountTwo);

        // same object -> returns true
        assertTrue(addAccountOneCommand.equals(addAccountOneCommand));

        // same values -> returns true
        AddAccountCommand addAccountOneCommandCopy = new AddAccountCommand(accountOne);
        assertTrue(addAccountOneCommand.equals(addAccountOneCommandCopy));

        // different types -> returns false
        assertFalse(addAccountOneCommand.equals(1));

        // null -> returns false
        assertFalse(addAccountOneCommand.equals(null));

        // different person -> returns false
        assertFalse(addAccountOneCommand.equals(addAccountTwoCommand));
    }

}
