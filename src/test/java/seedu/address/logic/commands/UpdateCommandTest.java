package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.UPDESC_1;
import static seedu.address.logic.commands.CommandTestUtil.UPDESC_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPerson.getTypicalMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPerson;
import seedu.address.testutil.UpdateProfileDescriptorBuilder;

public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());

    @Test
    public void execute_isDefaultProfile_failure() {
        Person editedPerson = TypicalPerson.PERSON1;
        UpdateCommand.UpdateProfileDescriptor descriptor =
                new UpdateProfileDescriptorBuilder(editedPerson.getProfile()).build();
        UpdateCommand updateCommand = new UpdateCommand(descriptor);
        String expectedMessage = UpdateCommand.MESSAGE_ERROR;
        assertCommandFailure(updateCommand, model, expectedMessage);
    }
    @Test
    public void execute_sameID_failure() {
        Person editedPerson = TypicalPerson.PERSON1;
        model.setCurrentPerson(editedPerson);
        UpdateCommand.UpdateProfileDescriptor descriptor =
                new UpdateProfileDescriptorBuilder(editedPerson.getProfile()).build();
        UpdateCommand updateCommand = new UpdateCommand(descriptor);
        String expectedMessage = UpdateCommand.MESSAGE_SAME_ID;
        assertCommandFailure(updateCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final UpdateCommand standardCommand = new UpdateCommand(UPDESC_1);

        // same values -> returns true
        UpdateCommand.UpdateProfileDescriptor copyDescriptor = new UpdateCommand.UpdateProfileDescriptor(UPDESC_1);
        UpdateCommand commandWithSameValues = new UpdateCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(UPDESC_2)));
    }
}
