package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Updates an existing stock in the stock book.
 */
public class BookmarkCommand extends Command {

    public static final String COMMAND_WORD = "bookmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Bookmarks important stocks with "
            + "the given serial number. \n"
            + "Parameters: "
            + PREFIX_SERIAL_NUMBER + "SERIAL NUMBER \n"
            + "You may provide more than one serial number \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "CS2103 ";


    public static final String MESSAGE_BOOKMARK_STOCK_SUCCESS = "Bookmarked Stock: %1$s";
    public static final String MESSAGE_NOT_BOOKMARKED = "At least one stock to bookmark must be provided.";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock is already bookmarked.";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists";

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
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stock> lastShownStocks = model.getFilteredStockList();

        List<SerialNumber> serialNumbers = targetSerialNumbers.stream().collect(Collectors.toList());
        List<String> serials = serialNumbers.stream().map((serial) -> serial.toString().trim())
                .collect(Collectors.toCollection(ArrayList::new));
        List<Stock> stocksToBookmark= new ArrayList<>();
        List<Stock> updatedStocks = new ArrayList<>();

        // Find stocks to be updated
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

        // Some serial numbers do not exist
        if (serials.size() != stocksToBookmark.size()) {
            throw new CommandException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        // Update stocks
        for (Stock stockToBookmark: stocksToBookmark) {
            stockToBookmark.setBookmarked();

            model.setStock(stockToBookmark, stockToBookmark);
            updatedStocks.add(stockToBookmark);
        }

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_BOOKMARK_STOCK_SUCCESS, stocksAsString(updatedStocks)));
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


}
