package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.LowStocksPredicate;

/**
 * Lists all stocks in the stock book to the user.
 */
public class ListLowStocksCommand extends ListCommand {

    public static final String LIST_WORD = "low";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory that are low in quantity";

    public static final Predicate<Stock> IS_LOW_STOCK = new LowStocksPredicate();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStockList(IS_LOW_STOCK);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
