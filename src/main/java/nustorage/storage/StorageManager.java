package nustorage.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import nustorage.commons.core.LogsCenter;
import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.ReadOnlyUserPrefs;
import nustorage.model.UserPrefs;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private FinanceAccountStorage financeAccountStorage;
    private InventoryStorage inventoryStorage;


    // /**
    //  * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
    //  */
    // public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
    //     super();
    //     this.addressBookStorage = addressBookStorage;
    //     this.userPrefsStorage = userPrefsStorage;
    // }


    /**
     * Creates a {@code StorageManager} with the given
     * {@code FinanceAccountStorage}, {@code InventoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FinanceAccountStorage financeAccountStorage, InventoryStorage inventoryStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.financeAccountStorage = financeAccountStorage;
        this.inventoryStorage = inventoryStorage;
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

    // ================ FinanceAccount methods ==============================


    @Override
    public Path getFinanceAccountFilePath() {
        return financeAccountStorage.getFinanceAccountFilePath();
    }


    @Override
    public Optional<ReadOnlyFinanceAccount> readFinanceAccount() throws DataConversionException, IOException {
        return readFinanceAccount(financeAccountStorage.getFinanceAccountFilePath());
    }


    @Override
    public Optional<ReadOnlyFinanceAccount> readFinanceAccount(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read finance account from data file: " + filePath);
        return financeAccountStorage.readFinanceAccount(filePath);
    }


    @Override
    public void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount) throws IOException {
        saveFinanceAccount(financeAccount, financeAccountStorage.getFinanceAccountFilePath());
    }


    @Override
    public void saveFinanceAccount(ReadOnlyFinanceAccount financeAccount, Path filePath) throws IOException {
        logger.fine("Attempting to write finance account to data file: " + filePath);
        financeAccountStorage.saveFinanceAccount(financeAccount, filePath);
    }


    // ================ InventoryWindow methods ==============================


    @Override
    public Path getInventoryFilePath() {
        return inventoryStorage.getInventoryFilePath();
    }


    @Override
    public Optional<ReadOnlyInventory> readInventory() throws DataConversionException, IOException {
        return readInventory(inventoryStorage.getInventoryFilePath());
    }


    @Override
    public Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read finance account from data file: " + filePath);
        return inventoryStorage.readInventory(filePath);
    }


    @Override
    public void saveInventory(ReadOnlyInventory inventory) throws IOException {
        saveInventory(inventory, inventoryStorage.getInventoryFilePath());
    }


    @Override
    public void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException {
        logger.fine("Attempting to write finance account to data file: " + filePath);
        inventoryStorage.saveInventory(inventory, filePath);
    }

}
