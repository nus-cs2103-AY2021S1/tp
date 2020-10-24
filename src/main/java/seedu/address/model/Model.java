package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.propertybook.PropertyModel;


/**
 * The API of the Model component.
 */
public interface Model extends BidderModel, SellerModel, PropertyModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Bid> PREDICATE_SHOW_ALL_BIDS = unused -> true;

    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    //=========== UserPrefs ================================================================================

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

    // --- Address book file path ---

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path propertyBookFilePath);

    // --- Property book file path ---

    /**
     * Returns the user prefs' property book file path.
     */
    Path getPropertyBookFilePath();

    /**
     * Sets the user prefs' property book file path.
     */
    void setPropertyBookFilePath(Path propertyBookFilePath);

    //=========== AddressBook ================================================================================

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    //=========== BidBook ================================================================================

    /** Returns the BidBook */
    ReadOnlyBidBook getBidBook();

    ObservableList<Bid> getFilteredBidList();

    void updateFilteredBidList(Predicate<Bid> predicate);

    void addBid(Bid bid);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasBid(Bid bid);

    void deleteBid(Bid target);

    /**
     * Replaces the given bid {@code target} with {@code editedBid}.
     * {@code target} must exist in the bid book.
     * The bid identity of {@code editedBid} must not be the same as another existing bid in the bid book.
     */
    void setBid(Bid target, Bid editedBid);

    //=========== MeetingManager ================================================================================

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setMeetingManager(ReadOnlyMeetingBook meetingManager);

    /** Returns the Meeting manager */
    ReadOnlyMeetingBook getMeetingBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /** Returns an unmodifiable view of the sorted meeting list */
    ObservableList<Meeting> getSortedMeetingList();

    /**
     * Updates the comparator of the sorted meeting list to filter by the given {@code compatator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedMeetingList(Comparator<Meeting> comparator);

}
