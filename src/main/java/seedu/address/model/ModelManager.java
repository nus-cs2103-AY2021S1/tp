package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.bid.Bid;
import seedu.address.model.calendar.CalendarMeeting;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BidBook bidBook;
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final PropertyBook propertyBook;
    private final MeetingBook meetingBook;

    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Bid> filteredBids;
    private final FilteredList<CalendarMeeting> filteredMeetings;
    private final FilteredList<Property> filteredProperties;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs, bidBook, meetingManager and propertyBook.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyBidBook bidBook,
                        ReadOnlyMeetingManager meetingManager,
                        ReadOnlyPropertyBook propertyBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, bidBook, meetingManager, propertyBook);

        logger.fine("Initializing with address book: " + addressBook
                + " and user prefs " + userPrefs + " and bid book: " + bidBook
                + " and meeting manager" + meetingManager
                + " and property book: " + propertyBook);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.bidBook = new BidBook(bidBook);
        this.meetingBook = new MeetingBook(meetingManager);
        this.propertyBook = new PropertyBook(propertyBook);

        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredBids = new FilteredList<>(this.bidBook.getBidList());
        filteredMeetings = new FilteredList<>(this.meetingBook.getMeetingList());
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new BidBook(), new MeetingBook(), new PropertyBook());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyBidBook getBidBook() {
        return bidBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
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
    public ObservableList<Bid> getFilteredBidList() {
        return filteredBids;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBidList(Predicate<Bid> predicate) {
        requireNonNull(predicate);
        filteredBids.setPredicate(predicate);
    }

    @Override
    public void addBid(Bid bid) {
        bidBook.addBid(bid);
        updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
    }

    //=========== PropertyBook ================================================================================


    @Override
    public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
        this.propertyBook.resetData(propertyBook);
    }

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return propertyBook;
    }

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyBook.hasProperty(property);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyBook.removeProperty(target);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);
        propertyBook.setProperty(target, editedProperty);
    }

    //=========== Filtered Property List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Property} backed by the internal list of
     * {@code versionedPropertyBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    //=========== MeetingManager ================================================================================

    @Override
    public void setMeetingManager(ReadOnlyMeetingManager meetingManager) {
        this.meetingBook.resetData(meetingManager);
    }

    @Override
    public ReadOnlyMeetingManager getMeetingManager() {
        return meetingBook;
    }

    @Override
    public boolean hasMeeting(CalendarMeeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeetings(meeting);
    }

    @Override
    public void deleteMeeting(CalendarMeeting target) {
        meetingBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(CalendarMeeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public void setMeeting(CalendarMeeting target, CalendarMeeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        meetingBook.setMeeting(target, editedMeeting);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<CalendarMeeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<CalendarMeeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && meetingBook.equals(other.meetingBook)
                && filteredMeetings.equals(other.filteredMeetings)
                && propertyBook.equals(other.propertyBook)
                && filteredProperties.equals(other.filteredProperties);
    }
}
