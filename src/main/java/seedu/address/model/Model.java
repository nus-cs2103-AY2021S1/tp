package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.roomservice.RoomService;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Booking> PREDICATE_SHOW_ALL_BOOKINGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' person book file path.
     */
    Path getPersonBookFilePath();

    /**
     * Sets the user prefs' person book file path.
     */
    void setPersonBookFilePath(Path addressBookFilePath);

    /**
     * Returns the user prefs' booking book file path.
     */
    Path getBookingBookFilePath();

    /**
     * Sets the user prefs' booking book file path.
     */
    void setBookingBookFilePath(Path addressBookFilePath);

    /**
     * Replaces person book data with the data in {@code personBook}.
     */
    void setPersonBook(ReadOnlyPersonBook personBook);

    /** Returns the PersonBook */
    ReadOnlyPersonBook getPersonBook();

    /**
     * Replaces booking book data with the data in {@code bookingBook}.
     */
    void setBookingBook(ReadOnlyBookingBook bookingBook);

    /** Returns the BookingBook */
    ReadOnlyBookingBook getBookingBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the person book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the id is in the person book.
     */
    boolean hasPersonWithId(Integer id);

    /**
     * Return person with matching id.
     */
    Person getPersonWithId(Integer id);

    /**
     * Deletes the given person.
     * The person must exist in the person book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the person book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the person book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the person book.
     */
    void setPerson(Person target, Person editedPerson);

    // Room Book Methods
    /**
     * Adds the given room.
     * {@code rooom} must not already exist in the room book.
     */
    void addRoom(Room r);

    /**
     * Replaces current room list with {@code rooms}.
     */
    void setRooms(List<Room> rooms);

    /**
     * Replaces current {@code ReadOnlyRoomBook} with {@code newData}.
     */
    void resetData(ReadOnlyRoomBook newData);

    /**
     * Replaces current {@code ReadOnlyBookingBook} with {@code newData}.
     */
    void resetData(ReadOnlyBookingBook newData);

    /**
     * Returns a {@code String} that displays all rooms.
     */
    String displayRooms(ObservableList<Integer> rooms);

    /**
     * Returns a {@code String} that displays all Single rooms.
     */
    String displaySingleRooms(ObservableList<Integer> rooms);

    /**
     * Returns a {@code String} that displays all Double rooms.
     */
    String displayDoubleRooms(ObservableList<Integer> rooms);

    /**
     * Returns a {@code String} that displays all Suite rooms.
     */
    String displaySuiteRooms(ObservableList<Integer> rooms);

    /**
     * Returns true if the roomId exist in the room book.
     */
    boolean hasRoom(int roomId);

    /**
     * Returns a {@code Room} with the corresponding {@code roomId}.
     */
    Room getRoom(Integer roomId);

    /**
     * Returns a {@code ObservableList} of all available rooms given a list of unavailable rooms.
     */
    ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms);

    /**
     * Returns a {@code ReadOnlyRoomBook}.
     */
    ReadOnlyRoomBook getRoomBook();

    // Booking Book Methods

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasBooking(Booking booking);

    /**
     * Returns true if a person with the id is in the address book.
     */
    boolean hasBookingWithId(Integer id);

    Booking getBookingWithId(Integer id);

    /**
     * Deletes the given booking.
     * The booking must exist in the booking book.
     */
    void deleteBooking(Booking target);

    /**
     * Deletes all booking associated with the personId.
     * The booking must exist in the booking book.
     */
    void deleteBookingByPersonId(Integer personId);

    /**
     * Adds the given booking.
     * The booking must not exist in the booking book.
     */
    void addBooking(Booking b);

    /**
     * Replaces bookings in the booking book with the list.
     */
    void setBookings(List<Booking> bookings);

    /**
     * Replaces the given booking {@code target} with {@code editedBooking}.
     * {@code target} must exist in the address book.
     * The {@code editedBooking} must not conflict with existing bookings.
     */
    void setBooking(Booking target, Booking editedBooking);

    ObservableList<Integer> getUnavailableRooms(LocalDate startDate, LocalDate endDate);

    /**
     * Sets the isActive state to be false.
     */
    void setBookingInactive(int bookingId);

    /**
     * Sets the isActive state to be true.
     */
    void setBookingActive(int bookingId);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered booking list */
    ObservableList<Booking> getFilteredBookingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookingList(Predicate<Booking> predicate);


    // Room Service Methods
    /**
     * Adds a Room Service.
     */
    void addRoomService(RoomService rs);

    /**
     * Returns a {@code ObservableList} of all room services.
     */
    ObservableList<RoomService> getRoomServicesForBooking(Integer bookingId);

    /**
     * Replaces room service book data with the data in {@code roomServiceBook}.
     */
    void setRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook);

    /**
     * Returns a {@code ReadOnlyRoomServiceBook}.
     */
    ReadOnlyRoomServiceBook getRoomServiceBook();
}
