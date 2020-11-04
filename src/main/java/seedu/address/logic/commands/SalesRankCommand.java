package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Ranks the drink items in the SalesBook based on the number od
 */
public class SalesRankCommand extends Command {

    public static final String COMMAND_WORD = "s-rank";

    public static final String MESSAGE_USAGE = ": Lists the sales of drink items in a ranked manner.\n"
            + "The first drink in the list is the most popular drink sold.";

    public static final String MESSAGE_SUCCESS = "The list of drinks items has been ranked and is shown in the"
            + " Sales Tracker below.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        model.sortSalesBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
