package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_BIDDER_ID;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_BID_AMOUNT;
import static seedu.address.logic.commands.bidcommands.AddBidCommand.MESSAGE_INVALID_PROPERTY_ID;
import static seedu.address.model.price.Price.isValidPrice;

import java.nio.file.Path;
import java.util.ArrayList;
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
import seedu.address.model.bid.BidComparator;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.InvalidSellerIdException;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final BidComparator bidComparator = new BidComparator();

    private final BidBook bidBook;
    private final BidderAddressBook bidderAddressBook;
    private final SellerAddressBook sellerAddressBook;
    private final UserPrefs userPrefs;
    private final PropertyBook propertyBook;
    private final MeetingBook meetingBook;

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

    public ModelManager(ReadOnlyUserPrefs userPrefs, ReadOnlyBidBook bidBook,
                        ReadOnlyPropertyBook propertyBook, ReadOnlyBidderAddressBook bidderAddressBook,
                        ReadOnlySellerAddressBook sellerAddressBook, ReadOnlyMeetingBook meetingManager) {
        super();
        requireAllNonNull(userPrefs, bidBook, propertyBook,
                bidderAddressBook, sellerAddressBook, meetingManager);

        logger.fine("Initializing with: "
                + "\n user prefs " + userPrefs
                + "\n bid book: " + bidBook
                + "\n property book: " + propertyBook
                + "\n bidderAddressBook: " + bidderAddressBook
                + "\n sellerAddressBook: " + sellerAddressBook
                + "\n and meeting manager" + meetingManager
        );

        this.userPrefs = new UserPrefs(userPrefs);
        this.bidderAddressBook = new BidderAddressBook(bidderAddressBook);
        this.sellerAddressBook = new SellerAddressBook(sellerAddressBook);
        this.bidBook = new BidBook(bidBook);
        this.meetingBook = new MeetingBook(meetingManager);
        this.propertyBook = new PropertyBook(propertyBook);

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
        this (new UserPrefs(), new BidBook(), new PropertyBook(), new BidderAddressBook(), new SellerAddressBook(),
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

    //=========== BidBook ================================================================================


    @Override
    public void setBidBook(ReadOnlyBidBook bidBook) {
        this.bidBook.resetData(bidBook);
    }

    @Override
    public ReadOnlyBidBook getBidBook() {
        return bidBook;
    }

    @Override
    public ObservableList<Bid> getFilteredBidList() {
        return filteredBids;
    }

    @Override
    public void addBid(Bid bid) throws CommandException {
        checkIsValidBid(bid);
        bidBook.addBid(bid);
        updateSortedBidList(bidComparator);
        updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
    }

    @Override
    public void updateFilteredBidList(Predicate<Bid> predicate) {
        requireNonNull(predicate);
        updateSortedBidList(bidComparator);
        filteredBids.setPredicate(predicate);
    }

    @Override
    public boolean hasBid(Bid bid) {
        requireNonNull(bid);
        return bidBook.hasBid(bid);
    }

    /**
     * checks a bid against the property and bidder list to see if the ids exists and if bid amount is valid
     * @param bid bid to validate
     * @throws CommandException
     */
    private void checkIsValidBid(Bid bid) throws CommandException {
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

    /**
     * sorts the list using the comparator
     * @param comparator predicate to use to compare
     */
    private void updateSortedBidList(Comparator<Bid> comparator) {
        requireAllNonNull(comparator);
        sortedBids.setComparator(comparator);
        bidBook.setBids(sortedBids);
    }

    @Override
    public void deleteBid(Bid target) {
        bidBook.removeBid(target);
    }

    @Override
    public void setBid(Bid target, Bid editedBid) throws CommandException {
        requireAllNonNull(target, editedBid);
        checkIsValidBid(editedBid);
        bidBook.setBid(target, editedBid);
    }

    @Override
    public void setBidBookFilePath(Path bidBookFilePath) {
        requireNonNull(bidBookFilePath);
        userPrefs.setBidBookFilePath(bidBookFilePath);
    }

    @Override
    public Path getBidBookFilePath() {
        return userPrefs.getBidBookFilePath();
    }

    //=========== Filtered List Accessors =============================================================

    //=========== PropertyBook ================================================================================

    @Override
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setPropertyBookFilePath(propertyBookFilePath);
    }

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
    public boolean hasPropertyExceptPropertyId(Property property, PropertyId excludedId) {
        requireAllNonNull(property, excludedId);
        return propertyBook.hasPropertyExceptPropertyId(property, excludedId);
    }

    @Override
    public void deleteProperty(Property target) {
        PropertyId propertyId = target.getPropertyId();
        bidBook.removeAllBidsWithPropertyId(propertyId);
        meetingBook.removeAllMeetingsWithPropertyId(propertyId);
        propertyBook.removeProperty(target);
    }

    @Override
    public void deletePropertyByPropertyId(PropertyId propertyId) {
        bidBook.removeAllBidsWithPropertyId(propertyId);
        meetingBook.removeAllMeetingsWithPropertyId(propertyId);
        propertyBook.removePropertyByPropertyId(propertyId);
    }

    private boolean isValidProperty(Property property) {
        return sellerAddressBook.containsSellerId(property.getSellerId());
    }

    @Override
    public Property addProperty(Property property) {
        if (!isValidProperty(property)) {
            throw new InvalidSellerIdException();
        }
        Property added = propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return added;
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
        if (!isValidProperty(editedProperty)) {
            throw new InvalidSellerIdException();
        }
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
    public void addMeeting(Meeting meeting) throws CommandException {
        checkIsValidMeeting(meeting);
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    /**
     * checks a bid against the property and bidder list to see if the ids exists and if bid amount is valid
     * @param  meeting meeting to validate
     * @throws CommandException
     */
    private void checkIsValidMeeting(Meeting meeting) throws CommandException {
        requireNonNull(meeting);
        if (!containsPropertyId(meeting.getPropertyId())) {
            throw new CommandException(MESSAGE_INVALID_PROPERTY_ID);
        }
        if (!containsBidderId(meeting.getBidderId())) {
            throw new CommandException(MESSAGE_INVALID_BIDDER_ID);
        }
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
        bidBook.removeAllBidsWithBidderId((BidderId) target.getId());
        meetingBook.removeAllMeetingsWithBidderId((BidderId) target.getId());
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

    @Override
    public boolean hasBidderExceptBidderId(Bidder editedBidder, BidderId bidderId) {
        requireNonNull(editedBidder);
        return bidderAddressBook.hasBidderExceptBidderId(editedBidder, bidderId);
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
        ArrayList<Property> propertiesToRemove = propertyBook.getPropertiesBySellerId((SellerId) target.getId());
        propertiesToRemove.forEach(this::deleteProperty);
        propertyBook.removeAllPropertiesWithSellerId((SellerId) target.getId());
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

    @Override
    public boolean hasSellerExceptSellerId(Seller editedSeller, SellerId sellerId) {
        requireNonNull(editedSeller);
        return sellerAddressBook.hasSellerExceptSellerId(editedSeller, sellerId);
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
        return userPrefs.equals(other.userPrefs)
                && sellerAddressBook.equals(other.sellerAddressBook)
                && filteredSellers.equals(other.filteredSellers)

                && bidderAddressBook.equals(other.bidderAddressBook)
                && filteredBidders.equals(other.filteredBidders)

                && meetingBook.equals(other.meetingBook)
                && filteredMeetings.equals(other.filteredMeetings)

                && propertyBook.equals(other.propertyBook)
                && filteredProperties.equals(other.filteredProperties)

                && bidBook.equals(other.bidBook)
                && filteredBids.equals(other.filteredBids);
    }
}
