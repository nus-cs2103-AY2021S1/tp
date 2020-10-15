package nustorage.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.FinanceAccount;
import nustorage.model.ReadOnlyFinanceAccount;


/**
 * A storage interface for {@link nustorage.model.FinanceAccount}
 */
public interface FinanceAccountStorage {

    /**
     * @return file path to json data file.
     */
    Path getFinanceAccountFilePath();


    /**
     * Read finance account from json storage file.
     *
     * @return FinanceAccount data, or {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFinanceAccount> readFinanceAccount() throws DataConversionException, IOException;


    /**
     * @param filePath overrides default storage filepath.
     * @see #readFinanceAccount()
     */
    Optional<ReadOnlyFinanceAccount> readFinanceAccount(Path filePath) throws DataConversionException, IOException;


    /**
     * Saves the given {@link FinanceAccount} to storage in Json format
     *
     * @param financeAccount given finance account to be stored, cannot be null.
     * @throws IOException if there's any problem writing to file.
     */
    void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount) throws IOException;


    /**
     * @param filePath overrides default storage filepath.
     * @see #saveFinanceAccount(ReadOnlyFinanceAccount)
     */
    void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount, Path filePath) throws IOException;

}
