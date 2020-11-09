package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.TypicalBookings;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ArchiveBookingCommand.
 */
class ArchiveBookingCommandTest {
    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(),
            getTypicalRoomBook(), getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_success() throws CommandException {
        model.addBooking(TypicalBookings.ACTIVE_BOOKING_DAN);
        ArchiveBookingCommand command = new ArchiveBookingCommand(CommandTestUtil.VALID_BOOKING_ID_DAN);
        String result = command.execute(model).getFeedbackToUser();
        Booking booking = model.getBookingWithId(CommandTestUtil.VALID_BOOKING_ID_DAN);
        assertEquals(String.format(ArchiveBookingCommand.MESSAGE_SUCCESS, booking), result);
    }

    @Test
    public void execute_bookingMissing_failure() {
        ArchiveBookingCommand command = new ArchiveBookingCommand(CommandTestUtil.VALID_BOOKING_ID_DAN);
        assertCommandFailure(command, model, MESSAGE_BOOKING_MISSING);
    }

    @Test
    public void execute_bookingInactive_failure() {
        model.addBooking(BOOKING_AMY);
        ArchiveBookingCommand command = new ArchiveBookingCommand(CommandTestUtil.VALID_BOOKING_ID_AMY);
        assertCommandFailure(command, model, ArchiveBookingCommand.MESSAGE_ALREADY_ARCHIVED);
    }

    @Test
    public void equals() {
        int roomId = 2103;
        final ArchiveBookingCommand standardCommand = new ArchiveBookingCommand(roomId);

        // same values -> returns true
        ArchiveBookingCommand commandWithSameValues = new ArchiveBookingCommand(roomId);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different Room Id -> return false
        assertFalse(standardCommand.equals(new ArchiveBookingCommand(roomId + 1)));
    }
}
