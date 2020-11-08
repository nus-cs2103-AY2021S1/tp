//@@author EkamSinghPandher
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class DeleteModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void execute_validModuleUnfilteredList_success() {
        Module deletedModule = new ModuleBuilder(CS2100).build();
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(
                CS2100.getModuleName());

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, deletedModule);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.deleteModule(model.getFilteredModuleList().get(0));

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentModule() {
        Module nonExistantModule = new ModuleBuilder(CS2100)
                .withName("NONEXISTANT")
                .build();
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(nonExistantModule.getModuleName());
        String expectedMessage = Messages.MESSAGE_INVALID_MODULE_DISPLAYED;

        assertThrows(CommandException.class, expectedMessage, () ->
                deleteModuleCommand.execute(model));
    }

    @Test
    public void execute_deleteModuleInSelectedMeeting_updatesSelectedMeeting() throws CommandException {
        Meeting selectedMeeting = model.getSelectedMeeting();

        new DeleteModuleCommand(selectedMeeting.getModule().getModuleName()).execute(model);

        assertNull(model.getSelectedMeeting());
    }

    @Test
    public void equals() {
        DeleteModuleCommand moduleCommand1 = new DeleteModuleCommand(CS2100.getModuleName());
        DeleteModuleCommand moduleCommand2 = new DeleteModuleCommand(CS2101.getModuleName());

        // same object -> returns true
        assertTrue(moduleCommand2.equals(moduleCommand2));

        // same values -> returns true
        DeleteModuleCommand moduleCommand1Copy = new DeleteModuleCommand(CS2100.getModuleName());
        assertTrue(moduleCommand1.equals(moduleCommand1Copy));

        // different types -> returns false
        assertFalse(moduleCommand2.equals(1));

        // null -> returns false
        assertFalse(moduleCommand2.equals(null));

        // different meeting -> returns false
        assertFalse(moduleCommand2.equals(moduleCommand1));
    }
}
