package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.ModuleBuilder;

public class EditModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditModuleCommand(null, null));
    }

    @Test
    public void execute_editModuleInSelectedMeeting_updatesSelectedMeeting() throws CommandException {
        Meeting selectedMeeting = model.getSelectedMeeting();
        Module selectedModule = selectedMeeting.getModule();

        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder(selectedMeeting.getModule()).withModuleName("Blah").build();

        new EditModuleCommand(selectedMeeting.getModule().getModuleName(), descriptor).execute(model);

        Module expectedModule = new ModuleBuilder(selectedModule).withName("Blah").build();
        Meeting expectedMeeting = new MeetingBuilder(selectedMeeting).withModule(expectedModule).build();

        assertEquals(model.getSelectedMeeting(), expectedMeeting);
    }
}
