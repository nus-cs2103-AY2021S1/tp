package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SalesRecordEntry;

/**
 * Ranks the drink items in the SalesBook based on the number od
 */
public class SalesRankCommand extends Command {

    public static final String COMMAND_WORD = "s-rank";

    public static final String MESSAGE_USAGE = ": Lists the sales of drink items in a ranked manner.\n"
            + "The first drink in the list is the most popular drink sold.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "s-rank not yet implemented.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

}
