package seedu.address.logic.commands.consumption;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearConsumptionCommand extends Command {

    public static final String COMMAND_WORD = "clearC";

    public static final String MESSAGE_SUCCESS = "Daily consumption has been cleared!";

    private static final Logger logger = Logger.getLogger("ClearConsumptionLogger");

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Clearing Consumption List .......");
        requireNonNull(model);
        model.clearConsumption();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
