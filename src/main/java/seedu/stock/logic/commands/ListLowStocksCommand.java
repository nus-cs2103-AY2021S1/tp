package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.LowStocksPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListLowStocksCommand extends ListCommand {

    public static final String LIST_WORD = "low";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory that are low in quantity";

    public static final Predicate<Stock> isLowStock = new LowStocksPredicate();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStockList(isLowStock);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
