package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER_DESCRIPTION;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Adds a note to an existing stock in the stock book.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the stock identified "
            + "by the serial number of the stock.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + " "
            + PREFIX_NOTE + PREFIX_NOTE_DESCRIPTION + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "shengsiong1 "
            + PREFIX_NOTE + "chicken will expire soon";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Stock: %1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists";

    private static final Logger logger = LogsCenter.getLogger(NoteCommand.class);

    private final SerialNumber serialNumber;
    private final Note note;

    /**
     * Constructs a NoteCommand
     * @param serialNumber of the stock in the stock book
     * @param note to add to the stock
     */
    public NoteCommand(SerialNumber serialNumber, Note note) {
        requireAllNonNull(serialNumber, note);

        this.serialNumber = serialNumber;
        this.note = note;
    }

    /**
     * Executes the note command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws SerialNumberNotFoundException If there is any errors in getting serial number of stock.
     */
    @Override
    public CommandResult execute(Model model) throws SerialNumberNotFoundException {
        logger.log(Level.INFO, "Starting to execute note command");

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> allStocks = model.getFilteredStockList();

        Stock stockToAddNote = getStockFromSerialNumber(serialNumber, allStocks);

        Stock stockWithAddedNote = createStockWithAddedNote(stockToAddNote, note);
        model.setStock(stockToAddNote, stockWithAddedNote);

        logger.log(Level.INFO, "Valid serial number and note input with note added to stock.");
        return new CommandResult(generateSuccessMessage(stockWithAddedNote));
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
            logger.log(Level.WARNING, "Valid serial number input but serial number not found.");
            throw new SerialNumberNotFoundException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        return stockToViewNotes.get();
    }

    /**
     * Creates the stock with added note.
     *
     * @param stockToAddNote The stock in the list to be updated.
     * @param noteToAdd The note to be added to stock.
     * @return The stock with updated attributes.
     */
    private static Stock createStockWithAddedNote(Stock stockToAddNote, Note noteToAdd) {
        assert stockToAddNote != null;
        Stock stockWithAddedNote = stockToAddNote.addNote(noteToAdd);

        return stockWithAddedNote;
    }

    /**
     * Generates a command execution success message when the note is added to
     * {@code stockToAddNote}.
     */
    private String generateSuccessMessage(Stock stockWithAddedNote) {
        return String.format(MESSAGE_ADD_NOTE_SUCCESS, stockWithAddedNote);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }
        // state check
        NoteCommand otherNoteCommand = (NoteCommand) other;
        return serialNumber.equals(otherNoteCommand.serialNumber)
                && note.equals(otherNoteCommand.note);
    }
}
