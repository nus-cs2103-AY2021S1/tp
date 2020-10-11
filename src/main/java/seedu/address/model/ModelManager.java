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
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model, SellerModel, BidderModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BidBook bidBook;
    private final AddressBook addressBook;
    private final BidderAddressBook bidderAddressBook;
    private final SellerAddressBook sellerAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Seller> filteredSellers;
    private final FilteredList<Bidder> filteredBidders;
    private final FilteredList<Bid> filteredBids;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyBidderAddressBook bidderAddressBook,
                        ReadOnlySellerAddressBook sellerAddressBook,
                        ReadOnlyBidBook bidBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, bidderAddressBook, sellerAddressBook, bidBook);

        logger.fine("Initializing with address book: " + addressBook
                + " and user prefs " + userPrefs + " and bid book: " + bidBook
                + "\n bidderAddressBook: " + bidderAddressBook
                + "\n sellerAddressBook: " + sellerAddressBook);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.bidderAddressBook = new BidderAddressBook(bidderAddressBook);
        this.sellerAddressBook = new SellerAddressBook(sellerAddressBook);
        this.bidBook = new BidBook(bidBook);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredBidders = new FilteredList<>(this.bidderAddressBook.getBidderList());
        filteredSellers = new FilteredList<>(this.sellerAddressBook.getSellerList());

        filteredBids = new FilteredList<>(this.bidBook.getBidList());
    }

    /**
     * Constructor for the ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(), new UserPrefs(),
                new BidderAddressBook(),
                new SellerAddressBook(),
                new BidBook());
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
    public void updateFilteredBidderList(Predicate<Bidder> predicate) {
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
    public void updateFilteredSellerList(Predicate<Seller> predicate) {
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
                && filteredPersons.equals(other.filteredPersons);
    }

}
