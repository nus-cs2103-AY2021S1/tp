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
import nustorage.model.FinanceAccount;


public class JsonFinanceAccountStorage implements FinanceAccountStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFinanceAccountStorage.class);

    private Path filePath;


    public JsonFinanceAccountStorage(Path filePath) {
        this.filePath = filePath;
    }


    @Override
    public Path getFinanceAccountFilePath() {
        return this.filePath;
    }


    @Override
    public Optional<FinanceAccount> readFinanceAccount() throws DataConversionException {
        return readFinanceAccount(filePath);
    }


    @Override
    public Optional<FinanceAccount> readFinanceAccount(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFinanceAccount> jsonFinanceAccount = JsonUtil.readJsonFile(
                filePath, JsonSerializableFinanceAccount.class);

        if (jsonFinanceAccount.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFinanceAccount.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + " " + e.getMessage());
            throw new DataConversionException(e);
        }
    }


    @Override
    public void saveFinanceAccount(FinanceAccount financeAccount) throws IOException {
        saveFinanceAccount(financeAccount, filePath);
    }


    @Override
    public void saveFinanceAccount(FinanceAccount financeAccount, Path filepath) throws IOException {
        requireAllNonNull(financeAccount, filepath);

        FileUtil.createIfMissing(filepath);

        JsonUtil.saveJsonFile(new JsonSerializableFinanceAccount(financeAccount), filePath);
    }

}
