package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.BookmarkedPredicate;

/**
 * Lists all stocks in the stock book to the user.
 */
public class ListBookmarkCommand extends ListCommand {

    public static final String LIST_WORD = "bookmark";

    public static final String MESSAGE_SUCCESS = "Listing all bookmarked stocks in inventory";

    public static final Predicate<Stock> IS_BOOKMARKED = new BookmarkedPredicate();

    private static final Logger logger = LogsCenter.getLogger(ListBookmarkCommand.class);


    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute list bookmark command");
        requireNonNull(model);
        model.updateFilteredStockList(IS_BOOKMARKED);
        logger.log(Level.INFO, "Finished listing all bookmarked stocks successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
