package com.eva.storage;

import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.ALICE;
import static com.eva.testutil.TypicalPersons.HOON;
import static com.eva.testutil.TypicalPersons.IDA;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.eva.commons.exceptions.DataConversionException;
import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;

public class JsonEvaStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEvaStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEvaDatabase(null));
    }

    private java.util.Optional<ReadOnlyEvaDatabase<Person>> readEvaDatabase(String filePath) throws Exception {
        return new JsonEvaStorage(Paths.get(filePath))
                .readPersonDatabase(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEvaDatabase("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEvaDatabase("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEvaDatabase("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEvaDatabase("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        EvaDatabase<Person> original = getTypicalPersonDatabase();
        JsonEvaStorage jsonEvaStorage = new JsonEvaStorage(filePath);

        // Save in new file and read back
        jsonEvaStorage.savePersonDatabase(original, filePath);
        ReadOnlyEvaDatabase<Person> readBack = jsonEvaStorage.readPersonDatabase(filePath).get();
        assertEquals(original, new EvaDatabase<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonEvaStorage.savePersonDatabase(original, filePath);
        readBack = jsonEvaStorage.readPersonDatabase(filePath).get();
        assertEquals(original, new EvaDatabase<>(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonEvaStorage.savePersonDatabase(original); // file path not specified
        readBack = jsonEvaStorage.readPersonDatabase().get(); // file path not specified
        assertEquals(original, new EvaDatabase<>(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyEvaDatabase<Person> addressBook, String filePath) {
        try {
            new JsonEvaStorage(Paths.get(filePath))
                    .savePersonDatabase(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new EvaDatabase<>(), null));
    }
}
