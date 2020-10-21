package seedu.stock.logic.commands;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_TYPE;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.SortUtil;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the inventory list according to "
            + "the given argument. \n"
            + "Parameters: "
            + PREFIX_SORT_TYPE + "FIELD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_TYPE + "name";

    public static final String MESSAGE_SORT_STOCK_SUCCESS = "Sorted stock by %1$s";
    public static final String MESSAGE_INVALID_FIELD = "The field to be sorted by is not recognized.";

    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);

    private String fieldToSort;

    public SortCommand(String fieldToSort) {
        this.fieldToSort = fieldToSort;
    }

    @Override
    public CommandResult execute(Model model) {
        Comparator<Stock> comparator = SortUtil.generateComparator(fieldToSort);
        model.sortFilteredStockList(comparator);
        logger.log(Level.INFO, "Sorting successful");
        return new CommandResult(String.format(MESSAGE_SORT_STOCK_SUCCESS, SortUtil.getFieldDescription(fieldToSort)));
    }
}
