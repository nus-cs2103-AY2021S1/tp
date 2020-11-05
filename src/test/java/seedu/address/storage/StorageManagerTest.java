package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.meeting.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.MeetingBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.storage.bidderstorage.JsonBidderAddressBookStorage;
import seedu.address.storage.bidstorage.JsonBidBookStorage;
import seedu.address.storage.meeting.JsonMeetingBookStorage;
import seedu.address.storage.property.JsonPropertyBookStorage;
import seedu.address.storage.sellerstorage.JsonSellerAddressBookStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonBidBookStorage bidBookStorage = new JsonBidBookStorage(getTempFilePath("bb"));
        JsonBidderAddressBookStorage bidderAddressBookStorage =
                new JsonBidderAddressBookStorage(getTempFilePath("bidderb"));
        JsonSellerAddressBookStorage sellerAddressBookStorage =
                new JsonSellerAddressBookStorage(getTempFilePath("sellerb"));
        JsonMeetingBookStorage meetingBookStorage =
                new JsonMeetingBookStorage(getTempFilePath("mb"));
        JsonPropertyBookStorage propertyBookStorage =
                new JsonPropertyBookStorage(getTempFilePath("pb"));
        storageManager = new StorageManager(userPrefsStorage, bidBookStorage,
                bidderAddressBookStorage, sellerAddressBookStorage, meetingBookStorage, propertyBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getUserPrefsFilePath() {
        assertEquals(storageManager.getUserPrefsFilePath(), getTempFilePath("prefs"));
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    // ------------------- PROPERTY STORAGE -------------------

    @Test
    public void propertyBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPropertyBookStorageTest} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPropertyBookStorageTest} class.
         */
        PropertyBook original = getTypicalPropertyBook();
        storageManager.savePropertyBook(original);
        ReadOnlyPropertyBook retrieved = storageManager.readPropertyBook().get();
        assertEquals(original, new PropertyBook(retrieved));
    }

    @Test
    public void getPropertyBookFilePath() {
        assertNotNull(storageManager.getPropertyBookFilePath());
    }

    // ------------------- BID STORAGE -------------------

    @Test
    public void bidBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonBidBookStorageTest} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonBidBookStorageTest} class.
         */
        BidBook original = getTypicalBidBook();
        storageManager.saveBidBook(original);
        ReadOnlyBidBook retrieved = storageManager.readBidBook().get();
        assertEquals(original, new BidBook(retrieved));
    }

    @Test
    public void getBidBookFilePath() {
        assertNotNull(storageManager.getBidBookFilePath());
    }

    // ------------------- BIDDER STORAGE -------------------

    @Test
    public void bidderAddressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonBidderAddressBookStorageTest} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonBidderAddressBookStorageTest} class.
         */
        BidderAddressBook original = getTypicalBidderAddressBook();
        storageManager.saveBidderAddressBook(original);
        ReadOnlyBidderAddressBook retrieved = storageManager.readBidderAddressBook().get();
        assertEquals(original, new BidderAddressBook(retrieved));
    }

    @Test
    public void getBidderAddressBookFilePath() {
        assertNotNull(storageManager.getBidderAddressBookFilePath());
    }

    // ------------------- SELLER STORAGE -------------------

    @Test
    public void sellerAddressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonSellerAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonSellerAddressBookStorage} class.
         */
        SellerAddressBook original = getTypicalSellerAddressBook();
        storageManager.saveSellerAddressBook(original);
        ReadOnlySellerAddressBook retrieved = storageManager.readSellerAddressBook().get();
        assertEquals(original, new SellerAddressBook(retrieved));
    }

    @Test
    public void getSellerAddressBookFilePath() {
        assertNotNull(storageManager.getSellerAddressBookFilePath());
    }
    @Test
    public void getMeetingAddressBookFilePath() {
        assertNotNull(storageManager.getMeetingBookFilePath());
    }

    @Test
    public void meetingBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMeetingBookStorageTest} class.
         */
        MeetingBook original = getTypicalMeetingAddressBook();
        storageManager.saveMeetingBook(original);
        ReadOnlyMeetingBook retrieved = storageManager.readMeetingBook().get();
        assertEquals(original, new MeetingBook(retrieved));
    }
}
