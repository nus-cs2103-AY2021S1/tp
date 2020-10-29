package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ItemList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.ItemListStorage;
import seedu.address.storage.JsonItemListStorage;
import seedu.address.storage.JsonLocationListStorage;
import seedu.address.storage.JsonRecipeListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.LocationListStorage;
import seedu.address.storage.RecipeListStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.InventoryUiManager;
import seedu.address.ui.Ui;


/**
 * Runs the application.
 */
public class InventoryMainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);
    private static final Logger logger = LogsCenter.getLogger(InventoryMainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Inventory ]=============================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ItemListStorage itemListStorage = new JsonItemListStorage(userPrefs.getItemListFilePath());
        LocationListStorage locationListStorage = new JsonLocationListStorage(userPrefs.getLocationListFilePath());
        RecipeListStorage recipeListStorage = new JsonRecipeListStorage(userPrefs.getRecipeListFilePath());

        storage = new StorageManager(itemListStorage, locationListStorage, recipeListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new InventoryUiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s item list and {@code userPrefs}. <br>
     * The data from the sample item list will be used instead if {@code storage}'s item list is not found,
     * or an empty item list will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs)
            throws IOException, DataConversionException {
        ReadOnlyItemList initialItemList = initItemList(storage);
        ReadOnlyLocationList initialLocationList = initLocationList(storage);
        ReadOnlyRecipeList initialRecipeList = initRecipeList(storage);
        return new ModelManager(initialItemList, initialLocationList, initialRecipeList, userPrefs);
    }

    private ReadOnlyItemList initItemList(Storage storage) {
        Optional<ReadOnlyItemList> itemListOptional;
        ReadOnlyItemList initialItemList;
        try {
            itemListOptional = storage.readItemList();
            if (itemListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample ItemList");
            }
            initialItemList = itemListOptional.orElseGet(SampleDataUtil::getSampleItemList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ItemList");
            initialItemList = new ItemList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ItemList");
            initialItemList = new ItemList();
        }
        return initialItemList;
    }

    private ReadOnlyLocationList initLocationList(Storage storage) throws IOException, DataConversionException {
        Optional<ReadOnlyLocationList> locationListOptional;
        ReadOnlyLocationList initialLocationList;
        locationListOptional = storage.readLocationList();
        if (locationListOptional.isEmpty()) {
            logger.info("Data file not found. Will be starting with a sample LocationList");
        }
        initialLocationList = locationListOptional.orElseGet(SampleDataUtil::getSampleLocationList);
        return initialLocationList;
    }

    private ReadOnlyRecipeList initRecipeList(Storage storage) {
        Optional<ReadOnlyRecipeList> recipeListOptional;
        ReadOnlyRecipeList initialRecipeList;
        try {
            recipeListOptional = storage.readRecipeList();
            if (recipeListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample RecipeList");
            }
            initialRecipeList = recipeListOptional.orElseGet(SampleDataUtil::getSampleRecipeList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty RecipeList");
            initialRecipeList = new RecipeList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty RecipeList");
            initialRecipeList = new RecipeList();
        }
        return initialRecipeList;
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
            logger.warning("Problem while reading from the file. Will be starting with empty user prefs");
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
        logger.info("Starting ItemList " + InventoryMainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Inventoryinator ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
