package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_ID;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_ID_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOUBLEROOM_ID1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOUBLEROOM_ID2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SINGLEROOM_ID1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SINGLEROOM_ID2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SINGLEROOM_ID3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_SINGLE_HARRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUITEROOM_ID1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUITEROOM_ID2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUITEROOM_ID3;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.GENE;
import static seedu.address.testutil.TypicalPersons.SINGLE_HARRY;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CheckInCommand.
 */
public class AddBookingCommandTest {
    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void constructor_nullStartDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE, VALID_ROOM_ID_GENE,
                null, VALID_END_DATE_GENE));
    }

    @Test
    public void constructor_nullEndDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE, VALID_ROOM_ID_GENE,
                VALID_START_DATE_GENE, null));
    }

    @Test
    public void execute_success() throws CommandException {
        model.addPerson(GENE);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_ROOM_ID_GENE, VALID_PERSON_ID_GENE,
                VALID_START_DATE_GENE, VALID_END_DATE_GENE, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_GENE, VALID_ROOM_ID_GENE,
                VALID_START_DATE_GENE, VALID_END_DATE_GENE);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_singleRoomSuccessOne() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SINGLEROOM_ID1, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SINGLEROOM_ID1,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_singleRoomSuccessTwo() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SINGLEROOM_ID2, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SINGLEROOM_ID2,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_singleRoomSuccessThree() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SINGLEROOM_ID3, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SINGLEROOM_ID3,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_doubleRoomSuccessOne() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_DOUBLEROOM_ID1, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_DOUBLEROOM_ID1,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_doubleRoomSuccessTwo() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_DOUBLEROOM_ID2, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_DOUBLEROOM_ID2,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_doubleRoomSuccessThree() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_DOUBLEROOM_ID2, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_DOUBLEROOM_ID2,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_suiteRoomSuccessOne() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SUITEROOM_ID1, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SUITEROOM_ID1,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_suiteRoomSuccessTwo() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SUITEROOM_ID2, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SUITEROOM_ID2,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_suiteRoomSuccessThree() throws CommandException {
        model.addPerson(SINGLE_HARRY);

        // stub
        // when i create a new booking, my booking ID++
        // so when i assertEquals, i fail because my addBookingCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_SUITEROOM_ID3, VALID_PERSON_ID_SINGLE_HARRY,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY, VALID_SUITEROOM_ID3,
                VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_invalidRoomIdOne_throwsCommandException() {
        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY,
                INVALID_ROOM_ID, VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY).execute(model));
    }

    @Test
    public void execute_invalidRoomIdTwo_throwsCommandException() {
        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_SINGLE_HARRY,
                INVALID_ROOM_ID_HIGH, VALID_START_DATE_SINGLE_HARRY, VALID_END_DATE_SINGLE_HARRY).execute(model));
    }

    @Test
    public void execute_thirtyOneNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        // start of invalid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(31);

        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate).execute(model));
    }

    @Test
    public void execute_thirtyTwoNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        // start of invalid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(32);

        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate).execute(model));
    }

    @Test
    public void execute_thirtyThreeNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        // second element on invalid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(33);

        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate).execute(model));
    }

    @Test
    public void execute_oneHundredNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        // far beyond start of invalid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(100);

        assertThrows(CommandException.class, () -> new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate).execute(model));
    }

    @Test
    public void execute_twentyNineNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        final int testBookingId = 7;

        // 2nd last valid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(29);

        Booking booking = new Booking(VALID_ROOM_ID_GENE, VALID_PERSON_ID_GENE,
                VALID_START_DATE_GENE, endDate, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_thirtyNights_throwsCommandException() throws CommandException {
        model.addPerson(GENE);

        final int testBookingId = 7;

        // 2nd last valid boundary value
        LocalDate endDate = VALID_START_DATE_GENE.plusDays(30);

        Booking booking = new Booking(VALID_ROOM_ID_GENE, VALID_PERSON_ID_GENE,
                VALID_START_DATE_GENE, endDate, true, testBookingId);

        AddBookingCommand command = new AddBookingCommand(VALID_PERSON_ID_GENE,
                VALID_ROOM_ID_GENE, VALID_START_DATE_GENE, endDate);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        final AddBookingCommand standardCommand = new AddBookingCommand(VALID_PERSON_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY);

        // same values -> returns true
        AddBookingCommand commandWithSameValues = new AddBookingCommand(VALID_PERSON_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different PERSON Id -> return false
        assertFalse(standardCommand.equals(new AddBookingCommand(VALID_PERSON_ID_BOB, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY)));

        // different Room Id -> return false
        assertFalse(standardCommand.equals(new AddBookingCommand(VALID_PERSON_ID_AMY, VALID_ROOM_ID_BOB,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY)));

        // different start date -> return false
        assertFalse(standardCommand.equals(new AddBookingCommand(VALID_PERSON_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_BOB, VALID_END_DATE_AMY)));

        // different end date -> return false
        assertFalse(standardCommand.equals(new AddBookingCommand(VALID_PERSON_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_BOB)));
    }
}
