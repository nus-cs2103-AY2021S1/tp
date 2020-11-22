package nustorage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import nustorage.commons.core.Config;
import nustorage.commons.core.LogsCenter;
import nustorage.commons.core.Version;
import nustorage.commons.exceptions.DataConversionException;
import nustorage.commons.util.ConfigUtil;
import nustorage.commons.util.StringUtil;
import nustorage.logic.Logic;
import nustorage.logic.LogicManager;
import nustorage.model.FinanceAccount;
import nustorage.model.Inventory;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.ReadOnlyUserPrefs;
import nustorage.model.UserPrefs;
import nustorage.storage.FinanceAccountStorage;
import nustorage.storage.InventoryStorage;
import nustorage.storage.JsonFinanceAccountStorage;
import nustorage.storage.JsonInventoryStorage;
import nustorage.storage.JsonUserPrefsStorage;
import nustorage.storage.Storage;
import nustorage.storage.StorageManager;
import nustorage.storage.UserPrefsStorage;
import nustorage.ui.Ui;
import nustorage.ui.UiManager;


/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;


    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing NUStorage ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        FinanceAccountStorage financeAccountStorage =
                new JsonFinanceAccountStorage(userPrefs.getFinanceAccountFilePath());
        InventoryStorage inventoryStorage = new JsonInventoryStorage(userPrefs.getInventoryFilePath());

        storage = new StorageManager(financeAccountStorage, inventoryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }


    /**
     * Returns a {@code ModelManager} with the data from {@code storage} and {@code userPrefs}. <br>
     * or an empty model will be used instead if errors occur when reading {@code storage}.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {

        Optional<ReadOnlyFinanceAccount> optionalFinanceAccount;
        ReadOnlyFinanceAccount initialFinanceAccount;

        Optional<ReadOnlyInventory> optionalInventory;
        ReadOnlyInventory initialInventory;

        try {
            optionalFinanceAccount = storage.readFinanceAccount();
            if (optionalFinanceAccount.isEmpty()) {
                logger.info("Data file not found. Will be starting with an empty finance account!");
            }
            initialFinanceAccount = optionalFinanceAccount.orElseGet(FinanceAccount::new);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty finance account!");
            initialFinanceAccount = new FinanceAccount();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty finance account!");
            initialFinanceAccount = new FinanceAccount();
        }

        try {
            optionalInventory = storage.readInventory();
            if (optionalInventory.isEmpty()) {
                logger.info("Data file not found. Will be starting with an empty inventory!");
            }
            initialInventory = optionalInventory.orElseGet(Inventory::new);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty inventory!");
            initialInventory = new Inventory();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty inventory!");
            initialInventory = new Inventory();
        }

        return new ModelManager(initialFinanceAccount, initialInventory, userPrefs);

    }


    private void initLogging(Config config) {
        LogsCenter.init(config);
    }


    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }


    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty data");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }


    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting NUStorage " + MainApp.VERSION);
        ui.start(primaryStage);
    }


    @Override
    public void stop() {
        logger.info("============================ [ Stopping NUStorage ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }

}
