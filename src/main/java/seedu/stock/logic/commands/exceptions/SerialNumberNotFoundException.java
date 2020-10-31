package seedu.stock.logic.commands.exceptions;

import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.logic.commands.NoteViewCommand;


/**
 * Represents an error which occurs during execution of a
 * {@link NoteCommand}, {@link NoteDeleteCommand} or {@link NoteViewCommand}.
 */
public class SerialNumberNotFoundException extends Exception {

    public SerialNumberNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SourceCompanyNotFoundException} with the specified detail
     * {@code message} and {@code cause}.
     */
    public SerialNumberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
