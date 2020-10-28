package seedu.resireg.logic.commands;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Toggles between showing students and rooms as separate tabs and showing students and rooms as one combined tab.
 */
public class ToggleTabSplitCommand extends Command {
    public static final String COMMAND_WORD = "toggle-split";

    public static final String MESSAGE_SUCCESS = "Toggled students/rooms split.";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Toggles between showing students and rooms as separate tabs and showing them as one combined tab");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        return new ToggleTabSplitCommandResult(MESSAGE_SUCCESS);
    }
}
