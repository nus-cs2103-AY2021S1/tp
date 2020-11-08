package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderModel;
import seedu.address.model.id.SellerId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.propertybook.PropertyModel;
import seedu.address.model.selleraddressbook.SellerModel;


/**
 * The API of the Model component.
 */
public interface Model extends BidderModel, SellerModel, PropertyModel {

    /** {@code Predicate} that always evaluate to true */

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


    // --- Property book file path ---

    /**
     * Returns the user prefs' property book file path.
     */
    Path getPropertyBookFilePath();

    /**
     * Sets the user prefs' property book file path.
     */
    void setPropertyBookFilePath(Path propertyBookFilePath);


    //=========== BidBook ================================================================================

    /** Sets the BidBook */
    void setBidBook(ReadOnlyBidBook bidBook);

    /** Returns the BidBook */
    ReadOnlyBidBook getBidBook();

    /** Returns the filtered version of BidBook */
    ObservableList<Bid> getFilteredBidList();

    /**
     * updates the Bid book with the new predicate
     * @param predicate predicate useed to determine what is filtered
     */
    void updateFilteredBidList(Predicate<Bid> predicate);

    /**
     * sets the filepath for BidBook
     * @param bidBookFilePath the file path to specify to
     */
    void setBidBookFilePath(Path bidBookFilePath);

    /**
     * retrieves the the filepath of BidBook
     */
    Path getBidBookFilePath();

    /**
     * adds a bid to the BidBook
     *
     * @param bid to add to the BidBook.
     * @throws CommandException If the bid cannot be added.
     */
    void addBid(Bid bid) throws CommandException;

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasBid(Bid bid);

    /**
     * deleted a bid from the BidBook
     * @param target
     */
    void deleteBid(Bid target);

    /**
     * Replaces the given bid {@code target} with {@code editedBid}.
     * {@code target} must exist in the bid book.
     * The bid identity of {@code editedBid} must not be the same as another existing bid in the bid book.
     *
     * @throws CommandException if the editedBid is invalid.
     */
    void setBid(Bid target, Bid editedBid) throws CommandException;

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
     * @throws CommandException If there is a problem executing the command.
     */
    void addMeeting(Meeting meeting) throws CommandException;

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

    //=========== MeetingManager ================================================================================

}
