package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.Model;

/**
 * Toggles the tabs in Warenager.
 */
public class TabCommand extends Command {

    public static final String COMMAND_WORD = "tab";
    public static final String MESSAGE_SUCCESS = "Tab has been switched!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles to the next tab.\n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;;
    private static final Logger logger = LogsCenter.getLogger(TabCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.log(Level.INFO, "Switching tabs");
        return new CommandResult(MESSAGE_SUCCESS, null, false, false, null, false, null, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof TabCommand);
    }
}
