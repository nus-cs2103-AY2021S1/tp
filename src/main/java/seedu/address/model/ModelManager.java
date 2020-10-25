package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_BIDDER_ID;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_BID_AMOUNT;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_PROPERTY_ID;
import static seedu.address.model.price.Price.isValidPrice;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.property.Property;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BidBook bidBook;
    private final AddressBook addressBook;
    private final BidderAddressBook bidderAddressBook;
    private final SellerAddressBook sellerAddressBook;
    private final UserPrefs userPrefs;
    private final PropertyBook propertyBook;
    private final MeetingBook meetingBook;

    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Seller> filteredSellers;
    private final FilteredList<Bidder> filteredBidders;
    private final FilteredList<Bid> filteredBids;
    private final FilteredList<Meeting> filteredMeetings;
    private final FilteredList<Property> filteredProperties;
    private final SortedList<Meeting> sortedMeetings;
    private final SortedList<Bid> sortedBids;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs, bidBook, meetingManager and propertyBook.
     */

    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyBidBook bidBook,
                        ReadOnlyPropertyBook propertyBook, ReadOnlyBidderAddressBook bidderAddressBook,
                        ReadOnlySellerAddressBook sellerAddressBook, ReadOnlyMeetingBook meetingManager) {
        super();
        requireAllNonNull(addressBook, userPrefs, bidBook, propertyBook,
                bidderAddressBook, sellerAddressBook, meetingManager);

        logger.fine("Initializing with address book: " + addressBook
                + " and user prefs " + userPrefs + " and bid book: " + bidBook
                + " and property book: " + propertyBook
                + "\n bidderAddressBook: " + bidderAddressBook
                + "\n sellerAddressBook: " + sellerAddressBook
                + "\n and meeting manager" + meetingManager
        );

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.bidderAddressBook = new BidderAddressBook(bidderAddressBook);
        this.sellerAddressBook = new SellerAddressBook(sellerAddressBook);
        this.bidBook = new BidBook(bidBook);
        this.meetingBook = new MeetingBook(meetingManager);
        this.propertyBook = new PropertyBook(propertyBook);

        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredBidders = new FilteredList<>(this.bidderAddressBook.getBidderList());
        filteredSellers = new FilteredList<>(this.sellerAddressBook.getSellerList());
        filteredBids = new FilteredList<>(this.bidBook.getBidList());
        filteredMeetings = new FilteredList<>(this.meetingBook.getMeetingList());
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
        sortedMeetings = new SortedList<>(this.meetingBook.getMeetingList());
        sortedBids = new SortedList<>(this.bidBook.getBidList());

    }

    /**
     * Constructor for the ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(),
                new UserPrefs(),
                new BidBook(),
                new PropertyBook(),
                new BidderAddressBook(),
                new SellerAddressBook(),
                new MeetingBook());

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

    @Override
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setPropertyBookFilePath(propertyBookFilePath);
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

    //=========== BidBook ================================================================================

    @Override
    public ReadOnlyBidBook getBidBook() {
        return bidBook;
    }

    @Override
    public ObservableList<Bid> getFilteredBidList() {
        return filteredBids;
    }

    @Override
    public void addBid(Bid bid) {
        bidBook.addBid(bid);
        updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
    }

    @Override
    public void updateFilteredBidList(Predicate<Bid> predicate) {
        requireNonNull(predicate);
        filteredBids.setPredicate(predicate);
    }

    @Override
    public boolean hasBid(Bid bid) {
        requireNonNull(bid);
        return bidBook.hasBid(bid);
    }

    @Override
    public void isValidBid(Bid bid) throws CommandException {
        requireNonNull(bid);
        if (!containsPropertyId(bid.getPropertyId())) {
            throw new CommandException(MESSAGE_INVALID_PROPERTY_ID);
        }
        if (!containsBidderId(bid.getBidderId())) {
            throw new CommandException(MESSAGE_INVALID_BIDDER_ID);
        }
        if (!isValidPrice(bid.getBidAmount().getPrice())) {
            throw new CommandException(MESSAGE_INVALID_BID_AMOUNT);
        }
    }

    @Override
    public void updateSortedBidList(Comparator<Bid> comparator) {
        requireAllNonNull(comparator);
        sortedBids.setComparator(comparator);
        bidBook.setBids(sortedBids);
    }

    @Override
    public void deleteBid(Bid target) {
        bidBook.removeBid(target);
    }

    @Override
    public void setBid(Bid target, Bid editedBid) {
        requireAllNonNull(target, editedBid);

        bidBook.setBid(target, editedBid);
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
    public void deletePropertyByPropertyId(PropertyId propertyId) {
        propertyBook.removePropertyByPropertyId(propertyId);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public Property getPropertyById(PropertyId propertyId) {
        return propertyBook.getPropertyById(propertyId);
    }

    @Override
    public boolean containsPropertyId(PropertyId propertyId) {
        return propertyBook.containsPropertyId(propertyId);
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
    public void setMeetingManager(ReadOnlyMeetingBook meetingManager) {
        this.meetingBook.resetData(meetingManager);
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return meetingBook;
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeetings(meeting);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        meetingBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        meetingBook.setMeeting(target, editedMeeting);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Meeting> getSortedMeetingList() {
        return sortedMeetings;
    }

    @Override
    public void updateSortedMeetingList(Comparator<Meeting> comparator) {
        requireAllNonNull(comparator);
        sortedMeetings.setComparator(comparator);
        meetingBook.setMeetings(sortedMeetings);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedMeetingBook}
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    //=========== Bidder =============================================================

    @Override
    public void setBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) {
        this.bidderAddressBook.resetData(bidderAddressBook);
    }

    @Override
    public ReadOnlyBidderAddressBook getBidderAddressBook() {
        return bidderAddressBook;
    }

    @Override
    public boolean hasBidder(Bidder bidder) {
        requireNonNull(bidder);
        return bidderAddressBook.hasBidder(bidder);
    }

    @Override
    public void deleteBidder(Bidder target) {
        bidderAddressBook.removeBidder(target);
    }

    @Override
    public void addBidder(Bidder bidder) {
        bidderAddressBook.addBidder(bidder);
        updateFilteredBidderList(PREDICATE_SHOW_ALL_BIDDERS);
    }

    @Override
    public void setBidder(Bidder target, Bidder editedBidder) {
        requireAllNonNull(target, editedBidder);

        bidderAddressBook.setBidder(target, editedBidder);
    }

    @Override
    public boolean containsBidderId(BidderId bidderId) {
        return bidderAddressBook.containsBidderId(bidderId);
    }

    @Override
    public Path getBidderAddressBookFilePath() {
        return userPrefs.getBidderAddressBookFilePath();
    }

    @Override
    public void setBidderAddressBookFilePath(Path bidderAddressBookFilePath) {
        requireNonNull(bidderAddressBookFilePath);
        userPrefs.setAddressBookFilePath(bidderAddressBookFilePath);
    }

    @Override
    public ObservableList<Bidder> getFilteredBidderList() {
        return filteredBidders;
    }

    @Override
    public void updateFilteredBidderList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredBidders.setPredicate(predicate);
    }

    //=========== Seller =============================================================

    @Override
    public void setSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) {
        this.sellerAddressBook.resetData(sellerAddressBook);
    }

    @Override
    public ReadOnlySellerAddressBook getSellerAddressBook() {
        return sellerAddressBook;
    }

    @Override
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return sellerAddressBook.hasSeller(seller);
    }

    @Override
    public void deleteSeller(Seller target) {
        sellerAddressBook.removeSeller(target);
    }

    @Override
    public void addSeller(Seller seller) {
        sellerAddressBook.addSeller(seller);
        updateFilteredSellerList(PREDICATE_SHOW_ALL_SELLERS);
    }

    @Override
    public void setSeller(Seller target, Seller editedSeller) {
        requireAllNonNull(target, editedSeller);

        sellerAddressBook.setSeller(target, editedSeller);
    }

    @Override
    public Path getSellerAddressBookFilePath() {
        return userPrefs.getSellerAddressBookFilePath();
    }

    @Override
    public void setSellerAddressBookFilePath(Path sellerAddressBookFilePath) {
        requireNonNull(sellerAddressBookFilePath);
        userPrefs.setAddressBookFilePath(sellerAddressBookFilePath);
    }

    @Override
    public ObservableList<Seller> getFilteredSellerList() {
        return filteredSellers;
    }

    @Override
    public void updateFilteredSellerList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredSellers.setPredicate(predicate);
    }

    //=========== EQUALS =============================================================

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
                && filteredProperties.equals(other.filteredProperties)
                && bidBook.equals(other.bidBook)
                && filteredBids.equals(other.filteredBids);
    }
}
