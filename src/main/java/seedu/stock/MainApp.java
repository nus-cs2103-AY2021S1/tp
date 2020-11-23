package seedu.stock;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.stock.commons.core.Config;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.core.Version;
import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.commons.util.ConfigUtil;
import seedu.stock.commons.util.StringUtil;
import seedu.stock.logic.Logic;
import seedu.stock.logic.LogicManager;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.ReadOnlyUserPrefs;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.util.SampleDataUtil;
import seedu.stock.storage.JsonSerialNumberSetsBookStorage;
import seedu.stock.storage.JsonStockBookStorage;
import seedu.stock.storage.JsonUserPrefsStorage;
import seedu.stock.storage.SerialNumberSetsBookStorage;
import seedu.stock.storage.StockBookStorage;
import seedu.stock.storage.Storage;
import seedu.stock.storage.StorageManager;
import seedu.stock.storage.UserPrefsStorage;
import seedu.stock.ui.Ui;
import seedu.stock.ui.UiManager;

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
        logger.info("=============================[ Initializing StockBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StockBookStorage stockBookStorage = new JsonStockBookStorage(userPrefs.getStockBookFilePath());
        SerialNumberSetsBookStorage serialNumberSetsBookStorage =
                                new JsonSerialNumberSetsBookStorage(userPrefs.getSerialNumberSetsBookFilePath());
        storage = new StorageManager(stockBookStorage, userPrefsStorage, serialNumberSetsBookStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s stock book and {@code userPrefs}. <br>
     * The data from the sample stock book will be used instead if {@code storage}'s stock book is not found,
     * or an empty stock book will be used instead if errors occur when reading {@code storage}'s stock book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyStockBook> stockBookOptional;
        Optional<ReadOnlySerialNumberSetsBook> serialNumberSetsBookOptional;
        ReadOnlyStockBook initialData;
        ReadOnlySerialNumberSetsBook initialSerialNumberSetsBookData;
        try {
            stockBookOptional = storage.readStockBook();
            serialNumberSetsBookOptional = storage.readSerialNumberSetsBook();
            if (!stockBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample StockBook");
            }
            if (!serialNumberSetsBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample SerialNumberSetsBook");
            }
            initialData = stockBookOptional.orElseGet(SampleDataUtil::getSampleStockBook);
            initialSerialNumberSetsBookData = serialNumberSetsBookOptional
                                                .orElseGet(SampleDataUtil::getSampleSerialNumberSetsBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty StockBook");
            initialData = new StockBook();
            initialSerialNumberSetsBookData = new SerialNumberSetsBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty StockBook");
            initialData = new StockBook();
            initialSerialNumberSetsBookData = new SerialNumberSetsBook();
        }

        return new ModelManager(initialData, userPrefs, initialSerialNumberSetsBookData);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty StockBook");
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
        logger.info("Starting StockBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Stock Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
