package seedu.pivot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.pivot.commons.core.Config;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.Version;
import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.commons.util.ConfigUtil;
import seedu.pivot.commons.util.StringUtil;
import seedu.pivot.logic.Logic;
import seedu.pivot.logic.LogicManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.ReadOnlyUserPrefs;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.util.SampleDataUtil;
import seedu.pivot.storage.JsonPivotStorage;
import seedu.pivot.storage.JsonUserPrefsStorage;
import seedu.pivot.storage.PivotStorage;
import seedu.pivot.storage.ReferenceStorage;
import seedu.pivot.storage.Storage;
import seedu.pivot.storage.StorageManager;
import seedu.pivot.storage.UserPrefsStorage;
import seedu.pivot.ui.Ui;
import seedu.pivot.ui.UiManager;

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
        logger.info("=============================[ Initializing PIVOT ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PivotStorage pivotStorage = new JsonPivotStorage(userPrefs.getPivotFilePath());
        ReferenceStorage referenceStorage = new ReferenceStorage();
        storage = new StorageManager(pivotStorage, userPrefsStorage, referenceStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s PIVOT and {@code userPrefs}. <br>
     * The data from the sample PIVOT will be used instead if {@code storage}'s PIVOT is not found,
     * or an empty PIVOT will be used instead if errors occur when reading {@code storage}'s PIVOT.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPivot> pivotOptional;
        ReadOnlyPivot initialData;
        try {
            pivotOptional = storage.readPivot();
            if (!pivotOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PIVOT");
                storage.addReferenceTestFile(); //includes test1.txt as sample data
            }
            initialData = pivotOptional.orElseGet(SampleDataUtil::getSamplePivot);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PIVOT");
            initialData = new Pivot();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PIVOT");
            initialData = new Pivot();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PIVOT");
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
        logger.info("Starting PIVOT " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping PIVOT ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
