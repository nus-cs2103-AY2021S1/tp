package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER_DESCRIPTION;

import java.util.List;
import java.util.Optional;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

public class StockViewCommand extends Command {

    public static final String COMMAND_WORD = "stockview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all the details of the stock identified "
            + "by the serial number of the stock.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "ntuc1";

    public static final String MESSAGE_NOTE_DISPLAY_SUCCESS = "Viewing Stock: %1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists.";

    private final SerialNumber serialNumber;

    /**
     * Constructs a StockViewCommand.
     * @param serialNumber of the stock in the stock book
     */
    public StockViewCommand(SerialNumber serialNumber) {
        requireAllNonNull(serialNumber);

        this.serialNumber = serialNumber;
    }

    /**
     * Executes the stock view command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws SerialNumberNotFoundException If there are any errors.
     */
    @Override
    public CommandResult execute(Model model) throws SerialNumberNotFoundException {

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> stockList = model.getFilteredStockList();

        Stock stockToView = getStockFromSerialNumber(serialNumber, stockList);

        return new CommandResult(generateSuccessMessage(stockToView), null,
                false, true, stockToView, false, null, false, false);
    }

    /**
     * Returns a Stock found from the list of Stock using the given the Serial Number
     * @param serialNumber Serial Number of the Stock
     * @param stockList list of Stock
     * @throws SerialNumberNotFoundException if serial number is not found
     */
    private static Stock getStockFromSerialNumber(SerialNumber serialNumber, List<Stock> stockList)
            throws SerialNumberNotFoundException {
        requireNonNull(serialNumber);
        requireNonNull(stockList);

        Optional<Stock> stockToViewNotes = Optional.empty();

        // Find the stock to add note to
        for (Stock currentStock : stockList) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            if (currentStockSerialNumber.equals(serialNumber.getSerialNumberAsString())) {
                stockToViewNotes = Optional.of(currentStock);
                break;
            }
        }

        if (stockToViewNotes.isEmpty()) {
            throw new SerialNumberNotFoundException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        return stockToViewNotes.get();
    }

    /**
     * Generates a command execution success message when the notes of
     * {@code stockToViewNotes} is displayed.
     */
    private String generateSuccessMessage(Stock stockToViewNotes) {
        return String.format(MESSAGE_NOTE_DISPLAY_SUCCESS, stockToViewNotes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof StockViewCommand)) {
            return false;
        }
        // state check
        StockViewCommand otherStockViewCommand = (StockViewCommand) other;
        return serialNumber.equals(otherStockViewCommand.serialNumber);
    }

}
