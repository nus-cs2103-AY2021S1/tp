package seedu.zookeep;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.zookeep.commons.core.Config;
import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.commons.core.Version;
import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.commons.util.ConfigUtil;
import seedu.zookeep.commons.util.StringUtil;
import seedu.zookeep.logic.Logic;
import seedu.zookeep.logic.LogicManager;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.ReadOnlyUserPrefs;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.util.SampleDataUtil;
import seedu.zookeep.storage.JsonUserPrefsStorage;
import seedu.zookeep.storage.JsonZooKeepBookStorage;
import seedu.zookeep.storage.Storage;
import seedu.zookeep.storage.StorageManager;
import seedu.zookeep.storage.UserPrefsStorage;
import seedu.zookeep.storage.ZooKeepBookStorage;
import seedu.zookeep.ui.Ui;
import seedu.zookeep.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ZooKeepBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(userPrefs.getZooKeepBookFilePath());
        storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s ZooKeepBook and {@code userPrefs}. <br>
     * The data from the sample ZooKeepBook will be used instead if {@code storage}'s ZooKeepBook is not found,
     * or an empty ZooKeepBook will be used instead if errors occur when reading {@code storage}'s ZooKeepBook.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyZooKeepBook> zooKeepBookOptional;
        ReadOnlyZooKeepBook initialData;
        try {
            zooKeepBookOptional = storage.readZooKeepBook();
            if (!zooKeepBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ZooKeepBook");
            }
            initialData = zooKeepBookOptional.orElseGet(SampleDataUtil::getSampleZooKeepBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ZooKeepBook");
            initialData = new ZooKeepBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ZooKeepBook");
            initialData = new ZooKeepBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ZooKeepBook");
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
        logger.info("Starting ZooKeepBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ZooKeepBook ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
