package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BookingBook;
import seedu.address.model.Model;
import seedu.address.model.PersonBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingMatchesBookingIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditBookingDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final Integer VALID_ID_AMY = 1;
    public static final Integer VALID_ID_BOB = 2;
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_GENE = "Gene Xiao";
    public static final String VALID_NAME_SINGLE_HARRY = "Harry SingleLover";
    public static final String VALID_NAME_DOUBLE_HARRY = "Harry DoubleLover";
    public static final String VALID_NAME_SUITE_HARRY = "Harry SuiteLover";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_GENE = "420420420";
    public static final String VALID_PHONE_SINGLE_HARRY = "111222333";
    public static final String VALID_PHONE_DOUBLE_HARRY = "222111333";
    public static final String VALID_PHONE_SUITE_HARRY = "333111222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_GENE = "gene@nuscomputing.com";
    public static final String VALID_EMAIL_SINGLE_HARRY = "singleharry@nus.com";
    public static final String VALID_EMAIL_DOUBLE_HARRY = "doubleharry@nus.com";
    public static final String VALID_EMAIL_SUITE_HARRY = "suiteharry@nus.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_GENE = "Streety McStreetFace";
    public static final String VALID_ADDRESS_SINGLE_HARRY = "Block 111, Single Street";
    public static final String VALID_ADDRESS_DOUBLE_HARRY = "Block 222, Double Street";
    public static final String VALID_ADDRESS_SUITE_HARRY = "Block 333, Suite Street";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // Check In Values
    public static final int VALID_PERSON_ID_GENE = 69;
    public static final int VALID_PERSON_ID_AMY = 23;
    public static final int VALID_PERSON_ID_BOB = 12;
    public static final int VALID_PERSON_ID_CHLOE = 6;
    public static final int VALID_PERSON_ID_DAN = 4;
    public static final int INVALID_PERSON_ID = 1000; // use for non-existing personId test

    public static final int VALID_PERSON_ID_SINGLE_HARRY = 420;
    public static final int VALID_PERSON_ID_DOUBLE_HARRY = 421;
    public static final int VALID_PERSON_ID_SUITE_HARRY = 422;

    public static final int VALID_SINGLEROOM_ID1 = 2104;
    public static final int VALID_SINGLEROOM_ID2 = 2110;
    public static final int VALID_SINGLEROOM_ID3 = 2112;
    public static final int VALID_DOUBLEROOM_ID1 = 2113;
    public static final int VALID_DOUBLEROOM_ID2 = 2120;
    public static final int VALID_DOUBLEROOM_ID3 = 2122;
    public static final int VALID_SUITEROOM_ID1 = 2123;
    public static final int VALID_SUITEROOM_ID2 = 2129;
    public static final int VALID_SUITEROOM_ID3 = 2132;

    public static final int VALID_ROOM_ID_AMY = 2103;
    public static final int VALID_ROOM_ID_BOB = 2104;
    public static final int VALID_ROOM_ID_GENE = 2106;
    public static final int CONFLICT_AMY_ROOM_ID_CHLOE = 2103;
    public static final int VALID_ROOM_ID_DAN = 2104;
    public static final int INVALID_ROOM_ID = 1;
    public static final int INVALID_ROOM_ID_HIGH = 9999;

    public static final LocalDate VALID_START_DATE_AMY = LocalDate.parse("2020-10-05",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_AMY = LocalDate.parse("2020-10-10",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_BOB = LocalDate.parse("2020-12-12",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_BOB = LocalDate.parse("2020-12-13",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate CONFLICT_AMY_START_DATE_CHLOE = LocalDate.parse("2020-10-06",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate CONFLICT_AMY_END_DATE_CHLOE = LocalDate.parse("2020-10-09",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_DAN = LocalDate.parse("2020-11-11",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_DAN = LocalDate.parse("2020-11-21",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_GENE = LocalDate.parse("2020-01-01",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_GENE = LocalDate.parse("2020-01-30",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static final LocalDate VALID_START_DATE_SINGLE_HARRY = LocalDate.parse("2020-03-25",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_SINGLE_HARRY = LocalDate.parse("2020-04-12",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_DOUBLE_HARRY = LocalDate.parse("2020-06-25",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_DOUBLE_HARRY = LocalDate.parse("2020-07-25",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_SUITE_HARRY = LocalDate.parse("2020-08-25",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_SUITE_HARRY = LocalDate.parse("2020-09-25",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));


    // Booking Values
    public static final int VALID_BOOKING_ID_AMY = 11;
    public static final int VALID_BOOKING_ID_BOB = 12;
    public static final int CONFLICT_AMY_VALID_BOOKING_ID_CHLOE = 1;
    public static final int VALID_BOOKING_ID_DAN = 14;
    public static final int BOOKING_DURATION_AMY = 5;

    public static final int VALID_BOOKING_ID_SINGLE_HARRY = 105;
    public static final int VALID_BOOKING_ID_DOUBLE_HARRY = 205;
    public static final int VALID_BOOKING_ID_SUITE_HARRY = 305;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    // Check In Descriptions
    public static final String PERSON_ID_DESC_AMY = " " + PREFIX_PERSON_ID + VALID_PERSON_ID_AMY;
    public static final String PERSON_ID_DESC_BOB = " " + PREFIX_PERSON_ID + VALID_PERSON_ID_BOB;
    public static final String ROOM_ID_DESC_AMY = " " + PREFIX_ROOM_ID + VALID_ROOM_ID_AMY;
    public static final String ROOM_ID_DESC_BOB = " " + PREFIX_ROOM_ID + VALID_ROOM_ID_BOB;
    public static final String START_DATE_DESC_AMY = " " + PREFIX_START_DATE + VALID_START_DATE_AMY;
    public static final String START_DATE_DESC_BOB = " " + PREFIX_START_DATE + VALID_START_DATE_BOB;
    public static final String END_DATE_DESC_AMY = " " + PREFIX_END_DATE + VALID_END_DATE_AMY;
    public static final String END_DATE_DESC_BOB = " " + PREFIX_END_DATE + VALID_END_DATE_BOB;

    public static final String BOOKING_ID_DESC_BOB = " " + PREFIX_BOOKING_ID + VALID_BOOKING_ID_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    // Invalid Check In Descriptions
    public static final String INVALID_PERSON_ID_DESC = " " + PREFIX_PERSON_ID + "a12"; // letter not allowed in PID
    public static final String INVALID_ROOM_ID_DESC = " " + PREFIX_ROOM_ID + "88"; // roomId supposed to be four digits
    public static final String INVALID_ROOM_ID_DESC2 = " " + PREFIX_ROOM_ID + "419&"; // '&' not allowed in roomIds
    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START_DATE + "2020 02 02"; // 2020-02-02
    public static final String INVALID_END_DATE_DESC = " " + PREFIX_END_DATE + "2020 06 02"; // 2020-02-02

    // Get Bill Descriptions
    public static final String BOOKING_ID_DESC_AMY = " " + PREFIX_BOOKING_ID + VALID_BOOKING_ID_AMY;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final EditBookingCommand.EditBookingDescriptor DESC_BOOKING_AMY;
    public static final EditBookingCommand.EditBookingDescriptor DESC_BOOKING_BOB;

    public static final int VALID_PRICE = 50;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_BOOKING_AMY = new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_AMY)
                .withStartDate(VALID_START_DATE_AMY).withEndDate(VALID_END_DATE_AMY).build();
        DESC_BOOKING_BOB = new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB)
                .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build();

    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PersonBook expectedAddressBook = new PersonBook(actualModel.getPersonBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getPersonBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the booking book, filtered booking list and selected booking in {@code actualModel} remain unchanged
     */
    public static void assertBookingBookCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        BookingBook expectedBookingBook = new BookingBook(actualModel.getBookingBook());
        List<Booking> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBookingBook, actualModel.getBookingBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookingList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonWithId(Model model, int personId) {
        assert personId > 0;

        Person person = model.getPersonWithId(personId);
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the booking at the given {@code targetIndex} in the
     * {@code model}'s booking book.
     */
    public static void showBookingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookingList().size());

        Booking booking = model.getFilteredBookingList().get(targetIndex.getZeroBased());
        final int bookingId = booking.getId();
        model.updateFilteredBookingList(new BookingMatchesBookingIdPredicate(bookingId));

        assertEquals(1, model.getFilteredBookingList().size());
    }


    /**
     * Updates {@code model}'s filtered list to show only the booking with given {@code bookingId} in the
     * {@code model}'s booking book.
     */
    public static void showBookingWithId(Model model, Integer bookingId) {
        assertTrue(bookingId < model.getFilteredBookingList().size());

        model.updateFilteredBookingList(new BookingMatchesBookingIdPredicate(bookingId));

        assertEquals(1, model.getFilteredBookingList().size());
    }

}
