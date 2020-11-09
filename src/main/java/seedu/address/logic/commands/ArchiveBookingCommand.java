package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Archive (change booking to inactive) the booking with a particular booking id
 */
public class ArchiveBookingCommand extends Command {

    public static final String COMMAND_WORD = "archiveBooking";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Archives a booking. \n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID (must be a valid booking id)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "3";

    public static final String MESSAGE_SUCCESS = "Successfully archived booking: %s";
    public static final String MESSAGE_ALREADY_ARCHIVED = "You have already archived this booking.";

    private final int bookingId;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a ArchiveBookingCommand
     * @param bookingId booking to archive
     */
    public ArchiveBookingCommand(int bookingId) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
    }

    /**
     * Archives booking with the bookingId
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert bookingId >= 0;
        logger.info(String.format("Archiving booking with id %s", bookingId));

        if (!model.hasBookingWithId(bookingId)) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        if (!model.getBookingWithId(bookingId).isActive()) {
            throw new CommandException(MESSAGE_ALREADY_ARCHIVED);
        }

        model.setBookingInactive(bookingId);

        assert !model.getBookingWithId(bookingId).isActive();
        logger.info(String.format("Archived booking with id %s", bookingId));

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
        if (!(other instanceof ArchiveBookingCommand)) {
            return false;
        }

        ArchiveBookingCommand o = (ArchiveBookingCommand) other;
        return this.bookingId == o.bookingId;
    }
}
