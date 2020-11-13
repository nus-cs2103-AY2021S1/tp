package seedu.bagel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.bagel.commons.core.Config;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.commons.core.Version;
import seedu.bagel.commons.exceptions.DataConversionException;
import seedu.bagel.commons.util.ConfigUtil;
import seedu.bagel.commons.util.StringUtil;
import seedu.bagel.logic.Logic;
import seedu.bagel.logic.LogicManager;
import seedu.bagel.model.Bagel;
import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.ReadOnlyUserPrefs;
import seedu.bagel.model.UserPrefs;
import seedu.bagel.model.util.SampleDataUtil;
import seedu.bagel.storage.BagelStorage;
import seedu.bagel.storage.JsonBagelStorage;
import seedu.bagel.storage.JsonUserPrefsStorage;
import seedu.bagel.storage.Storage;
import seedu.bagel.storage.StorageManager;
import seedu.bagel.storage.UserPrefsStorage;
import seedu.bagel.ui.Ui;
import seedu.bagel.ui.UiManager;

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
        logger.info("=============================[ Initializing Bagel ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        BagelStorage bagelStorage = new JsonBagelStorage(userPrefs.getBagelFilePath());
        storage = new StorageManager(bagelStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, getHostServices());
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Bagel and {@code userPrefs}. <br>
     * The data from the sample Bagel will be used instead if {@code storage}'s Bagel is not found,
     * or an empty Bagel will be used instead if errors occur when reading {@code storage}'s Bagel.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyBagel> bagelOptional;
        ReadOnlyBagel initialData;
        try {
            bagelOptional = storage.readBagel();
            if (!bagelOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Bagel");
            }
            initialData = bagelOptional.orElseGet(SampleDataUtil::getSampleBagel);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Bagel");
            initialData = new Bagel();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Bagel");
            initialData = new Bagel();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Bagel");
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
        logger.info("Starting Bagel " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Bagel ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
