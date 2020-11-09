package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.propertybook.PropertyModel.PREDICATE_SHOW_ALL_PROPERTIES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.BIDDER_ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.BIDDER_GEORGE;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.meeting.TypicalMeeting.ADMINMEETING01;
import static seedu.address.testutil.meeting.TypicalMeeting.PAPERWORKMEETING05;
import static seedu.address.testutil.meeting.TypicalMeeting.VIEWINGMEETING03;
import static seedu.address.testutil.meeting.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_ALICE;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_BENSON;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.BidderModel;
import seedu.address.model.id.PropertyId;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.InvalidSellerIdException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.model.selleraddressbook.SellerModel;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.bidder.BidderAddressBookBuilder;
import seedu.address.testutil.property.PropertyBookBuilder;
import seedu.address.testutil.property.PropertyBuilder;
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
    public void setPropertyBook_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPropertyBook(null));
    }

    @Test
    public void setPropertyBook() {
        modelManager.setPropertyBook(getTypicalPropertyBook());
        assertEquals(getTypicalPropertyBook(), modelManager.getPropertyBook());
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
    public void hasProperty_sameIdentity_returnsTrue() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        Property other = new PropertyBuilder(PROPERTY_B)
                .withAddress(PROPERTY_A.getAddress().toString())
                .build();
        assertTrue(modelManager.hasProperty(other));
    }

    @Test
    public void hasPropertyExceptPropertyId_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.hasPropertyExceptPropertyId(null, new PropertyId(1)));
    }

    @Test
    public void hasPropertyExceptPropertyId_nullPropertyId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPropertyExceptPropertyId(PROPERTY_A, null));
    }

    @Test
    public void hasPropertyExceptPropertyId_notFoundInPropertyBook_returnsFalse() {
        assertFalse(modelManager.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_B.getPropertyId()));

        // inside property book but has excluded property id
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        assertFalse(modelManager.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_A.getPropertyId()));
    }

    @Test
    public void hasPropertyExceptPropertyId_inPropertyBook_returnsTrue() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        assertTrue(modelManager.hasPropertyExceptPropertyId(PROPERTY_A, PROPERTY_B.getPropertyId()));
    }

    @Test
    public void deleteProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteProperty(null));
    }

    @Test
    public void deleteProperty_doesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> modelManager.deleteProperty(PROPERTY_A));
    }

    @Test
    public void deleteProperty_succeeds() {
        // no bids or meetings
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.addProperty(PROPERTY_A);
        modelManager.deleteProperty(PROPERTY_A);
        ModelManager expected = new ModelManager();
        expected.setSellerAddressBook(getTypicalSellerAddressBook());
        assertEquals(expected, modelManager);

        // cascade delete bids and meetings
        modelManager = TestUtil.getTypicalModelManager();
        modelManager.deleteProperty(PROPERTY_A);

        PropertyBook expectedPropertyBook = getTypicalPropertyBook();
        expectedPropertyBook.removeProperty(PROPERTY_A);
        BidBook expectedBidBook = getTypicalBidBook();
        expectedBidBook.removeAllBidsWithPropertyId(PROPERTY_A.getPropertyId());
        MeetingBook expectedMeetingBook = getTypicalMeetingAddressBook();
        expectedMeetingBook.removeAllMeetingsWithPropertyId(PROPERTY_A.getPropertyId());
        expected = TestUtil.getTypicalModelManager();
        expected.setPropertyBook(expectedPropertyBook);
        expected.setBidBook(expectedBidBook);
        expected.setMeetingManager(expectedMeetingBook);
        assertEquals(expected, modelManager);
    }

    @Test
    public void deletePropertyByPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deletePropertyByPropertyId(null));
    }

    @Test
    public void deletePropertyByPropertyId_propertyNotFound_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> modelManager.deletePropertyByPropertyId(new PropertyId(1)));
    }

    @Test
    public void deletePropertyByPropertyId_success() {
        // no cascading
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.setPropertyBook(getTypicalPropertyBook());
        modelManager.deletePropertyByPropertyId(PROPERTY_A.getPropertyId());
        ModelManager expected = new ModelManager();
        expected.setSellerAddressBook(getTypicalSellerAddressBook());
        PropertyBook expectedPropertyBook = getTypicalPropertyBook();
        expectedPropertyBook.removeProperty(PROPERTY_A);
        expected.setPropertyBook(expectedPropertyBook);
        assertEquals(expected, modelManager);

        // cascading delete
        modelManager = TestUtil.getTypicalModelManager();
        modelManager.deletePropertyByPropertyId(PROPERTY_A.getPropertyId());
        expected = TestUtil.getTypicalModelManager();
        expected.setPropertyBook(expectedPropertyBook);
        BidBook expectedBidBook = getTypicalBidBook();
        expectedBidBook.removeAllBidsWithPropertyId(PROPERTY_A.getPropertyId());
        MeetingBook expectedMeetingBook = getTypicalMeetingAddressBook();
        expectedMeetingBook.removeAllMeetingsWithPropertyId(PROPERTY_A.getPropertyId());
        expected.setPropertyBook(expectedPropertyBook);
        expected.setMeetingManager(expectedMeetingBook);
        expected.setBidBook(expectedBidBook);
        assertEquals(expected, modelManager);
    }

    @Test
    public void addProperty_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addProperty(null));
    }

    @Test
    public void addProperty_invalidProperty_throwsInvalidSellerIdException() {
        assertThrows(InvalidSellerIdException.class, () -> modelManager.addProperty(PROPERTY_A));
    }

    @Test
    public void addProperty_addDuplicateProperty_throwsDuplicatePropertyException() {
        modelManager = TestUtil.getTypicalModelManager();

        // same object
        assertThrows(DuplicatePropertyException.class, () -> modelManager.addProperty(PROPERTY_A));

        // same property id
        Property samePropertyA = new PropertyBuilder(PROPERTY_B)
                .withPropertyId(PROPERTY_A.getPropertyId().toString())
                .build();
        assertThrows(DuplicatePropertyException.class, () -> modelManager.addProperty(samePropertyA));

        // same address
        Property anotherPropertyA = new PropertyBuilder(PROPERTY_B)
                .withAddress(PROPERTY_A.getAddress().toString())
                .build();
        assertThrows(DuplicatePropertyException.class, () -> modelManager.addProperty(anotherPropertyA));
    }

    @Test
    public void addProperty_success() {
        modelManager = TestUtil.getTypicalModelManager();
        Property propertyD = new PropertyBuilder()
                .withPropertyId(PropertyId.DEFAULT_PROPERTY_ID.toString())
                .withPropertyName("Dempsey Hill")
                .withAddress("101 Dempsey Dill")
                .withSellerId("S2")
                .withAskingPrice(99.99)
                .withIsClosedDeal("active")
                .withPropertyType("Bungalow")
                .withIsRental("No")
                .build();
        modelManager.addProperty(propertyD);
        ModelManager expected = TestUtil.getTypicalModelManager();
        PropertyBook expectedPropertyBook = getTypicalPropertyBook();
        expectedPropertyBook.addProperty(propertyD);
        expected.setPropertyBook(expectedPropertyBook);
        assertEquals(expected, modelManager);
    }

    @Test
    public void getPropertyById_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.getPropertyById(null));
    }

    @Test
    public void getPropertyById_propertyNotFound_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> modelManager.getPropertyById(new PropertyId(1)));
    }

    @Test
    public void getPropertyByPropertyId_propertyInPropertyBook_returnsProperty() {
        modelManager = TestUtil.getTypicalModelManager();
        assertEquals(PROPERTY_A, modelManager.getPropertyById(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void containsPropertyId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.containsPropertyId(null));
    }

    @Test
    public void containsPropertyId_notInPropertyBook_returnsFalse() {
        assertFalse(modelManager.containsPropertyId(new PropertyId(1)));
    }

    @Test
    public void containsPropertyId_inPropertyBook_returnsTrue() {
        modelManager = TestUtil.getTypicalModelManager();
        assertTrue(modelManager.containsPropertyId(PROPERTY_A.getPropertyId()));
    }

    @Test
    public void setProperty_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProperty(null, PROPERTY_A));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProperty(PROPERTY_A, null));
    }

    @Test
    public void setProperty_propertyNotFound_throwsPropertyNotFoundException() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        assertThrows(PropertyNotFoundException.class, () -> modelManager.setProperty(PROPERTY_A, PROPERTY_B));
    }

    @Test
    public void setProperty_invalidEditedProperty_throwsInvalidSellerIdException() {
        modelManager.setSellerAddressBook(getTypicalSellerAddressBook());
        modelManager.setPropertyBook(getTypicalPropertyBook());
        Property editedProperty = new PropertyBuilder(PROPERTY_A)
                .withSellerId("S100")
                .build();
        assertThrows(InvalidSellerIdException.class, () -> modelManager.setProperty(PROPERTY_A, editedProperty));
    }

    @Test
    public void setProperty_editedPropertyExists_throwsDuplicatePropertyException() {
        modelManager = TestUtil.getTypicalModelManager();

        // same object
        assertThrows(DuplicatePropertyException.class, () -> modelManager.setProperty(PROPERTY_A, PROPERTY_B));

        // same property id
        Property sameId = new PropertyBuilder(PROPERTY_A)
                .withPropertyId(PROPERTY_B.getPropertyId().toString())
                .build();
        assertThrows(DuplicatePropertyException.class, () -> modelManager.setProperty(PROPERTY_A, sameId));

        // same address
        Property sameAddress = new PropertyBuilder(PROPERTY_A)
                .withAddress(PROPERTY_B.getAddress().toString())
                .build();
        assertThrows(DuplicatePropertyException.class, () -> modelManager.setProperty(PROPERTY_A, sameAddress));
    }

    @Test
    public void setProperty_success() {
        Property editedProperty = new PropertyBuilder(PROPERTY_A)
                .withPropertyName("New name")
                .build();
        modelManager = TestUtil.getTypicalModelManager();
        modelManager.setProperty(PROPERTY_A, editedProperty);
        ModelManager expected = TestUtil.getTypicalModelManager();
        PropertyBook expectedPropertyBook = getTypicalPropertyBook();
        expectedPropertyBook.setProperty(PROPERTY_A, editedProperty);
        expected.setPropertyBook(expectedPropertyBook);
        assertEquals(expected, modelManager);
    }

    @Test
    public void getFilteredPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPropertyList().remove(0));
    }

    @Test
    public void updateFilteredPropertyList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredPropertyList(null));
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

    @Test
    public void cascadingDeleteBidder_returnsTrue() {
        // no cascading
        // bids or meeting to be removed - only BIDDER_GEORGE
        modelManager.addBidder(BIDDER_GEORGE);
        modelManager.deleteBidder(BIDDER_GEORGE);
        ModelManager expectedModelManager = new ModelManager();
        assertEquals(expectedModelManager, modelManager);

        // cascading delete
        modelManager = TestUtil.getTypicalModelManager();
        modelManager.deleteBidder(ALICE); // with bidderId B1
        expectedModelManager = TestUtil.getTypicalModelManager();

        BidderAddressBook expectedBidderAddressBook = getTypicalBidderAddressBook();
        expectedBidderAddressBook.removeBidder(ALICE);
        expectedModelManager.setBidderAddressBook(expectedBidderAddressBook);

        BidBook expectedBidBook = getTypicalBidBook();
        expectedBidBook.removeBid(BID_A);
        expectedModelManager.setBidBook(expectedBidBook);

        MeetingBook expectedMeetingBook = getTypicalMeetingAddressBook();
        expectedMeetingBook.removeMeeting(ADMINMEETING01);
        expectedMeetingBook.removeMeeting(VIEWINGMEETING03);
        expectedMeetingBook.removeMeeting(PAPERWORKMEETING05);
        expectedModelManager.setMeetingManager(expectedMeetingBook);

        assertEquals(expectedModelManager, modelManager);
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

    @Test
    public void cascadingDeleteSeller_returnsTrue() {
        // no cascading
        // properties removed - only BIDDER_GEORGE
        modelManager.addSeller(SELLER_BENSON);
        modelManager.deleteSeller(SELLER_BENSON);
        ModelManager expectedModelManager = new ModelManager();
        assertEquals(expectedModelManager, modelManager);

        // cascading delete
        modelManager = TestUtil.getTypicalModelManager();
        modelManager.deleteSeller(SELLER_BENSON); // with sellerId S2
        expectedModelManager = TestUtil.getTypicalModelManager();

        SellerAddressBook expectedSellerAddessBook = getTypicalSellerAddressBook();
        expectedSellerAddessBook.removeSeller(SELLER_BENSON);
        expectedModelManager.setSellerAddressBook(expectedSellerAddessBook);

        PropertyBook expectedPropertyBook = getTypicalPropertyBook();
        expectedPropertyBook.removeProperty(PROPERTY_B);
        expectedModelManager.setPropertyBook(expectedPropertyBook);

        BidBook expectedBidBook = getTypicalBidBook();
        expectedBidBook.removeAllBidsWithPropertyId(PROPERTY_B.getPropertyId());
        expectedModelManager.setBidBook(expectedBidBook);

        MeetingBook expectedMeetingBook = getTypicalMeetingAddressBook();
        expectedMeetingBook.removeAllMeetingsWithPropertyId(PROPERTY_B.getPropertyId());
        expectedModelManager.setMeetingManager(expectedMeetingBook);

        assertEquals(expectedModelManager, modelManager);
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
