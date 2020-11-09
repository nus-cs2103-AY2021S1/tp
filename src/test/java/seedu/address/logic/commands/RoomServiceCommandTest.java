package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import static seedu.address.testutil.TypicalBookings.*;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomServiceBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

/**
 * Integration test for RoomServiceCommand
 */
class RoomServiceCommandTest {
    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), new RoomServiceBook());
    private int bookingId = VALID_BOOKING_ID_DAN;
    private RoomServiceType type = RoomServiceType.WIFI;
    private RoomServiceCommand roomServiceCommand = new RoomServiceCommand(new RoomService(bookingId, type));

    @Test
    void execute_success() throws CommandException {
        model.addBooking(ACTIVE_BOOKING_DAN);
        roomServiceCommand.execute(model);
        assertEquals(model.getRoomServicesForBooking(bookingId).size(), 1);
    }

    @Test
    void execute_inactiveBooking_throwsCommandException() {
        model.addBooking(BOOKING_BOB);
        assertThrows(CommandException.class, () ->
            new RoomServiceCommand(new RoomService(VALID_BOOKING_ID_BOB, type)).execute(model)
        );
    }

    @Test
    void execute_invalidBooking_throwsCommandException() {
        assertThrows(CommandException.class, () ->
                new RoomServiceCommand(new RoomService(12319, type)).execute(model)
        );
    }

    @Test
    void equals() {

        // same object -> returns true
        assertTrue(roomServiceCommand.equals(roomServiceCommand));

        // same values -> returns true
        RoomServiceCommand copy = new RoomServiceCommand(new RoomService(bookingId, type));
        assertTrue(roomServiceCommand.equals(copy));

        // different types -> returns false
        assertFalse(roomServiceCommand.equals(1));

        // null -> returns false
        assertFalse(roomServiceCommand.equals(null));

        // different room service -> returns false
        assertFalse(roomServiceCommand.equals(new RoomService(100, type)));
    }
}
