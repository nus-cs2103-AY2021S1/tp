package seedu.stock.logic.commands;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER_DESCRIPTION;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Adds a note to an existing stock in the stock book.
 */
public class NoteDeleteCommand extends Command {

    public static final String COMMAND_WORD = "notedelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a note,"
            + " specified by index of note shown in stock display, or all notes of the stock identified "
            + "by the serial number of the stock.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + " "
            + PREFIX_NOTE_INDEX + PREFIX_NOTE_INDEX_DESCRIPTION + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "ntuc1 "
            + PREFIX_NOTE_INDEX + "1";

    private static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note(s) from Stock: %1$s";
    private static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND =
            "Stock with given serial number does not exists";
    private static final String MESSAGE_NOTE_INDEX_NOT_FOUND = "Note at index specified is not found.";
    private static final String MESSAGE_STOCK_HAS_NO_NOTE = "Stock specified has no note.";
    private static final Logger logger = LogsCenter.getLogger(NoteDeleteCommand.class);

    private final SerialNumber serialNumber;
    private final NoteIndex noteIndex;

    /**
     * Constructs a NoteDeleteCommand
     * @param serialNumber of the stock in the stock book
     * @param noteIndex of note to delete from the stock
     */
    public NoteDeleteCommand(SerialNumber serialNumber, NoteIndex noteIndex) {
        requireAllNonNull(serialNumber, noteIndex);

        this.serialNumber = serialNumber;
        this.noteIndex = noteIndex;
    }

    /**
     * Executes the note command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws CommandException If there are any errors.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException, SerialNumberNotFoundException {
        logger.log(Level.INFO, "Starting to execute note delete command");

        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> lastShownStocks = model.getFilteredStockList();

        Optional<Stock> stockToDeleteNote = Optional.empty();
        // Find the stock to add note to
        for (Stock currentStock : lastShownStocks) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            if (currentStockSerialNumber.equals(serialNumber.getSerialNumberAsString())) {
                stockToDeleteNote = Optional.of(currentStock);
                break;
            }
        }

        if (stockToDeleteNote.isEmpty()) {
            throw new SerialNumberNotFoundException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        if (stockToDeleteNote.get().getNotes().size() == 0) {
            throw new CommandException(MESSAGE_STOCK_HAS_NO_NOTE);
        }

        if (noteIndex.getOneBased() > stockToDeleteNote.get().getNotes().size()) {
            throw new CommandException(MESSAGE_NOTE_INDEX_NOT_FOUND);
        }

        Stock stockWithDeletedNote = createStockWithDeletedNote(stockToDeleteNote.get(), noteIndex);
        model.setStock(stockToDeleteNote.get(), stockWithDeletedNote);

        logger.log(Level.INFO, "Finished deleting notes successfully");
        return new CommandResult(generateSuccessMessage(stockWithDeletedNote));
    }

    /**
     * Creates the stock with added note.
     *
     * @param stockToDeleteNote The stock in the list to be updated.
     * @param index The index of note to delete from stock.
     * @return The stock with updated attributes.
     */
    private static Stock createStockWithDeletedNote(Stock stockToDeleteNote, NoteIndex index) {
        assert stockToDeleteNote != null;
        Stock stockWithDeletedNote = stockToDeleteNote.deleteNote(index);

        return stockWithDeletedNote;
    }

    /**
     * Generates a command execution success message when the note is deleted from
     * {@code stockToAddNote}.
     */
    private String generateSuccessMessage(Stock stockWithDeletedNote) {
        return String.format(MESSAGE_DELETE_NOTE_SUCCESS, stockWithDeletedNote);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof NoteDeleteCommand)) {
            return false;
        }
        // state check
        NoteDeleteCommand otherNoteDeleteCommand = (NoteDeleteCommand) other;
        return serialNumber.equals(otherNoteDeleteCommand.serialNumber)
                && noteIndex.equals(otherNoteDeleteCommand.noteIndex);
    }
}
