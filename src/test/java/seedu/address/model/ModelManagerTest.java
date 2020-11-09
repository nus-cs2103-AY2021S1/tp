package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;
import static seedu.address.testutil.TypicalBookings.CONFLICT_AMY_BOOKING_CHLOE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOM;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOMID;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.BookingBookBuilder;
import seedu.address.testutil.PersonBookBuilder;
import seedu.address.testutil.TypicalRoomService;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new PersonBook(), new PersonBook(modelManager.getPersonBook()));
        assertEquals(new BookingBook(), new BookingBook(modelManager.getBookingBook()));
        assertEquals(new RoomServiceBook(), new RoomServiceBook(modelManager.getRoomServiceBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPersonBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setBookingBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPersonBookFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setBookingBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPersonBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPersonBookFilePath(null));
    }

    @Test
    public void setBookingBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBookingBookFilePath(null));
    }

    @Test
    public void setPersonBookFilePath_validPath_setsPersonBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPersonBookFilePath(path);
        assertEquals(path, modelManager.getPersonBookFilePath());
    }

    @Test
    public void setBookingBookFilePath_validPath_setsBookingBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setBookingBookFilePath(path);
        assertEquals(path, modelManager.getBookingBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPersonBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPersonBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPersonWithId_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPersonWithId(null));
    }

    @Test
    public void hasPersonWithId_personNotInPersonBook_returnsFalse() {
        assertFalse(modelManager.hasPersonWithId(0));
    }

    @Test
    public void hasPersonWithId_personInPersonBook_returnsTrue() {
        modelManager.addPerson(AMY);
        assertTrue(modelManager.hasPersonWithId(VALID_ID_AMY));
    }

    @Test
    public void hasBooking_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBooking(null));
    }

    @Test
    public void hasBooking_bookingNotInBookingBook_returnsFalse() {
        assertFalse(modelManager.hasBooking(CONFLICT_AMY_BOOKING_CHLOE));
    }

    @Test
    public void hasBooking_bookingInBookingBook_returnsTrue() {
        modelManager.addBooking(BOOKING_AMY);
        assertTrue(modelManager.hasBooking(BOOKING_AMY));
    }

    @Test
    public void hasBookingWithId_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBookingWithId(null));
    }

    @Test
    public void hasBookingWithId_bookingNotInBookingBook_returnsFalse() {
        assertFalse(modelManager.hasBookingWithId(0));
    }

    @Test
    public void hasBookingWithId_bookingInBookingBook_returnsTrue() {
        modelManager.addBooking(BOOKING_AMY);
        assertTrue(modelManager.hasBookingWithId(VALID_BOOKING_ID_AMY));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredBookingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBookingList().remove(0));
    }

    @Test
    public void hasRoom_roomNotInRoomBook_returnsFalse() {
        assertFalse(modelManager.hasRoom(2103));
    }

    @Test
    public void hasRoom_roomInRoomBook_returnsTrue() {
        modelManager.addRoom(DEFAULT_ROOM);
        assertTrue(modelManager.hasRoom(DEFAULT_ROOMID));
    }

    @Test
    public void getRoom_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.getRoom(null));
    }

    @Test
    public void getRoom_success() {
        modelManager.addRoom(DEFAULT_ROOM);
        assertTrue(modelManager.getRoom(DEFAULT_ROOMID).equals(DEFAULT_ROOM));
    }

    @Test
    void addRoomService_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addRoomService(null));
    }

    @Test
    void addRoomService_success() {
        modelManager.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertEquals(modelManager.getRoomServicesForBooking(VALID_BOOKING_ID_DAN).size(), 1);
    }

    @Test
    void getRoomServicesForBooking_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.getRoomServicesForBooking(null));
    }

    @Test
    void getRoomServicesForBooking_success() {
        modelManager.addRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertTrue(modelManager.getRoomServicesForBooking(
                VALID_BOOKING_ID_DAN).get(0).equals(TypicalRoomService.ROOM_SERVICE_DAN_DINING));
    }

    @Test
    public void equals() {
        PersonBook personBook = new PersonBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        PersonBook differentAddressBook = new PersonBook();
        UserPrefs userPrefs = new UserPrefs();
        RoomBook roomBook = new RoomBook();
        BookingBook bookingBook = new BookingBookBuilder().withBooking(BOOKING_AMY).withBooking(BOOKING_BOB).build();
        BookingBook differentBookingBook = new BookingBook();
        RoomServiceBook roomServiceBook = new RoomServiceBook();

        // same values -> returns true
        modelManager = new ModelManager(personBook, userPrefs, roomBook, bookingBook, roomServiceBook);
        ModelManager modelManagerCopy = new ModelManager(
                personBook, userPrefs, roomBook, bookingBook, roomServiceBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different personBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, roomBook, bookingBook, roomServiceBook)));

        // different bookingBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(personBook,
                userPrefs, roomBook, differentBookingBook, roomServiceBook)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(personBook,
                userPrefs, roomBook, bookingBook, roomServiceBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPersonBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(personBook,
                differentUserPrefs, roomBook, bookingBook, roomServiceBook)));

    }
}
