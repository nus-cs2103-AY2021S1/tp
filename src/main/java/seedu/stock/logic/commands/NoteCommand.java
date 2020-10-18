package seedu.stock.logic.commands;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;

/**
 * Adds a note to an existing stock in the stock book.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the stock identified "
            + "by the serial number of the stock.\n"
            + "Parameters:\n"
            + "sn/ [SERIAL NUMBER]\n"
            + "note/ [NOTE]\n"
            + "Example: " + COMMAND_WORD + "sn/111111"
            + "note/ Arrives every thursday 6pm.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Stock: %1$s";

    private final SerialNumber serialNumber;
    private final Note note;
    /**
     * @param serialNumber of the stock in the stock book
     * @param note to add to the stock
     */
    public NoteCommand(SerialNumber serialNumber, Note note) {
        requireAllNonNull(serialNumber, note);

        this.serialNumber = serialNumber;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Stock stockToAddNote) {
        return String.format(MESSAGE_ADD_NOTE_SUCCESS, stockToAddNote);
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
        NoteCommand e = (NoteCommand) other;
        return serialNumber.equals(e.serialNumber)
                && note.equals(e.note);
    }
}
