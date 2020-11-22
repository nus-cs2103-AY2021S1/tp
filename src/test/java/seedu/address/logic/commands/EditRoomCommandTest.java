package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class EditRoomCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validRoomRange_success() throws CommandException {
        CommandResult commandResult = new EditRoomCommand(1, 99).execute(model);
        // compare output
        assertEquals(String.format(EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS, 1, 99),
            commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidMinRoom_throwsCommandException() {
        EditRoomCommand editRoomCommand = new EditRoomCommand(0, 20);

        assertThrows(CommandException.class,
            String.format(EditRoomCommand.MESSAGE_INVALID_MIN_ROOM, UserPrefs.MIN_ALLOWED_ROOMS,
                UserPrefs.MAX_ALLOWED_ROOMS), () -> editRoomCommand.execute(model));
    }

    @Test
    public void execute_invalidMaxRoom_throwsCommandException() {
        EditRoomCommand editRoomCommand = new EditRoomCommand(1, 101);

        assertThrows(CommandException.class,
            String.format(EditRoomCommand.MESSAGE_INVALID_MAX_ROOM, UserPrefs.MIN_ALLOWED_ROOMS,
                UserPrefs.MAX_ALLOWED_ROOMS), () -> editRoomCommand.execute(model));
    }

    @Test
    public void equals() {
        final EditRoomCommand standardCommand = new EditRoomCommand(1, 20);

        // same values -> equals
        assertEquals(standardCommand, new EditRoomCommand(1, 20));

        // different values -> not equals
        assertNotEquals(standardCommand, new EditRoomCommand(1, 10));
        assertNotEquals(standardCommand, new EditRoomCommand(2, 20));

        // same object -> equals
        assertEquals(standardCommand, standardCommand);

        // null -> not equals
        assertNotEquals(null, standardCommand);

        // different types -> not equals
        assertNotEquals(standardCommand, new ClearCommand());

    }
}
