package seedu.cc.logic.commands.accountlevel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;

public class EditAccountNameCommandTest {
    private static final Account GENERAL_ACCOUNT = new Account(new Name("General account"));
    private static final Name NEW_NAME = new Name("New Name");
    private static final Name NAME_ONE = new Name("Name One");
    private static final Name NAME_TWO = new Name("Name Two");
    private static final int ONE = 1;

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
        Name newName = NEW_NAME;
        EditAccountNameCommand editAccountNameCommand = new EditAccountNameCommand(newName);

        Model expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());
        expectedModel.setAccount(GENERAL_ACCOUNT, new Account(newName));

        String expectedMessage = String.format(EditAccountNameCommand.MESSAGE_SUCCESS, currentName.toString(),
                newName.toString());

        assertCommandSuccess(editAccountNameCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editSameName_failure() {
        Name name = GENERAL_ACCOUNT.getName();
        EditAccountNameCommand editAccountNameCommand = new EditAccountNameCommand(name);

        String expectedMessage = EditAccountNameCommand.MESSAGE_NAME_UNCHANGED;

        assertCommandFailure(editAccountNameCommand, model, expectedMessage);
    }

    @Test
    public void execute_editDuplicatedName_failure() {
        Name newName = NEW_NAME;
        model.addAccount(new Account(newName));
        EditAccountNameCommand editAccountNameCommand = new EditAccountNameCommand(newName);

        String expectedMessage = EditAccountNameCommand.MESSAGE_DUPLICATED_NAME;

        assertCommandFailure(editAccountNameCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Name nameOne = NAME_ONE;
        Name nameTwo = NAME_TWO;
        EditAccountNameCommand editAccountNameOneCommand = new EditAccountNameCommand(nameOne);
        EditAccountNameCommand editAccountNameTwoCommand = new EditAccountNameCommand(nameTwo);

        // same object -> returns true
        assertTrue(editAccountNameOneCommand.equals(editAccountNameOneCommand));

        // same values -> returns true
        EditAccountNameCommand addAccountNameOneCommandCopy = new EditAccountNameCommand(nameOne);
        assertTrue(editAccountNameOneCommand.equals(addAccountNameOneCommandCopy));

        // different types -> returns false
        assertFalse(editAccountNameOneCommand.equals(ONE));

        // null -> returns false
        assertFalse(editAccountNameOneCommand.equals(null));

        // different person -> returns false
        assertFalse(editAccountNameOneCommand.equals(editAccountNameTwoCommand));
    }

}
