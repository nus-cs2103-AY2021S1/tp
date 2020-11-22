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


public class EditBlockCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validBlockRange_success() throws CommandException {
        CommandResult commandResult = new EditBlockCommand('A', 'Z').execute(model);
        // compare output
        assertEquals(String.format(EditBlockCommand.MESSAGE_EDIT_BLOCK_SUCCESS, 'A', 'Z'),
            commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidFirstBlockLetter_throwsCommandException() {
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_FIRST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('1', 'Z').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_FIRST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('!', 'Z').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_FIRST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('+', 'Z').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_FIRST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('a', 'Z').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_FIRST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('z', 'Z').execute(model));
    }

    @Test
    public void execute_invalidLastBlockLetter_throwsCommandException() {
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_LAST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('A', '1').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_LAST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('A', '!').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_LAST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('A', '+').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_LAST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('A', 'a').execute(model));
        assertThrows(CommandException.class,
            String.format(EditBlockCommand.MESSAGE_INVALID_LAST_BLOCK_LETTER, UserPrefs.MIN_ALLOWED_BLOCK_LETTER,
                UserPrefs.MAX_ALLOWED_BLOCK_LETTER), () -> new EditBlockCommand('A', 'z').execute(model));
    }

    @Test
    public void equals() {
        final EditBlockCommand standardCommand = new EditBlockCommand('A', 'D');

        // same values -> equals
        assertEquals(standardCommand, new EditBlockCommand('A', 'D'));

        // different values -> not equals
        assertNotEquals(standardCommand, new EditBlockCommand('A', 'E'));
        assertNotEquals(standardCommand, new EditBlockCommand('E', 'G'));

        // same object -> equals
        assertEquals(standardCommand, standardCommand);

        // null -> not equals
        assertNotEquals(null, standardCommand);

        // different types -> not equals
        assertNotEquals(standardCommand, new ClearCommand());

    }
}
