package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyClientList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.policy.PolicyList;

/**
 * API of the Storage component
 */
public interface Storage extends ClientListStorage, UserPrefsStorage, PolicyListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClientListFilePath();

    @Override
    Optional<ReadOnlyClientList> readClientList() throws DataConversionException, IOException;

    @Override
    void saveClientList(ReadOnlyClientList clientList) throws IOException;

    @Override
    Path getPolicyListFilePath();

    @Override
    Optional<PolicyList> readPolicyList() throws DataConversionException, IOException;

    @Override
    void savePolicyList(PolicyList policyList) throws IOException;
}
