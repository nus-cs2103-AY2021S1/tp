package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Sets the theme to light theme.
 */
public class LightThemeCommand extends Command {
    public static final String COMMAND_WORD = "light";

    public static final String SUCCESS_MESSAGE = "Light theme set successfully.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SUCCESS_MESSAGE, true, false);
    }
}
