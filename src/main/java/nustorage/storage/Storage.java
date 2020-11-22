package nustorage.storage;


import java.io.IOException;
import java.util.Optional;

import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.ReadOnlyUserPrefs;
import nustorage.model.UserPrefs;


/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, FinanceAccountStorage, InventoryStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;


    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;


    @Override
    Optional<ReadOnlyFinanceAccount> readFinanceAccount() throws DataConversionException, IOException;


    @Override
    void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount) throws IOException;


    @Override
    Optional<ReadOnlyInventory> readInventory() throws DataConversionException, IOException;


    @Override
    void saveInventory(ReadOnlyInventory inventory) throws IOException;

}
