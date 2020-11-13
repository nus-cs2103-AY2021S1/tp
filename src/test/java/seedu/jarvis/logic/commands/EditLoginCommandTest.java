package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_PROF;
import static seedu.jarvis.logic.commands.CommandTestUtil.DESC_STUDENT;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_USERNAME_PROF;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.edit.EditLoginCommand;
import seedu.jarvis.logic.commands.edit.EditLoginCommand.EditLoginDescriptor;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.testutil.EditLoginDescriptorBuilder;
import seedu.jarvis.testutil.LoginBuilder;
import seedu.jarvis.testutil.TypicalManagers;

public class EditLoginCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
            TypicalManagers.getUserLogin());

    @Test
    public void execute_allFieldsSpecified_success() {
        UserLogin editedUserLogin = new LoginBuilder().build();
        EditLoginDescriptor descriptor = new EditLoginDescriptorBuilder(editedUserLogin).build();
        EditLoginCommand editLoginCommand = new EditLoginCommand(descriptor);

        String expectedMessage = String.format(EditLoginCommand.MESSAGE_EDIT_LOGIN_SUCCESS, editedUserLogin);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.setUserLogin(editedUserLogin);

        assertCommandSuccess(editLoginCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        UserLogin editedUserLogin = new LoginBuilder().withUsername(VALID_USERNAME_PROF).build();
        EditLoginDescriptor descriptor = new EditLoginDescriptorBuilder(editedUserLogin).build();
        EditLoginCommand editLoginCommand = new EditLoginCommand(descriptor);

        String expectedMessage = String.format(EditLoginCommand.MESSAGE_EDIT_LOGIN_SUCCESS, editedUserLogin);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel.setUserLogin(editedUserLogin);

        assertCommandSuccess(editLoginCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldsSpecified_success() {
        EditLoginCommand editLoginCommand = new EditLoginCommand(new EditLoginDescriptor());
        UserLogin editedUserLogin = new UserLogin();

        String expectedMessage = String.format(EditLoginCommand.MESSAGE_EDIT_LOGIN_SUCCESS, editedUserLogin);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        assertCommandSuccess(editLoginCommand, model, expectedMessage, expectedModel);
    }

    // One for allFields specified, one for one field specified, so on.
    @Test
    public void execute_allFieldsSpecified_commandTargetFeatureNotAssigned() {
        UserLogin editedUserLogin = new LoginBuilder().build();
        EditLoginDescriptor descriptor = new EditLoginDescriptorBuilder(editedUserLogin).build();
        EditLoginCommand editLoginCommand = new EditLoginCommand(descriptor);

        CommandResult commandResult = editLoginCommand.execute(model);
        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.NotAssigned, actualTargetTab);
    }

    @Test
    public void execute_someFieldsSpecified_commandTargetFeatureNotAssigned() {
        UserLogin editedUserLogin = new LoginBuilder().withUsername(VALID_USERNAME_PROF).build();
        EditLoginDescriptor descriptor = new EditLoginDescriptorBuilder(editedUserLogin).build();
        EditLoginCommand editLoginCommand = new EditLoginCommand(descriptor);

        CommandResult commandResult = editLoginCommand.execute(model);
        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.NotAssigned, actualTargetTab);
    }

    @Test
    public void execute_noFieldsSpecified_commandTargetFeatureNotAssigned() {
        EditLoginCommand editLoginCommand = new EditLoginCommand(new EditLoginDescriptor());

        CommandResult commandResult = editLoginCommand.execute(model);
        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.NotAssigned, actualTargetTab);
    }

    @Test
    public void equals() {
        final EditLoginCommand standardCommand = new EditLoginCommand(DESC_STUDENT);

        // same values -> returns true
        EditLoginDescriptor copyDescriptor = new EditLoginDescriptor(DESC_STUDENT);
        EditLoginCommand commandWithSameValues = new EditLoginCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditLoginCommand(DESC_PROF)));
    }
}
