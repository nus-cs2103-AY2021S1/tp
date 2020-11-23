package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commandshortcut.CommandShortcutSet;


class AddShortcutCommandTest {
    private static final String TYPICAL_DEFAULT_WORD = "add_client";
    private static final String TYPICAL_SHORTCUT = "a_a";
    private static final String INVALID_SHORTCUT = "sdf  sdfsdf";

    @Test
    void execute_addValidShortcut() {
        CommandShortcutSet.reset();
        assertDoesNotThrow(()-> assertEquals(
                String.format(AddShortcutCommand.MESSAGE_SUCCESS, TYPICAL_SHORTCUT, TYPICAL_DEFAULT_WORD),
                new AddShortcutCommand(TYPICAL_DEFAULT_WORD, TYPICAL_SHORTCUT)
                        .execute(ModelStub.TYPICAL_MODEL_STUB).getFeedbackToUser()));

    }

    @Test
    void execute_addInValidShortcut() {
        CommandShortcutSet.reset();
        assertThrows(CommandException.class, () -> new AddShortcutCommand(TYPICAL_DEFAULT_WORD, INVALID_SHORTCUT)
                .execute(ModelStub.TYPICAL_MODEL_STUB));
        assertThrows(CommandException.class, () -> new AddShortcutCommand(TYPICAL_DEFAULT_WORD, TYPICAL_DEFAULT_WORD)
                .execute(ModelStub.TYPICAL_MODEL_STUB));

    }
}
