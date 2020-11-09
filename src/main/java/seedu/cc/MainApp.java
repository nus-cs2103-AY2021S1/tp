package seedu.cc;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.cc.commons.core.Config;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.commons.core.Version;
import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.commons.util.ConfigUtil;
import seedu.cc.commons.util.StringUtil;
import seedu.cc.logic.Logic;
import seedu.cc.logic.LogicManager;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.util.SampleCommonCentsUtilData;
import seedu.cc.storage.CommonCentsStorage;
import seedu.cc.storage.JsonCommonCentsStorage;
import seedu.cc.storage.JsonUserPrefsStorage;
import seedu.cc.storage.Storage;
import seedu.cc.storage.StorageManager;
import seedu.cc.storage.UserPrefsStorage;
import seedu.cc.ui.Ui;
import seedu.cc.ui.UiManager;

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
        logger.info("=============================[ Initializing CommonCents ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        CommonCentsStorage commonCentsStorage = new JsonCommonCentsStorage(userPrefs.getCommonCentsFilePath());
        storage = new StorageManager(commonCentsStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        boolean isNotEmpty = !model.hasNoAccount();
        assert isNotEmpty;

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Common Cents and {@code userPrefs}. <br>
     * The data from the sample Common Cents will be used instead if {@code storage}'s Common Cents is not found,
     * or an empty Common Cents will be used instead if errors occur when reading {@code storage}'s Common Cents.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyCommonCents> commonCentsOptional;
        ReadOnlyCommonCents initialData;
        try {
            commonCentsOptional = storage.readCommonCents();
            if (commonCentsOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample CommonCents");
            }
            initialData = commonCentsOptional.orElseGet(SampleCommonCentsUtilData::getSampleCommonCents);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty CommonCents");
            initialData = SampleCommonCentsUtilData.initEmptyCommonCents();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty CommonCents");
            initialData = SampleCommonCentsUtilData.initEmptyCommonCents();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty CommonCents");
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
        primaryStage.setResizable(false);
        logger.info("Starting CommonCents " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping CommonCents ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
