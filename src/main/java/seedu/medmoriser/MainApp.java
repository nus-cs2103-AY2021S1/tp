package seedu.medmoriser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.medmoriser.commons.core.Config;
import seedu.medmoriser.commons.core.LogsCenter;
import seedu.medmoriser.commons.core.Version;
import seedu.medmoriser.commons.exceptions.DataConversionException;
import seedu.medmoriser.commons.util.ConfigUtil;
import seedu.medmoriser.commons.util.StringUtil;
import seedu.medmoriser.logic.Logic;
import seedu.medmoriser.logic.LogicManager;
import seedu.medmoriser.model.Medmoriser;
import seedu.medmoriser.model.Model;
import seedu.medmoriser.model.ModelManager;
import seedu.medmoriser.model.ReadOnlyMedmoriser;
import seedu.medmoriser.model.ReadOnlyUserPrefs;
import seedu.medmoriser.model.UserPrefs;
import seedu.medmoriser.model.util.SampleDataUtil;
import seedu.medmoriser.storage.JsonMedmoriserStorage;
import seedu.medmoriser.storage.JsonUserPrefsStorage;
import seedu.medmoriser.storage.MedmoriserStorage;
import seedu.medmoriser.storage.Storage;
import seedu.medmoriser.storage.StorageManager;
import seedu.medmoriser.storage.UserPrefsStorage;
import seedu.medmoriser.ui.Ui;
import seedu.medmoriser.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Medmoriser ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        MedmoriserStorage medmoriserStorage = new JsonMedmoriserStorage(userPrefs.getMedmoriserFilePath());
        storage = new StorageManager(medmoriserStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s medmoriser and {@code userPrefs}. <br>
     * The data from the sample medmoriser will be used instead if {@code storage}'s medmoriser is not found,
     * or an empty medmoriser will be used instead if errors occur when reading {@code storage}'s medmoriser.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyMedmoriser> medmoriserOptional;
        ReadOnlyMedmoriser initialData;
        try {
            medmoriserOptional = storage.readMedmoriser();
            if (!medmoriserOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Medmoriser");
            }
            initialData = medmoriserOptional.orElseGet(SampleDataUtil::getSampleMedmoriser);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Medmoriser");
            initialData = new Medmoriser();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Medmoriser");
            initialData = new Medmoriser();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Medmoriser");
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
        logger.info("Starting Medmoriser " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Medmoriser ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
