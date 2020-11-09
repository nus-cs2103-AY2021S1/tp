package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.commons.core.Messages.MESSAGE_EXCEED_DURATION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_END_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_ROOM_ID_MISSING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertBookingBookCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBookings.BOOKING_ID_1;
import static seedu.address.testutil.TypicalBookings.EDITED_ENDDATE_1;
import static seedu.address.testutil.TypicalBookings.EDITED_ROOM_ID_1;
import static seedu.address.testutil.TypicalBookings.EXCEED_1_MONTH_END_DATE_1;
import static seedu.address.testutil.TypicalBookings.INVALID_BOOKING_ID;
import static seedu.address.testutil.TypicalBookings.START_DATE_AFTER_END_DATE_1;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.BookingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingCommandTest {
    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Booking editedBooking = new BookingBuilder().withRoomId(VALID_ROOM_ID_BOB).withStartDate(VALID_START_DATE_BOB)
                .withEndDate(VALID_END_DATE_BOB).build();
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder(editedBooking).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new PersonBook(model.getPersonBook()), new UserPrefs(), model.getRoomBook(), model.getBookingBook(),
                model.getRoomServiceBook());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Booking firstBooking = model.getBookingWithId(BOOKING_ID_1);

        BookingBuilder bookingInList = new BookingBuilder(firstBooking);
        Booking editedBooking = bookingInList.withRoomId(EDITED_ROOM_ID_1).withEndDate(EDITED_ENDDATE_1).build();

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(EDITED_ROOM_ID_1).withEndDate(EDITED_ENDDATE_1).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new PersonBook(model.getPersonBook()), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.setBooking(firstBooking, editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        Booking bookingInFilteredList = model.getBookingWithId(BOOKING_ID_1);
        Booking editedBooking = new BookingBuilder(bookingInFilteredList).withRoomId(VALID_ROOM_ID_BOB)
                .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1,
                new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB)
                        .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new PersonBook(model.getPersonBook()), new UserPrefs(), model.getRoomBook(),
                new BookingBook(model.getBookingBook()), model.getRoomServiceBook());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookingIndexUnfilteredList_failure() {
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(INVALID_BOOKING_ID, descriptor);

        assertCommandFailure(editBookingCommand, model, MESSAGE_BOOKING_MISSING);
    }

    @Test
    public void execute_startDateAfterEndDate_failure() {
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withStartDate(START_DATE_AFTER_END_DATE_1).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        assertCommandFailure(editBookingCommand, model, MESSAGE_INVALID_START_END_DATE);
    }

    @Test
    public void execute_invalidRoomId_failure() {
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(1000).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        assertBookingBookCommandFailure(editBookingCommand, model, MESSAGE_ROOM_ID_MISSING);
    }

    @Test
    public void execute_exceedStayDuration_failure() {
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(2107).withEndDate(EXCEED_1_MONTH_END_DATE_1).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        assertBookingBookCommandFailure(editBookingCommand, model, MESSAGE_EXCEED_DURATION);
    }
    @Test
    public void equals() {
        final EditBookingCommand standardCommand = new EditBookingCommand(BOOKING_ID_1, DESC_BOOKING_AMY);

        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor copyDescriptor =
                new EditBookingCommand.EditBookingDescriptor(DESC_BOOKING_AMY);
        EditBookingCommand commandWithSameValues = new EditBookingCommand(BOOKING_ID_1, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(INVALID_BOOKING_ID, DESC_BOOKING_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(BOOKING_ID_1, DESC_BOOKING_BOB)));
    }

}
