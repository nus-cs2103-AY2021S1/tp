package seedu.address.logic.commands;

import seedu.address.model.Model;

public class ToggleCommand extends Command {
    public static final String COMMAND_WORD = "toggle";

    public static final String MESSAGE_TOGGLE_ACKNOWLEDGEMENT = "The view of the app has been toggled as requested";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_TOGGLE_ACKNOWLEDGEMENT, false, false,
                false, true);
    }
}
