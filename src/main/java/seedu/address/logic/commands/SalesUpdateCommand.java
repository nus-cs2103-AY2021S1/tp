package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSPM;

import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Drink;
import seedu.address.model.Model;


/**
 * Updates the sales of the drink items provided by the user.
 */
public class SalesUpdateCommand extends Command {

    public static final String COMMAND_WORD = "s-update";

    public static final int MAX_NUM_ALLOWED = 9999999;

    public static final String MESSAGE_MAX_NUM_ALLOWED_EXCEEDED = "Number of a drink sold should be a non-negative"
            + " unsigned integer that is less than or equal to " + MAX_NUM_ALLOWED + ".";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the sales of the drinks as entered. "
            + "Existing sales record will be overwritten by the input.\n"
            + "Parameters: DRINK [MORE_DRINKS] where DRINK is formatted as A/NUM.\n"
            + "A refers to the drink's abbreviation and NUM refers to the sales of that drink.\n"
            + "You must record the sales of at least one item, as a non-negative unsigned integer"
            + String.format(" that is less than %d.\n", MAX_NUM_ALLOWED)
            + "Example: " + COMMAND_WORD + " " + PREFIX_BSBM + "100" + " " + PREFIX_BSPM + "0";

    public static final String MESSAGE_SUCCESS = "Added sales to the record. You may use \'"
            + SalesListCommand.COMMAND_WORD + "\' to check the ordered current record.";

    private final Map<Drink, Integer> sales;

    public SalesUpdateCommand(Map<Drink, Integer> sales) {
        this.sales = sales;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.overwrite(sales);
        model.updateFilteredSalesRecordList(Model.PREDICATE_SHOW_ALL_SALES_RECORD_ENTRY);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SalesUpdateCommand)) {
            return false;
        }

        // state check
        SalesUpdateCommand e = (SalesUpdateCommand) other;
        return sales.equals(e.sales);
    }
}
