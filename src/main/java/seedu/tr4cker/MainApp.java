package seedu.tr4cker;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.tr4cker.commons.core.Config;
import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.commons.core.Version;
import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.commons.util.ConfigUtil;
import seedu.tr4cker.commons.util.StringUtil;
import seedu.tr4cker.logic.Logic;
import seedu.tr4cker.logic.LogicManager;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.ReadOnlyUserPrefs;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.util.SampleDataUtil;
import seedu.tr4cker.storage.JsonTr4cker;
import seedu.tr4cker.storage.JsonUserPrefsStorage;
import seedu.tr4cker.storage.Storage;
import seedu.tr4cker.storage.StorageManager;
import seedu.tr4cker.storage.UserPrefsStorage;
import seedu.tr4cker.ui.Ui;
import seedu.tr4cker.ui.UiManager;

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
        logger.info("=============================[ Initializing Tr4cker ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        seedu.tr4cker.storage.Tr4cker tr4cker = new JsonTr4cker(userPrefs.getTr4ckerFilePath());
        storage = new StorageManager(tr4cker, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Tr4cker and {@code userPrefs}. <br>
     * The data from the sample Tr4cker will be used instead if {@code storage}'s Tr4cker is not found,
     * or an empty Tr4cker will be used instead if errors occur when reading {@code storage}'s Tr4cker.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTr4cker> tr4ckerOptional;
        ReadOnlyTr4cker initialData;
        try {
            tr4ckerOptional = storage.readTr4cker();
            if (!tr4ckerOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Tr4cker");
            }
            initialData = tr4ckerOptional.orElseGet(SampleDataUtil::getSampleTr4cker);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Tr4cker");
            initialData = new seedu.tr4cker.model.Tr4cker();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Tr4cker");
            initialData = new seedu.tr4cker.model.Tr4cker();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Tr4cker");
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
        logger.info("Starting Tr4cker " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping TR4CKER ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
