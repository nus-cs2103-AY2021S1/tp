package tp.cap5buddy.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import tp.cap5buddy.modules.ModuleList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private JsonModuleListStorage jsonModuleListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(JsonModuleListStorage jsonModuleListStorage) {
        super();
        this.jsonModuleListStorage = jsonModuleListStorage;
    }

    public void saveModules(ModuleList moduleList) throws IOException {
        saveModules(moduleList, jsonModuleListStorage.getModuleListFilePath());
    }

    public void saveModules(ModuleList moduleList, Path filePath) throws IOException {
        jsonModuleListStorage.saveModuleList(moduleList, filePath);
    }

}
