package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commandshortcut.CommandShortcutSet;
import seedu.address.model.Model;

public class ListShortcutCommand extends Command {
    public static final String COMMAND_WORD = "list_shortcut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all shortcuts.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(CommandShortcutSet.getInstance().toString());
    }
}
