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


public class EditFloorCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validFloorRange_success() throws CommandException {
        CommandResult commandResult = new EditFloorCommand(1, 9).execute(model);
        // compare output
        assertEquals(String.format(EditFloorCommand.MESSAGE_EDIT_FLOOR_SUCCESS, 1, 9),
            commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidMinFloor_throwsCommandException() {
        EditFloorCommand editFloorCommand = new EditFloorCommand(0, 4);

        assertThrows(CommandException.class,
            String.format(EditFloorCommand.MESSAGE_INVALID_MIN_FLOOR, UserPrefs.MIN_ALLOWED_FLOORS,
                UserPrefs.MAX_ALLOWED_FLOORS), () -> editFloorCommand.execute(model));
    }

    @Test
    public void execute_invalidMaxFloor_throwsCommandException() {
        EditFloorCommand editFloorCommand = new EditFloorCommand(1, 12);

        assertThrows(CommandException.class,
            String.format(EditFloorCommand.MESSAGE_INVALID_MAX_FLOOR, UserPrefs.MIN_ALLOWED_FLOORS,
                UserPrefs.MAX_ALLOWED_FLOORS), () -> editFloorCommand.execute(model));
    }

    @Test
    public void equals() {
        final EditFloorCommand standardCommand = new EditFloorCommand(1, 4);

        // same values -> equals
        assertEquals(standardCommand, new EditFloorCommand(1, 4));

        // different values -> not equals
        assertNotEquals(standardCommand, new EditFloorCommand(1, 10));
        assertNotEquals(standardCommand, new EditFloorCommand(2, 4));

        // same object -> equals
        assertEquals(standardCommand, standardCommand);

        // null -> not equals
        assertNotEquals(null, standardCommand);

        // different types -> not equals
        assertNotEquals(standardCommand, new ClearCommand());

    }
}
