package com.eva.storage;

import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.JsonUtil;
import com.eva.model.EvaDatabase;

public class JsonPersonDatabaseTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonPersonDatabase dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonPersonDatabase.class).get();
        EvaDatabase addressBookFromFile = dataFromFile.toModelType();
        EvaDatabase typicalPersonsAddressBook = getTypicalPersonDatabase();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonPersonDatabase dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonPersonDatabase.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonPersonDatabase dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonPersonDatabase.class).get();
        assertThrows(IllegalValueException.class, JsonPersonDatabase.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
