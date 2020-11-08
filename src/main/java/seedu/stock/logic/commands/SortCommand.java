package seedu.stock.logic.commands;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_FIELD_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER_DESCRIPTION;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.SortUtil;
import seedu.stock.commons.util.SortUtil.Field;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

/**
 * Sorts existing stocks in the stock book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the inventory list according to "
            + "the given argument. \n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_SORT_ORDER + PREFIX_SORT_ORDER_DESCRIPTION + " "
            + PREFIX_SORT_FIELD + PREFIX_SORT_FIELD_DESCRIPTION + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SORT_ORDER + "descending "
            + PREFIX_SORT_FIELD + "quantity";

    public static final String MESSAGE_SORT_STOCK_SUCCESS = "Sorted stock by %1$s";
    public static final String MESSAGE_INVALID_FIELD = "The field to be sorted by is not recognized.";
    public static final String MESSAGE_INVALID_ORDER = "The order to be sorted is not recognized.";

    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);

    private Field fieldToSort;
    private boolean isReversed;

    /**
     * Constructs a new sort command object.
     *
     * @param fieldToSort The field to be sorted.
     * @param isReversed Indicates if the order will be ascending or descending.
     */
    public SortCommand(Field fieldToSort, boolean isReversed) {
        this.fieldToSort = fieldToSort;
        this.isReversed = isReversed;
    }

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute sort command");
        Comparator<Stock> comparator = isReversed ? SortUtil.generateReverseComparator(fieldToSort)
                : SortUtil.generateComparator(fieldToSort);
        model.sortFilteredStockList(comparator);
        logger.log(Level.INFO, "Finished sorting successfully");
        return new CommandResult(String.format(MESSAGE_SORT_STOCK_SUCCESS, SortUtil.getFieldDescription(fieldToSort)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand castedOther = (SortCommand) other;
        return fieldToSort.equals(castedOther.fieldToSort) && isReversed == castedOther.isReversed;
    }
}
