package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.roomservice.RoomService;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersonBook personBook;
    private final RoomBook roomBook;
    private final BookingBook bookingBook;
    private final RoomServiceBook roomServiceBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Booking> filteredBookings;

    /**
     * Initializes a ModelManager with the given personBook, bookingBook, roomBook and userPrefs.
     */
    public ModelManager(ReadOnlyPersonBook personBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomBook roomBook, ReadOnlyBookingBook bookingBook,
                        ReadOnlyRoomServiceBook roomServiceBook) {
        super();
        requireAllNonNull(personBook, userPrefs);

        logger.fine("Initializing with address book: " + personBook + " and user prefs " + userPrefs);

        this.personBook = new PersonBook(personBook);
        this.roomBook = new RoomBook(roomBook);
        this.bookingBook = new BookingBook(bookingBook);
        this.roomServiceBook = new RoomServiceBook(roomServiceBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personBook.getPersonList());
        filteredBookings = new FilteredList<>(this.bookingBook.getBookingList());

        // Initialize the nextAvailableId of Person class so that each new person gets a unique id
        Integer nextAvailableId = this.personBook.getPersonList().stream()
                .mapToInt(Person::getId).max().orElse(0) + 1;
        Person.setNextAvailableId(nextAvailableId);

        // Initialize the nextAvailableId of Booking class so that each new booking gets a unique id
        Integer nextAvailableIdBooking = this.bookingBook.getBookingList().stream()
                .mapToInt(Booking::getId).max().orElse(0) + 1;
        Booking.setNextAvailableId(nextAvailableIdBooking);
    }

    public ModelManager() {
        this(new PersonBook(), new UserPrefs(), new RoomBook(), new BookingBook(), new RoomServiceBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPersonBookFilePath() {
        return userPrefs.getPersonBookFilePath();
    }

    @Override
    public Path getBookingBookFilePath() {
        return userPrefs.getBookingBookFilePath();
    }

    @Override
    public void setPersonBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPersonBookFilePath(addressBookFilePath);
    }

    @Override
    public void setBookingBookFilePath(Path bookingBookFilePath) {
        requireNonNull(bookingBookFilePath);
        userPrefs.setBookingBookFilePath(bookingBookFilePath);
    }


    //=========== PersonBook ================================================================================

    @Override
    public void setPersonBook(ReadOnlyPersonBook personBook) {
        this.personBook.resetData(personBook);
    }

    @Override
    public ReadOnlyPersonBook getPersonBook() {
        return personBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonWithId(Integer id) {
        requireNonNull(id);
        return personBook.hasPersonWithId(id);
    }

    @Override
    public Person getPersonWithId(Integer id) {
        requireNonNull(id);
        return personBook.getPersonWithId(id);
    }

    @Override
    public void deletePerson(Person target) {
        personBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        personBook.setPerson(target, editedPerson);
    }

    //=========== RoomBook ===================================================================================
    @Override
    public ReadOnlyRoomBook getRoomBook() {
        return this.roomBook;
    }

    @Override
    public void addRoom(Room r) {
        this.roomBook.addRoom(r);
    }

    @Override
    public void setRooms(List<Room> rooms) {
        this.roomBook.setRooms(rooms);
    }

    @Override
    public void resetData(ReadOnlyRoomBook newData) {
        this.roomBook.resetData(newData);
    }

    @Override
    public void resetData(ReadOnlyBookingBook newData) {
        this.bookingBook.resetData(newData);
    }

    @Override
    public ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms) {
        return this.roomBook.getAvailableRooms(unavailableRooms);
    }

    @Override
    public String displayRooms(ObservableList<Integer> rooms) {
        logger.info("=============================[ Displaying All Rooms ]===========================");
        return this.roomBook.displayRooms(rooms);
    }

    @Override
    public String displaySingleRooms(ObservableList<Integer> rooms) {
        logger.info("=============================[ Displaying Single Rooms ]===========================");
        return this.roomBook.getSingleRooms(rooms);
    }

    @Override
    public String displayDoubleRooms(ObservableList<Integer> rooms) {
        logger.info("=============================[ Displaying Double Rooms ]===========================");
        return this.roomBook.getDoubleRooms(rooms);
    }

    @Override
    public String displaySuiteRooms(ObservableList<Integer> rooms) {
        logger.info("=============================[ Displaying Suite Rooms ]===========================");
        return this.roomBook.getSuiteRooms(rooms);
    }

    @Override
    public boolean hasRoom(int roomId) {
        return this.roomBook.hasRoom(roomId);
    }

    @Override
    public Room getRoom(Integer roomId) {
        return this.roomBook.getRoom(roomId);
    }

    //=========== BookingBook ===================================================================================

    @Override
    public void setBookingBook(ReadOnlyBookingBook bookingBook) {
        this.bookingBook.resetData(bookingBook);
    }

    @Override
    public ReadOnlyBookingBook getBookingBook() {
        return this.bookingBook;
    }

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookingBook.hasBooking(booking);
    }

    @Override
    public boolean hasBookingWithId(Integer id) {
        requireNonNull(id);
        return bookingBook.hasBookingWithId(id);
    }

    /**
     * Return booking with this booking id
     */
    @Override
    public Booking getBookingWithId(Integer id) {
        return bookingBook.getBookingWithId(id);
    }

    @Override
    public void deleteBookingByPersonId(Integer personId) {
        bookingBook.removeBookingWithPersonId(personId);
    }

    @Override
    public void deleteBooking(Booking target) {
        bookingBook.removeBooking(target);
    }

    @Override
    public void addBooking(Booking b) {
        this.bookingBook.addBooking(b);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
    }

    @Override
    public void setBookings(List<Booking> bookings) {
        this.bookingBook.setBookings(bookings);
    }

    @Override
    public void setBooking(Booking target, Booking editedBooking) {
        this.bookingBook.setBooking(target, editedBooking);
    }

    @Override
    public ObservableList<Integer> getUnavailableRooms(LocalDate startDate, LocalDate endDate) {
        return this.bookingBook.getUnavailableRooms(startDate, endDate);
    }

    @Override
    public void setBookingInactive(int bookingId) {
        this.bookingBook.setBookingInactive(bookingId);
    }

    @Override
    public void setBookingActive(int bookingId) {
        this.bookingBook.setBookingActive(bookingId);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Booking List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Booking} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }

    //=========== RoomServiceBook ================================================================================
    @Override
    public void addRoomService(RoomService rs) {
        this.roomServiceBook.addRoomService(rs);
    }

    @Override
    public ObservableList<RoomService> getRoomServicesForBooking(Integer bookingId) {
        return this.roomServiceBook.getRoomServicesForBooking(bookingId);
    }

    @Override
    public void setRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook) {
        this.roomServiceBook.resetData(roomServiceBook);
    }

    @Override
    public ReadOnlyRoomServiceBook getRoomServiceBook() {
        return this.roomServiceBook;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return personBook.equals(other.personBook)
                && bookingBook.equals(other.bookingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredBookings.equals(other.filteredBookings)
                && roomServiceBook.equals(other.roomServiceBook);
    }

}
