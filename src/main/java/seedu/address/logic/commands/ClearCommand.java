package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.BookingBook;
import seedu.address.model.Model;
import seedu.address.model.PersonBook;
import seedu.address.model.RoomServiceBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Concierge Book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPersonBook(new PersonBook());
        model.setBookingBook(new BookingBook());
        model.setRoomServiceBook(new RoomServiceBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
