package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.order.OrderManager;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.VendorManager;
import seedu.address.storage.JsonPresetManagerStorage;
import seedu.address.storage.JsonProfileManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonVendorManagerStorage;
import seedu.address.storage.MenuItemStorage;
import seedu.address.storage.PresetManagerStorage;
import seedu.address.storage.ProfileManagerStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.VendorManagerStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing VendorManager ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        VendorManagerStorage vendorManagerStorage = new JsonVendorManagerStorage(userPrefs.getVendorManagerFilePath());
        PresetManagerStorage presetManagerStorage = new JsonPresetManagerStorage(userPrefs.getOrderManagerFilePath());
        ProfileManagerStorage profileManagerStorage = new JsonProfileManagerStorage(
                userPrefs.getProfileManagerFilePath()
        );
        storage = new StorageManager(vendorManagerStorage, userPrefsStorage, presetManagerStorage,
                profileManagerStorage);
        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s vendor book and {@code userPrefs}. <br>
     * The data from the sample vendor book will be used instead if {@code storage}'s vendor book is not found,
     * or an empty vendor book will be used instead if errors occur when reading {@code storage}'s vendor book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyVendorManager> vendorManagerOptional;
        List<Optional<ReadOnlyMenuManager>> menuManagersOptional;
        ReadOnlyVendorManager initialData;
        List<MenuManager> initialMenuManagers = new ArrayList<>();
        OrderManager initialOrderManager = new OrderManager();
        try {
            vendorManagerOptional = storage.readVendorManager();
            if (vendorManagerOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample VendorManager");
            }
            initialData = vendorManagerOptional.orElseGet(SampleDataUtil::getSampleVendorManager);
            menuManagersOptional = new MenuItemStorage().readMenuManagers(initialData.getVendorList());
            menuManagersOptional.forEach(x -> x.ifPresentOrElse(y ->
                    initialMenuManagers.add(new MenuManager(y)), () -> {
                    logger.info("Data file not found. Will be starting with an empty menu");
                    initialMenuManagers.add(new MenuManager());
                }
            ));
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty VendorManager");
            initialData = new VendorManager();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty VendorManager");
            initialData = new VendorManager();
            assert model != null;
        }
        try {
            storage.saveVendorManager(initialData);
        } catch (IOException e) {
            logger.warning("Something unexpected occurred!");
            assert false;
        }
        try {
            if (!FileUtil.isFileExists(storage.getPresetManagerFilePath())) {
                storage.savePresetManager(new ArrayList<>(new ArrayList<>()));
            }
        } catch (IOException e) {
            logger.warning("Something unexpected occurred!");
            assert false;
        }

        return new ModelManager(initialData, userPrefs, initialMenuManagers, initialOrderManager);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty VendorManager");
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
        logger.info("Starting VendorManager " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Supper Strikers ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
