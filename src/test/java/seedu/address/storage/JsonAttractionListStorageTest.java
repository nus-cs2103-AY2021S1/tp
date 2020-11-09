package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.JURONG_BIRD_PARK;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AttractionList;
import seedu.address.model.ReadOnlyAttractionList;

public class JsonAttractionListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackPadStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAttractionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAttractionList(null));
    }

    private java.util.Optional<ReadOnlyAttractionList> readAttractionList(String filePath) throws Exception {
        return new JsonAttractionListStorage(Paths.get(filePath))
                .readAttractionList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAttractionList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAttractionList("notJsonFormatTrackPad.json"));
    }

    @Test
    public void readAttractionList_invalidAttractionTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAttractionList("invalidAttractionTrackPad.json"));
    }

    @Test
    public void readAttractionList_invalidAndValidAttractionTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAttractionList("invalidAndValidAttractionTrackPad.json"));
    }

    @Test
    public void readAndSaveAttractionList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAttractionList.json");
        AttractionList original = getTypicalAttractionList();
        JsonAttractionListStorage jsonAttractionListStorage = new JsonAttractionListStorage(filePath);

        // Save in new file and read back
        jsonAttractionListStorage.saveAttractionList(original, filePath);
        ReadOnlyAttractionList readBack = jsonAttractionListStorage.readAttractionList(filePath).get();
        assertEquals(original, new AttractionList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAttraction(MBS);
        original.removeAttraction(JURONG_BIRD_PARK);
        jsonAttractionListStorage.saveAttractionList(original, filePath);
        readBack = jsonAttractionListStorage.readAttractionList(filePath).get();
        assertEquals(original, new AttractionList(readBack));

        // Save and read without specifying file path
        original.addAttraction(EIFFEL_TOWER);
        jsonAttractionListStorage.saveAttractionList(original); // file path not specified
        readBack = jsonAttractionListStorage.readAttractionList().get(); // file path not specified
        assertEquals(original, new AttractionList(readBack));

    }

    @Test
    public void saveAttractionList_nullAttractionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAttractionList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code attractionList} at the specified {@code filePath}.
     */
    private void saveAttractionList(ReadOnlyAttractionList attractionList, String filePath) {
        try {
            new JsonAttractionListStorage(Paths.get(filePath))
                    .saveAttractionList(attractionList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAttractionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAttractionList(new AttractionList(), null));
    }
}
