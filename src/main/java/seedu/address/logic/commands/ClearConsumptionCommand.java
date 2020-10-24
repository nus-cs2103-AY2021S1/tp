package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearConsumptionCommand extends Command {

    public static final String COMMAND_WORD = "clearC";
    public static final String MESSAGE_SUCCESS = "Daily consumption has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearConsumption();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
