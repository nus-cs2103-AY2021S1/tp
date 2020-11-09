package tp.acecs2103.logic.commands;

import tp.acecs2103.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "Please refer to User Guide here: "
            + "https://github.com/AY2021S1-CS2103-T14-4/tp/blob/master/docs/UserGuide.md";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof HelpCommand)) {
            return false;
        } else {
            return true;
        }
    }
}
