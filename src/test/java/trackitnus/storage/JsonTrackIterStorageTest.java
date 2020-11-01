package trackitnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackitnus.commons.exceptions.DataConversionException;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.TrackIter;
import trackitnus.testutil.Assert;
import trackitnus.testutil.typical.TypicalContacts;
import trackitnus.testutil.typical.TypicalTrackIter;

public class JsonTrackIterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackIterStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTrackIter_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readTrackIter(null));
    }

    private java.util.Optional<ReadOnlyTrackIter> readTrackIter(String filePath) throws Exception {
        return new JsonTrackIterStorage(Paths.get(filePath)).readTrackIter(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrackIter("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readTrackIter("notJsonFormatTrackIter.json"));
    }

    @Test
    public void readTrackIter_invalidContactTrackIter_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readTrackIter("invalidContactTrackIter.json"));
    }

    @Test
    public void readTrackIter_invalidAndValidContactTrackIter_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readTrackIter("invalidAndValidContactTrackIter"
            + ".json"));
    }

    @Test
    public void readAndSaveTrackIter_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackIter.json");
        TrackIter original = TypicalTrackIter.getTypicalTrackIter();
        JsonTrackIterStorage jsonTrackIterStorage = new JsonTrackIterStorage(filePath);
        // Save in new file and read back
        jsonTrackIterStorage.saveTrackIter(original, filePath);
        ReadOnlyTrackIter readBack = jsonTrackIterStorage.readTrackIter(filePath).get();
        assertEquals(original, new TrackIter(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(TypicalContacts.HOON);
        original.removeContact(TypicalContacts.ALICE);
        jsonTrackIterStorage.saveTrackIter(original, filePath);
        readBack = jsonTrackIterStorage.readTrackIter(filePath).get();
        assertEquals(original, new TrackIter(readBack));

        // Save and read without specifying file path
        original.addContact(TypicalContacts.IDA);
        jsonTrackIterStorage.saveTrackIter(original); // file path not specified
        readBack = jsonTrackIterStorage.readTrackIter().get(); // file path not specified
        assertEquals(original, new TrackIter(readBack));

    }

    @Test
    public void saveTrackIter_nullTrackIter_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveTrackIter(null, "SomeFile.json"));
    }

    /**
     * Saves {@code trackIter} at the specified {@code filePath}.
     */
    private void saveTrackIter(ReadOnlyTrackIter trackIter, String filePath) {
        try {
            new JsonTrackIterStorage(Paths.get(filePath))
                .saveTrackIter(trackIter, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackIter_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveTrackIter(new TrackIter(), null));
    }
}
