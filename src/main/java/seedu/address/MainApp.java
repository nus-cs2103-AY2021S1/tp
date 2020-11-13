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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Nusave;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonNusaveStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.NusaveStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private static final String GREETING_MESSAGE = "Welcome to NUSave!";
    private static final String DATA_LOADED_SUCCESS_MESSAGE = "Successfully loaded existing data.\n";
    private static final String DATA_NOT_FOUND_ERROR_MESSAGE = "Data file not found.\n"
            + "Will be starting with sample data.\n";
    private static final String WRONG_DATA_FORMAT_ERROR_MESSAGE = "Data file not in the correct format.\n"
            + "Will be starting with an empty NUSave.\n";
    private static final String UNABLE_TO_READ_DATA_ERROR_MESSAGE = "Error reading from data file.\n"
            + "Will be starting with an empty NUSave.\n";

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    private String initialMessage = DATA_LOADED_SUCCESS_MESSAGE
            + GREETING_MESSAGE;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ NUSave ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        NusaveStorage nusaveStorage = new JsonNusaveStorage(userPrefs.getNusaveFilePath());
        storage = new StorageManager(nusaveStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, initialMessage);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s NUSave and {@code userPrefs}. <br>
     * The data from the sample NUSave will be used instead if {@code storage}'s NUSave is not found,
     * or an empty NUSave will be used instead if errors occur when reading {@code storage}'s NUSave.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyNusave> nusaveOptional;
        ReadOnlyNusave initialData;
        try {
            nusaveOptional = storage.readNusave();
            if (nusaveOptional.isEmpty()) {
                logger.info(DATA_NOT_FOUND_ERROR_MESSAGE);
                initialMessage = DATA_NOT_FOUND_ERROR_MESSAGE + GREETING_MESSAGE;
            }
            initialData = nusaveOptional.orElseGet(SampleDataUtil::getSampleNusave);
        } catch (DataConversionException e) {
            logger.warning(WRONG_DATA_FORMAT_ERROR_MESSAGE);
            initialData = new Nusave();
            initialMessage = WRONG_DATA_FORMAT_ERROR_MESSAGE + GREETING_MESSAGE;
        } catch (IOException e) {
            logger.warning(UNABLE_TO_READ_DATA_ERROR_MESSAGE);
            initialData = new Nusave();
            initialMessage = UNABLE_TO_READ_DATA_ERROR_MESSAGE + GREETING_MESSAGE;
        }

        return new ModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty NUSave");
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
        logger.info("Starting NUSave " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping NUSave ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
