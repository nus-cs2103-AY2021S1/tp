package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Sets the theme to dark theme.
 */
public class DarkThemeCommand extends Command {
    public static final String COMMAND_WORD = "dark";

    public static final String SUCCESS_MESSAGE = "Dark theme set successfully.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SUCCESS_MESSAGE, false, true);
    }
}
