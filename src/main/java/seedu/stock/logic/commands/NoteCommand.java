package seedu.stock.logic.commands;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import seedu.stock.logic.commands.exceptions.CommandException;
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
            + "Parameters:\n"
            + "sn/ [SERIAL NUMBER]\n"
            + "nt/ [NOTE]\n"
            + "Example: " + COMMAND_WORD + " sn/111111"
            + " nt/ Arrives every thursday 6pm.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Stock: %1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists";

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
     * @throws CommandException If there are any errors.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> allStocks = model.getFilteredStockList();

        Optional<Stock> stockToAddNote = Optional.empty();
        // Find the stock to add note to
        for (Stock currentStock : allStocks) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            if (currentStockSerialNumber.equals(serialNumber.getSerialNumberAsString())) {
                stockToAddNote = Optional.of(currentStock);
                break;
            }
        }

        if (stockToAddNote.isEmpty()) {
            throw new CommandException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        Stock stockWithAddedNote = createStockWithAddedNote(stockToAddNote.get(), note);
        model.setStock(stockToAddNote.get(), stockWithAddedNote);

        return new CommandResult(generateSuccessMessage(stockWithAddedNote));
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
