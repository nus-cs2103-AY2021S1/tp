package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;

public interface UserAccountsListStorage {

    Path getUserAccountFilepath();

    Optional<Account> login(String username, String password) throws DataConversionException, IllegalLoginException,
            IllegalValueException;

    void createAccount(String username, String password, String doctorName, String doctorMcr) throws
            DataConversionException, IOException, IllegalValueException;

}
