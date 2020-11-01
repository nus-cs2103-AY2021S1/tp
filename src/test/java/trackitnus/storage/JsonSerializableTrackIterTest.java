package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.commons.util.JsonUtil;
import trackitnus.model.TrackIter;
import trackitnus.testutil.Assert;
import trackitnus.testutil.typical.TypicalTrackIter;

public class JsonSerializableTrackIterTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackIterTest");
    private static final Path TYPICAL_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("typicalTrackIter.json");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactTrackIter.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve("duplicateContactTrackIter.json");

    @Test
    public void toModelType_typicalContactsFile_success() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTACTS_FILE,
            JsonSerializableTrackIter.class).get();
        TrackIter trackIterFromFile = dataFromFile.toModelType();
        TrackIter typicalContactsTrackIter = TypicalTrackIter.getTypicalTrackIter();
        assertEquals(trackIterFromFile, typicalContactsTrackIter);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
            JsonSerializableTrackIter.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackIter dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
            JsonSerializableTrackIter.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableTrackIter.MESSAGE_DUPLICATE_CONTACT,
            dataFromFile::toModelType);
    }

}
