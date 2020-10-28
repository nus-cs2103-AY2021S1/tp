package nustorage.storage;


import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import nustorage.commons.core.LogsCenter;
import nustorage.commons.exceptions.DataConversionException;
import nustorage.commons.exceptions.IllegalValueException;
import nustorage.commons.util.FileUtil;
import nustorage.commons.util.JsonUtil;
import nustorage.model.ReadOnlyFinanceAccount;


/**
 * A class to access FinanceAccount data stored as a json file on the hard disk.
 */
public class JsonFinanceAccountStorage implements FinanceAccountStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFinanceAccountStorage.class);

    private Path filePath;


    /**
     * Creates a new finance account storage object.
     *
     * @param filePath file path to storage file.
     */
    public JsonFinanceAccountStorage(Path filePath) {
        assert filePath != null : "File path for finance account storage is null!";

        this.filePath = filePath;
    }


    @Override
    public Path getFinanceAccountFilePath() {
        return this.filePath;
    }


    @Override
    public Optional<ReadOnlyFinanceAccount> readFinanceAccount() throws DataConversionException {
        return readFinanceAccount(filePath);
    }


    @Override
    public Optional<ReadOnlyFinanceAccount> readFinanceAccount(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFinanceAccount> jsonFinanceAccount = JsonUtil.readJsonFile(
                filePath, JsonSerializableFinanceAccount.class);

        if (jsonFinanceAccount.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFinanceAccount.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }
    }


    @Override
    public void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount) throws IOException {
        // assert financeAccount != null : "Finance account is null!";
        saveFinanceAccount(financeAccount, filePath);
    }


    @Override
    public void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount, Path filePath) throws IOException {
        requireAllNonNull(financeAccount, filePath);

        FileUtil.createIfMissing(filePath);

        JsonUtil.saveJsonFile(new JsonSerializableFinanceAccount(financeAccount), filePath);
    }

}
