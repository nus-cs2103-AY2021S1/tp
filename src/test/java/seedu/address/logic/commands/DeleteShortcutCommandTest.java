package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.logic.commandshortcut.CommandWord;


class DeleteShortcutCommandTest {

    private static final String VALID_SHORTCUT_TO_DELETE = "aa";
    private static final String NON_EXISTENT_SHORTCUT = "a_a";
    private static final String INVALID_SHORTCUT_TO_DELETE = "help";

    @Test
    void deleteFromModelSuccess() {
        setUpTypicalCommandShortcutSet();
        assertDoesNotThrow(()->assertEquals(new DeleteShortcutCommand(VALID_SHORTCUT_TO_DELETE)
                        .execute(ModelStub.TYPICAL_MODEL_STUB).getFeedbackToUser(),
                String.format(DeleteShortcutCommand.MESSAGE_DELETE_SHORTCUT_SUCCESS, VALID_SHORTCUT_TO_DELETE)));
    }

    @Test
    void deleteFromModelFailure() {
        setUpTypicalCommandShortcutSet();
        assertThrows(CommandException.class, () ->
                new DeleteShortcutCommand(NON_EXISTENT_SHORTCUT).execute(ModelStub.TYPICAL_MODEL_STUB));
        assertThrows(CommandException.class, () ->
                new DeleteShortcutCommand(INVALID_SHORTCUT_TO_DELETE).execute(ModelStub.TYPICAL_MODEL_STUB));
    }


    @Test
    void testEquals() {

        DeleteShortcutCommand deleteA = new DeleteShortcutCommand(VALID_SHORTCUT_TO_DELETE);
        DeleteShortcutCommand deleteACopy = new DeleteShortcutCommand(VALID_SHORTCUT_TO_DELETE);
        DeleteShortcutCommand deleteB = new DeleteShortcutCommand(INVALID_SHORTCUT_TO_DELETE);

        assertTrue(deleteA.equals(deleteACopy));

        assertTrue(deleteA.equals(deleteA));

        // different types -> returns false
        assertFalse(deleteA.equals(1));

        // null -> returns false
        assertFalse(deleteA.equals(null));

        // different shortcut -> returns false
        assertFalse(deleteA.equals(deleteB));
    }

    private void setUpTypicalCommandShortcutSet() {
        CommandShortcutSet.reset();

        assertDoesNotThrow(()->CommandShortcutSet.getInstance()
                .insertNewShortcut(CommandWord.DELETE_APPOINTMENT, "aa"));
    }
}
