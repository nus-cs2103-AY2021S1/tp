package seedu.address.storage.contactListStorage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ContactList;
import seedu.address.model.ModuleList;
import seedu.address.storage.JsonSerializableContactList;
import seedu.address.testutil.TypicalContacts;
import seedu.address.testutil.TypicalModules;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class JsonSerializableContactListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableModuleListTest");
    private static final Path TYPICAL_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("typicalModuleModuleList.json");
    private static final Path INVALID_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("invalidContactContactList.json");
    private static final Path DUPLICATE_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleModuleList.json");

    @Test
    public void toModelType_typicalContactsFile_success() throws Exception {

        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTACTS_FILE,
                JsonSerializableContactList.class).get();
        ContactList contactListFromFile = dataFromFile.toModelType();
        ContactList typicalContactContactList = TypicalContacts.getTypicalContactList();
        assertEquals(contactListFromFile, typicalContactContactList);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACTS_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContact_throwsIllegalValueException() throws Exception {
        JsonSerializableContactList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACTS_FILE,
                JsonSerializableContactList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableContactList.MESSAGE_DUPLICATE_CONTACT,
                 dataFromFile::toModelType);
    }

}
