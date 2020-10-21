package seedu.stock.logic.commands;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
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
            + PREFIX_SORT_ORDER + "ORDER"
            + PREFIX_SORT_TYPE + "FIELD \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_TYPE + "name";

    public static final String MESSAGE_SORT_STOCK_SUCCESS = "Sorted stock by %1$s";
    public static final String MESSAGE_INVALID_FIELD = "The field to be sorted by is not recognized.";
    public static final String MESSAGE_INVALID_ORDER = "The order to be sorted is not recognized.";

    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);

    private String fieldToSort;
    private boolean isReversed;

    public SortCommand(String fieldToSort, boolean isReversed) {
        this.fieldToSort = fieldToSort;
        this.isReversed = isReversed;
    }

    @Override
    public CommandResult execute(Model model) {
        Comparator<Stock> comparator = isReversed ? SortUtil.generateReverseComparator(fieldToSort)
                : SortUtil.generateComparator(fieldToSort);
        model.sortFilteredStockList(comparator);
        logger.log(Level.INFO, "Sorting successful");
        return new CommandResult(String.format(MESSAGE_SORT_STOCK_SUCCESS, SortUtil.getFieldDescription(fieldToSort)));
    }
}
