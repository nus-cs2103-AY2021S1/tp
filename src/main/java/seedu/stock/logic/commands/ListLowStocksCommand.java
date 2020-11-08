package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
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

    private static final Logger logger = LogsCenter.getLogger(ListLowStocksCommand.class);


    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute list low stocks command");
        requireNonNull(model);
        model.updateFilteredStockList(IS_LOW_STOCK);
        logger.log(Level.INFO, "Finished listing all low stocks successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
