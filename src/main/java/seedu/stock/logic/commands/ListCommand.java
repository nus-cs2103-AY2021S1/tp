package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.model.Model.PREDICATE_SHOW_ALL_STOCKS;

import seedu.stock.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all stocks in the inventory \n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStockList(PREDICATE_SHOW_ALL_STOCKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
