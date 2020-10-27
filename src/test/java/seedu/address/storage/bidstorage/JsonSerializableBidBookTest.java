package seedu.address.storage.bidstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.bidbook.BidBook;
import seedu.address.testutil.bids.TypicalBid;

public class JsonSerializableBidBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBidBookTest");
    private static final Path TYPICAL_BIDS_FILE = TEST_DATA_FOLDER.resolve("typicalBidBook.json");
    private static final Path INVALID_BID_FILE = TEST_DATA_FOLDER.resolve("invalidBidBook.json");
    private static final Path DUPLICATE_BID_FILE = TEST_DATA_FOLDER.resolve("duplicateBidBook.json");

    @Test
    public void toModelType_typicalBidsFile_success() throws Exception {
        JsonSerializableBidBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_BIDS_FILE,
                JsonSerializableBidBook.class).get();
        BidBook bidBookFromFile = dataFromFile.toModelType();
        BidBook typicalBidBook = TypicalBid.getTypicalBidBook();
        assertEquals(bidBookFromFile, typicalBidBook);
    }

    @Test
    public void toModelType_invalidBidFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBidBook dataFromFile = JsonUtil.readJsonFile(INVALID_BID_FILE,
                JsonSerializableBidBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBids_throwsIllegalValueException() throws Exception {
        JsonSerializableBidBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_BID_FILE,
                JsonSerializableBidBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBidBook.MESSAGE_DUPLICATE_BID,
                dataFromFile::toModelType);
    }

}
