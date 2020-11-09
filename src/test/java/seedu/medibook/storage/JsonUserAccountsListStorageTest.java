package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

public class JsonUserAccountsListStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonUserAccountsListStorageTest");

    private static final Path VALID_DIRECTORY = TEST_DATA_FOLDER.resolve("accounts.json");
    private static final Path INVALID_DIRECTORY = TEST_DATA_FOLDER.resolve("invalid.json");
    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "password";
    private static final String VALID_DOCTORNAME = "Doctor Name";
    private static final String VALID_DOCTORMCR = "M12345Q";
    private static final String INVALID_USERNAME = "a";
    private static final String INVALID_PASSWORD = "b";
    private static final String INVALID_DOCTORNAME = "Doctor N@me";
    private static final String INVALID_DOCTORMCR = "M1234QQ";

    @TempDir
    public Path testFolder;

    @Test
    public void login_invalidInfo_throwsIllegalLoginException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalLoginException.class, () -> storage.login("invaliduser", "invalidpass"));
    }

    @Test
    public void login_invalidFilepath_throwsIllegalLoginException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(INVALID_DIRECTORY);
        assertThrows(IllegalLoginException.class, () -> storage.login("user1", "password1"));
    }

    @Test
    public void login_validInfo_success() throws IllegalValueException, DataConversionException, IllegalLoginException {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        Optional<Account> expectedResult = Optional.of(new Account("user1", "password1",
                new Doctor(new Name("Doctor"), new Mcr("M12345Q"))));
        Optional<Account> result = storage.login("user1", "password1");
        assertEquals(result, expectedResult);
    }

    @Test
    public void createAccount_invalidUsernameInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(INVALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, VALID_DOCTORMCR));
    }

    @Test
    public void createAccount_invalidPasswordInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(VALID_USERNAME, INVALID_PASSWORD,
                VALID_DOCTORNAME, VALID_DOCTORMCR));
    }

    @Test
    public void createAccount_invalidNameInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                INVALID_DOCTORNAME, VALID_DOCTORMCR));
    }

    @Test
    public void createAccount_invalidMcrInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, INVALID_DOCTORMCR));
    }

    @Test
    public void createAccount_nullNameInput_throwsNullPointerException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(NullPointerException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                null, VALID_DOCTORMCR));
    }

    @Test
    public void createAccount_invalidNameInput_throwsNullPointerException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(NullPointerException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, null));
    }

    @Test
    public void createAccount_validUsernameAlreadyExistsInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, VALID_DOCTORMCR));
    }

    @Test
    public void createAccount_validMcrAlreadyExistsInput_throwsIllegalValueException() {
        JsonUserAccountsListStorage storage = new JsonUserAccountsListStorage(VALID_DIRECTORY);
        assertThrows(IllegalValueException.class, () -> storage.createAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, VALID_DOCTORMCR));
    }

}
