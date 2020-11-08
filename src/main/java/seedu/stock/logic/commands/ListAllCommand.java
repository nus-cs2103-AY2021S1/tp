package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.Model;

/**
 * Lists all stocks in the stock book to the user.
 */
public class ListAllCommand extends ListCommand {

    public static final String LIST_WORD = "all";

    public static final String MESSAGE_SUCCESS = "Listing all stocks in inventory";

    private static final Logger logger = LogsCenter.getLogger(ListAllCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute list all command");
        requireNonNull(model);
        model.sortFilteredStockList(SortUtil.generateGeneralComparator());
        logger.log(Level.INFO, "Finished listing all stocks successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
