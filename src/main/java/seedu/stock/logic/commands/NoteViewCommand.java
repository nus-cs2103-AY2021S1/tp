package seedu.stock.logic.commands;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

public class NoteViewCommand extends Command {

    public static final String COMMAND_WORD = "noteview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all the notes of the stock identified "
            + "by the serial number of the stock.\n"
            + "Parameters:\n"
            + "sn/ [SERIAL NUMBER]\n"
            + "Example: " + COMMAND_WORD + " sn/111111";

    public static final String MESSAGE_NOTE_DISPLAY_SUCCESS = "Viewing notes for Stock: %1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists.";
    private static final String MESSAGE_STOCK_HAS_NO_NOTE = "Stock specified has no note.";

    private final SerialNumber serialNumber;

    /**
     * Constructs a NoteViewCommand.
     * @param serialNumber of the stock in the stock book
     */
    public NoteViewCommand(SerialNumber serialNumber) {
        requireAllNonNull(serialNumber);

        this.serialNumber = serialNumber;
    }

    /**
     * Executes the note view command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws CommandException If there are any errors.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> lastShownStocks = model.getFilteredStockList();

        Optional<Stock> stockToViewNotes = Optional.empty();
        // Find the stock to add note to
        for (Stock currentStock : lastShownStocks) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            if (currentStockSerialNumber.equals(serialNumber.getSerialNumberAsString())) {
                stockToViewNotes = Optional.of(currentStock);
                break;
            }
        }

        if (stockToViewNotes.isEmpty()) {
            throw new CommandException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        if (stockToViewNotes.get().getNotes().size() == 0) {
            throw new CommandException(MESSAGE_STOCK_HAS_NO_NOTE);
        }

        return new CommandResult(generateSuccessMessage(stockToViewNotes.get()), null,
                false, true, stockToViewNotes.get(), false, null, false);
    }

    /**
     * Generates a command execution success message when the notes of
     * {@code stockToViewNotes} is displayed.
     */
    private String generateSuccessMessage(Stock stockToViewNotes) {
        return String.format(MESSAGE_NOTE_DISPLAY_SUCCESS, stockToViewNotes);
    }

}
