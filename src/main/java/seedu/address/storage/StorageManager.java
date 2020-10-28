package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyClientList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.policy.PolicyList;

/**
 * Manages storage of ClientList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClientListStorage clientListStorage;
    private UserPrefsStorage userPrefsStorage;
    private PolicyListStorage policyListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClientListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClientListStorage clientListStorage,
                          UserPrefsStorage userPrefsStorage,
                          PolicyListStorage policyListStorage) {
        super();
        this.clientListStorage = clientListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.policyListStorage = policyListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ClientList methods ==============================

    @Override
    public Path getClientListFilePath() {
        return clientListStorage.getClientListFilePath();
    }

    @Override
    public Optional<ReadOnlyClientList> readClientList() throws DataConversionException, IOException {
        return readClientList(clientListStorage.getClientListFilePath());
    }

    @Override
    public Optional<ReadOnlyClientList> readClientList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clientListStorage.readClientList(filePath);
    }

    @Override
    public void saveClientList(ReadOnlyClientList clientList) throws IOException {
        saveClientList(clientList, clientListStorage.getClientListFilePath());
    }

    @Override
    public void saveClientList(ReadOnlyClientList clientList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clientListStorage.saveClientList(clientList, filePath);
    }

    // ================ PolicyList methods ==============================

    @Override
    public Path getPolicyListFilePath() {
        return policyListStorage.getPolicyListFilePath();
    }

    @Override
    public Optional<PolicyList> readPolicyList() throws DataConversionException, IOException {
        return policyListStorage.readPolicyList();
    }

    @Override
    public Optional<PolicyList> readPolicyList(Path filePath) throws DataConversionException, IOException {
        return policyListStorage.readPolicyList(filePath);
    }

    @Override
    public void savePolicyList(PolicyList policyList) throws IOException {
        policyListStorage.savePolicyList(policyList);
    }

    @Override
    public void savePolicyList(PolicyList policyList, Path filePath) throws IOException {
        policyListStorage.savePolicyList(policyList, filePath);
    }

}
