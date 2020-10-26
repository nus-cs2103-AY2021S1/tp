package seedu.resireg.logic.commands;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.storage.Storage;

/**
 * Toggles the UI layout betweenn a tabbed view and a side-by-side split view.
 */
public class ToggleMainPanelCommand extends Command {
    public static final String COMMAND_WORD = CommandWordEnum.TOGGLE_MAIN_PANEL_COMMAND.toString();

    public static final String MESSAGE_SUCCESS = "Switched main panel layout.";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Toggles the main panel layout between a tabbed view and a split view.");

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        return new ToggleMainPanelCommandResult(MESSAGE_SUCCESS);
    }
}
