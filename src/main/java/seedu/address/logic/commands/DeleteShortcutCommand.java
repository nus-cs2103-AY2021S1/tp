package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.logic.commandshortcut.exceptions.CommandWordException;
import seedu.address.model.Model;

public class DeleteShortcutCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete_shortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the shortcut.\n"
            + "Parameters: SHORTCUT\n"
            + "Example: " + COMMAND_WORD + " a_a";

    public static final String MESSAGE_DELETE_SHORTCUT_SUCCESS = "Deleted Shortcut: %1$s";

    private String shortcutToDelete;

    public DeleteShortcutCommand(String alias) {
        this.shortcutToDelete = alias;
    }

    @Override
    public String deleteFromModel(Model model) throws CommandException {

        try {
            CommandShortcutSet.getInstance().deleteShortcut(shortcutToDelete);
        } catch (CommandWordException ex) {
            throw new CommandException(ex.getMessage());
        }
        return String.format(MESSAGE_DELETE_SHORTCUT_SUCCESS, shortcutToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other != null && other.getClass() == this.getClass()
                && shortcutToDelete.equals(((DeleteShortcutCommand) other).shortcutToDelete)); // state check
    }
}
