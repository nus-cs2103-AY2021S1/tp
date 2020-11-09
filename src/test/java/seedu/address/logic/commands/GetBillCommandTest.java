package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalBookings.ACTIVE_BOOKING_DAN;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;
import static seedu.address.testutil.TypicalBookings.INVALID_BOOKING_ID;
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
import seedu.address.model.booking.exception.BookingNotFoundException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GetBillCommand.
 */
public class GetBillCommandTest {

    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_success_withRoomService() throws CommandException {
        model.addBooking(ACTIVE_BOOKING_DAN);

        String expected = "Number of nights stayed: 10\n"
                + "Price per night: $70\n"
                + "Room services ordered:\n"
                + "Massaging service: $70\n"
                + "Wifi service: $15\n"
                + "Dining in service: $50\n"
                + "\n"
                + "Nett bill for booking id 14 = $835";

        GetBillCommand command = new GetBillCommand(VALID_BOOKING_ID_DAN);
        assertEquals(expected, command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_success_withNoRoomService() throws CommandException {
        model.addBooking(BOOKING_BOB);
        String expected = "Number of nights stayed: 1\n"
                + "Price per night: $70\n"
                + "No room services ordered\n"
                + "Nett bill for booking id 12 = $70";

        GetBillCommand command = new GetBillCommand(VALID_BOOKING_ID_BOB);
        assertEquals(expected, command.execute(model).getFeedbackToUser());

    }

    @Test
    public void execute_invalid_booking() throws CommandException {
        assertThrows(BookingNotFoundException.class, () -> {
            int bookingId = ACTIVE_BOOKING_DAN.getId();
            Booking booking = model.getBookingWithId(bookingId);
        });
    }

    @Test
    public void nonExistentBooking() throws CommandException {
        GetBillCommand command = new GetBillCommand(INVALID_BOOKING_ID);
        assertCommandFailure(command, model, MESSAGE_BOOKING_MISSING);
    }

    @Test
    public void equals() {
        final GetBillCommand standardCommand = new GetBillCommand(VALID_BOOKING_ID_DAN);

        // same values -> returns true
        GetBillCommand commandWithSameValues = new GetBillCommand(VALID_BOOKING_ID_DAN);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different bookingId -> return false
        assertFalse(standardCommand.equals(new GetBillCommand(VALID_BOOKING_ID_DAN + 1)));
    }
}
