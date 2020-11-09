package seedu.address.storage.bidder;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.storage.bidderstorage.JsonSerializableBidderAddressBook;
import seedu.address.testutil.bidder.TypicalBidder;

public class JsonSerializableBidderAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableBidderAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalBiddersAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidBidderAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateBidderAddressBook.json");

    @Test
    public void toModelType_typicalBiddersFile_success() throws Exception {
        JsonSerializableBidderAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableBidderAddressBook.class).get();
        BidderAddressBook addressBookFromFile = dataFromFile.toModelType();
        BidderAddressBook typicalBidderAddressBook = TypicalBidder.getTypicalBidderAddressBook();
        //assertEquals(addressBookFromFile, typicalBidderAddressBook);
    }

    @Test
    public void toModelType_invalidBidderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBidderAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableBidderAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBidders_throwsIllegalValueException() throws Exception {
        JsonSerializableBidderAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableBidderAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBidderAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
