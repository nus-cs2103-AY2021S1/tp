package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.commons.core.Messages.MESSAGE_CONFLICTING_BOOKING;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_BOOKING;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;

/**
 * Unarchive (change an inactive booking back to active) the booking with a particular booking id
 */
public class UnarchiveBookingCommand extends Command {
    public static final String COMMAND_WORD = "unarchiveBooking";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unarchive a booking. \n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID (must be a valid booking id)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "3";

    public static final String MESSAGE_SUCCESS = "Successfully unarchived booking: %s";
    public static final String MESSAGE_NOT_YET_ARCHIVED = "This booking is not archived yet.";

    private final int bookingId;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a UnarchiveBookingCommand
     * @param bookingId booking to archive
     */
    public UnarchiveBookingCommand(int bookingId) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
    }

    /**
     * Unarchive booking with the bookingId
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert bookingId >= 0;
        logger.info(String.format("Unarchiving booking with id %s", bookingId));

        if (!model.hasBookingWithId(bookingId)) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        if (model.getBookingWithId(bookingId).isActive()) {
            throw new CommandException(MESSAGE_NOT_YET_ARCHIVED);
        }

        try {
            model.setBookingActive(bookingId);
        } catch (ConflictingBookingException conflictE) {
            throw new CommandException(MESSAGE_CONFLICTING_BOOKING);
        } catch (DuplicateBookingException duplicateE) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        assert model.getBookingWithId(bookingId).isActive();
        logger.info(String.format("Unarchived booking with id %s", bookingId));

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getBookingWithId(bookingId)),
                false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnarchiveBookingCommand)) {
            return false;
        }

        UnarchiveBookingCommand o = (UnarchiveBookingCommand) other;
        return this.bookingId == o.bookingId;
    }
}
