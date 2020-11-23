package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER_DESCRIPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.SortUtil;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Updates an existing stock in the stock book.
 */
public class BookmarkCommand extends Command {

    public static final String COMMAND_WORD = "bookmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Bookmarks stocks in the inventory.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + " "
            + "[" + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + "]...\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "China3 ";


    public static final String MESSAGE_BOOKMARK_STOCK_SUCCESS = "Bookmarked Stock:%1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists:"
            + ": %1$s";
    public static final String MESSAGE_ALREADY_BOOKMARKED = "Stock with given serial number is already bookmarked"
            + ": %1$s";
    private static final Logger logger = LogsCenter.getLogger(BookmarkCommand.class);

    private final Set<SerialNumber> targetSerialNumbers;

    public BookmarkCommand (Set<SerialNumber> targetSerialNumbers) {
        this.targetSerialNumbers = targetSerialNumbers;
    }

    /**
     * Executes the bookmark command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws CommandException If there are any errors.
     */
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "Starting to execute bookmark command");
        requireNonNull(model);
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> lastShownStocks = model.getFilteredStockList();

        List<SerialNumber> serialNumbers = targetSerialNumbers.stream().collect(Collectors.toList());
        List<String> serials = serialNumbers.stream().map((serial) -> serial.toString().trim())
                .collect(Collectors.toCollection(ArrayList::new));
        List<Stock> stocksToBookmark = new ArrayList<>();
        List<String> stocksNotFound = new ArrayList<>();
        List<Stock> notUpdatedStocks = new ArrayList<>();
        List<Stock> updatedStocks = new ArrayList<>();

        // Find serials that are not found
        for (String currentSerialNumber : serials) {
            boolean noMatches = true;
            for (Stock currentStock : lastShownStocks) {
                String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
                if (currentSerialNumber.equals(currentStockSerialNumber)) {
                    noMatches = false;
                }
            }
            if (noMatches) {
                stocksNotFound.add(currentSerialNumber);
            }
        }

        // Find stocks to bookmark
        for (Stock currentStock : lastShownStocks) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            boolean anyMatches = false;

            for (String currentSerialNumber : serials) {
                if (currentSerialNumber.equals(currentStockSerialNumber)) {
                    anyMatches = true;
                }
            }
            if (anyMatches) {
                stocksToBookmark.add(currentStock);
            }
        }

        // Bookmark stocks
        for (Stock stockToBookmark: stocksToBookmark) {
            if (stockToBookmark.getIsBookmarked()) {
                notUpdatedStocks.add(stockToBookmark);
            } else {
                stockToBookmark.setBookmarked();
                model.setStock(stockToBookmark, stockToBookmark);
                updatedStocks.add(stockToBookmark);
            }

        }



        if (stocksNotFound.size() == serialNumbers.size()) {
            throw new CommandException(String.format(MESSAGE_SERIAL_NUMBER_NOT_FOUND , arrayAsString(stocksNotFound)));
        } else if (notUpdatedStocks.size() == serialNumbers.size()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_BOOKMARKED, stocksAsString(notUpdatedStocks)));
        } else if (updatedStocks.size() == serialNumbers.size()) {

            model.sortFilteredStockList(SortUtil.generateGeneralComparator());

            logger.log(Level.INFO, "Finished bookmarking stocks successfully");
            return new CommandResult(String.format(MESSAGE_BOOKMARK_STOCK_SUCCESS , stocksAsString(updatedStocks)));
        } else if (notUpdatedStocks.size() > 0 && stocksNotFound.size() > 0) {
            String result = String.format(MESSAGE_SERIAL_NUMBER_NOT_FOUND , arrayAsString(stocksNotFound))
                    + "\n" + String.format(MESSAGE_ALREADY_BOOKMARKED, stocksAsString(notUpdatedStocks));
            throw new CommandException(result);
        } else if (notUpdatedStocks.size() == 0 && stocksNotFound.size() > 0) {
            String result = String.format(MESSAGE_SERIAL_NUMBER_NOT_FOUND , arrayAsString(stocksNotFound))
                    + "\n" + String.format(MESSAGE_BOOKMARK_STOCK_SUCCESS , stocksAsString(updatedStocks));

            model.sortFilteredStockList(SortUtil.generateGeneralComparator());

            logger.log(Level.INFO, "Finished bookmarking stocks successfully");
            return new CommandResult(result);
        } else if (stocksNotFound.size() == 0 && notUpdatedStocks.size() > 0) {
            String result = String.format(MESSAGE_ALREADY_BOOKMARKED , stocksAsString(notUpdatedStocks))
                    + "\n" + String.format(MESSAGE_BOOKMARK_STOCK_SUCCESS , stocksAsString(updatedStocks));

            model.sortFilteredStockList(SortUtil.generateGeneralComparator());

            logger.log(Level.INFO, "Finished bookmarking stocks successfully");
            return new CommandResult(result);
        } else {
            String result = String.format(MESSAGE_ALREADY_BOOKMARKED , stocksAsString(notUpdatedStocks))
                    + "\n" + String.format(MESSAGE_SERIAL_NUMBER_NOT_FOUND , arrayAsString(stocksNotFound))
                    + "\n" + String.format(MESSAGE_BOOKMARK_STOCK_SUCCESS , stocksAsString(updatedStocks));

            model.sortFilteredStockList(SortUtil.generateGeneralComparator());

            logger.log(Level.INFO, "Finished bookmarking stocks successfully");
            return new CommandResult(result);
        }

    }

    /**
     * Displays the list of stocks in a clearer view, with each subsequent stock moved to the next line.
     *
     * @param stockList The list of stocks to be converted to String.
     * @return The String listing all stocks in the list.
     */
    private String stocksAsString(List<Stock> stockList) {
        String stocksAsString = "";
        for (int i = 0; i < stockList.size(); i++) {
            stocksAsString += "\n" + stockList.get(i).toString();
        }
        return stockList.size() == 0 ? "No stocks updated" : stocksAsString;
    }

    /**
     * Displays the list of serial numbers in a clearer view, with each subsequent serial number moved
     * to the next line.
     *
     * @param serialNumberList The list of serial numbers to convert to String.
     * @return The String depicting each serial number in the list.
     */
    public String serialNumberListAsString(List<SerialNumber> serialNumberList) {
        String serialNumbersAsString = "";
        for (int i = 0; i < serialNumberList.size(); i++) {
            serialNumbersAsString += "\n" + serialNumberList.get(i).toString();
        }
        return serialNumbersAsString;
    }

    /**
     * Displays the list of strings in a clearer view, with each subsequent string moved
     * to the next line.
     *
     * @param stringList The list of serial numbers to convert to String.
     * @return The String depicting each serial number in the list.
     */
    public String arrayAsString(List<String> stringList) {
        String serialNumbersAsString = "";
        for (int i = 0; i < stringList.size(); i++) {
            serialNumbersAsString += "\n" + stringList.get(i);
        }
        serialNumbersAsString += "\n";

        return serialNumbersAsString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookmarkCommand // instanceof handles nulls
                && targetSerialNumbers.equals(((BookmarkCommand) other).targetSerialNumbers)); // state check
    }


}
