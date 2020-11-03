package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.propertybook.PropertyModel.PREDICATE_SHOW_ALL_PROPERTIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.BIDDER_ALICE;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_ALICE;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.BidderModel;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.model.selleraddressbook.SellerModel;
import seedu.address.testutil.bidder.BidderAddressBookBuilder;
import seedu.address.testutil.property.PropertyBookBuilder;
import seedu.address.testutil.seller.SellerAddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new BidderAddressBook(), new BidderAddressBook(modelManager.getBidderAddressBook()));
        assertEquals(new SellerAddressBook(), new SellerAddressBook(modelManager.getSellerAddressBook()));
        assertEquals(new BidBook(), new BidBook(modelManager.getBidBook()));
        assertEquals(new MeetingBook(), new MeetingBook(modelManager.getMeetingBook()));
        assertEquals(new PropertyBook(), new PropertyBook(modelManager.getPropertyBook()));
    }

    // ------------------- USER PREFS -------------------


    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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

    // ------------------- BID BOOK -------------------

    @Test
    public void setBidBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBidBookFilePath(null));
    }

    @Test
    public void setBidBookFilePath_validPath_setsBidBookFilePath() {
        Path path = Paths.get("bid/book/file/path");
        modelManager.setBidBookFilePath(path);
        assertEquals(path, modelManager.getBidBookFilePath());
    }

    @Test
    public void hasBid_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBid(null));
    }

    @Test
    public void hasBid_bidNotInBidBook_returnsFalse() {
        assertFalse(modelManager.hasBid(BID_A));
    }

    @Test
    public void getFilteredBidList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBidList().remove(0));
    }

    // ------------------- PROPERTY BOOK -------------------

    @Test
    public void setPropertyBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyBookFilePath(null));
    }

    @Test
    public void setPropertyBookFilePath_validPath_setsPropertyBookFilePath() {
        Path path = Paths.get("property/book/file/path");
        modelManager.setPropertyBookFilePath(path);
        assertEquals(path, modelManager.getPropertyBookFilePath());
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(modelManager.hasProperty(PROPERTY_A));
    }

    @Test
    public void hasProperty_propertyInPropertyBook_returnsTrue() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        assertTrue(modelManager.hasProperty(PROPERTY_A));
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    // ----------------- BIDDER ---------------------
    @Test
    public void setBidderAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBidderAddressBookFilePath(null));
    }

    @Test
    public void setBidderAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("data/bidderaddressbook.json");
        modelManager.setBidderAddressBookFilePath(path);
        assertEquals(path, modelManager.getBidderAddressBookFilePath());
    }

    @Test
    public void hasBidder_nullBidder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBidder(null));
    }

    @Test
    public void hasBidder_bidderNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasBidder(BIDDER_ALICE));
    }

    @Test
    public void hasBidder_bidderInAddressBook_returnsTrue() {
        modelManager.addBidder(BIDDER_ALICE);
        assertTrue(modelManager.hasBidder(BIDDER_ALICE));
    }

    @Test
    public void getFilteredBidderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBidderList().remove(0));
    }

    // ----------------- SELLER ---------------------
    @Test
    public void setSellerAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSellerAddressBookFilePath(null));
    }

    @Test
    public void setSellerAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("data/selleraddressbook.json");
        modelManager.setSellerAddressBookFilePath(path);
        assertEquals(path, modelManager.getSellerAddressBookFilePath());
    }

    @Test
    public void hasSeller_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasSeller(null));
    }

    @Test
    public void hasSeller_sellerNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasSeller(SELLER_ALICE));
    }

    @Test
    public void hasSeller_sellerInAddressBook_returnsTrue() {
        modelManager.addSeller(SELLER_ALICE);
        assertTrue(modelManager.hasSeller(SELLER_ALICE));
    }

    @Test
    public void getFilteredSellerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredSellerList().remove(0));
    }
    // ------------------- GENERAL -------------------

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();

        BidderAddressBook bidderAddressBook = new BidderAddressBookBuilder().withBidder(BIDDER_ALICE).build();
        BidderAddressBook differentBidderAddressBook = new BidderAddressBook();

        SellerAddressBook sellerAddressBook = new SellerAddressBookBuilder().withSeller(SELLER_ALICE).build();
        SellerAddressBook differentSellerAddressBook = new SellerAddressBook();

        BidBook bidBook = new BidBook();
        MeetingBook meetingBook = new MeetingBook();

        PropertyBook propertyBook = new PropertyBookBuilder()
                .withProperty(PROPERTY_A).withProperty(PROPERTY_B).build();
        PropertyBook differentPropertyBook = new PropertyBook();

        modelManager = new ModelManager(userPrefs,
                bidBook,
                propertyBook,
                bidderAddressBook,
                sellerAddressBook,
                meetingBook);

        ModelManager modelManagerCopy = new ModelManager(userPrefs,
                bidBook,
                propertyBook,
                bidderAddressBook,
                sellerAddressBook,
                meetingBook);

        // same values -> returns true

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different bidderAddressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs,
                bidBook, propertyBook, differentBidderAddressBook, sellerAddressBook, meetingBook)));

        // different sellerAddressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs,
                bidBook, propertyBook, bidderAddressBook, differentSellerAddressBook, meetingBook)));

        // different propertyBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs,
                bidBook, differentPropertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // different filteredPropertyList -> returns false
        modelManager.updateFilteredPropertyList(property -> property.equals(PROPERTY_A));
        assertFalse(modelManager.equals(new ModelManager(userPrefs,
                bidBook, propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredSellerList(SellerModel.PREDICATE_SHOW_ALL_SELLERS);
        modelManager.updateFilteredBidderList(BidderModel.PREDICATE_SHOW_ALL_BIDDERS);
        modelManagerCopy.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));

        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, bidBook,
                propertyBook, bidderAddressBook, sellerAddressBook, meetingBook)));
    }
}
