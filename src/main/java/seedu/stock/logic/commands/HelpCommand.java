package seedu.stock.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Format: "
            + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Opened help window.";
    private static final Logger logger = LogsCenter.getLogger(HelpCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Opening help window");
        return new CommandResult(MESSAGE_SUCCESS, null, true, false,
                null, false, null, false, false);
    }
}
