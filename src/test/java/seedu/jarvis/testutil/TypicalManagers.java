package seedu.jarvis.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.UserPrefs;
import seedu.jarvis.model.login.Username;
import seedu.jarvis.storage.JsonAddressBookStorage;
import seedu.jarvis.storage.JsonUserLoginStorage;
import seedu.jarvis.storage.JsonUserPrefsStorage;
import seedu.jarvis.storage.Storage;
import seedu.jarvis.storage.StorageManager;

public class TypicalManagers {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    public static UserLogin getUserLogin() {
        return new UserLogin();
    }

    public static UserLogin getPopUserLogin(Username username, String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        return userLogin;
    }

    public static UserPrefs getUserPrefs() {
        return new UserPrefs();
    }

    public static JsonAddressBookStorage getJsonAddressBookStorage() {
        return new JsonAddressBookStorage(Paths.get("typicalPersonsAddressBook.json"));
    }

    public static JsonUserPrefsStorage getJsonUserPrefsStorage() {
        return new JsonUserPrefsStorage(Paths.get("TypicalUserPref.json"));
    }

    public static JsonUserLoginStorage getJsonUserLoginStorage() {
        return new JsonUserLoginStorage(Paths.get("EmptyUserLogin.json"));
    }

    public static Storage getStorage() {
        return new StorageManager(getJsonAddressBookStorage(), getJsonUserPrefsStorage(), getJsonUserLoginStorage());
    }

    public static Model getModel() {
        return new ModelManager(TypicalStudents.getTypicalAddressBook(), getUserPrefs(), getUserLogin());
    }
}
