package jimmy.mcgymmy.storage;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static jimmy.mcgymmy.testutil.TypicalFoods.ALICE;
import static jimmy.mcgymmy.testutil.TypicalFoods.HOON;
import static jimmy.mcgymmy.testutil.TypicalFoods.IDA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class JsonMcGymmyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMcGymmyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMcGymmy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMcGymmy(null));
    }

    private java.util.Optional<ReadOnlyMcGymmy> readMcGymmy(String filePath) throws Exception {
        return new JsonMcGymmyStorage(Paths.get(filePath)).readMcGymmy(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMcGymmy("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMcGymmy("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readMcGymmy_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMcGymmy("invalidPersonAddressBook.json"));
    }

    @Test
    public void readMcGymmy_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMcGymmy("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        McGymmy original = TypicalFoods.getTypicalMcGymmy();
        JsonMcGymmyStorage jsonAddressBookStorage = new JsonMcGymmyStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveMcGymmy(original, filePath);
        ReadOnlyMcGymmy readBack = jsonAddressBookStorage.readMcGymmy(filePath).get();
        assertEquals(original, new McGymmy(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFood(HOON);
        original.removeFood(ALICE);
        jsonAddressBookStorage.saveMcGymmy(original, filePath);
        readBack = jsonAddressBookStorage.readMcGymmy(filePath).get();
        assertEquals(original, new McGymmy(readBack));

        // Save and read without specifying file path
        original.addFood(IDA);
        jsonAddressBookStorage.saveMcGymmy(original); // file path not specified
        readBack = jsonAddressBookStorage.readMcGymmy().get(); // file path not specified
        assertEquals(original, new McGymmy(readBack));

    }

    @Test
    public void saveMcGymmy_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMcGymmy(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveMcGymmy(ReadOnlyMcGymmy addressBook, String filePath) {
        try {
            new JsonMcGymmyStorage(Paths.get(filePath))
                    .saveMcGymmy(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMcGymmy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMcGymmy(new McGymmy(), null));
    }
}
