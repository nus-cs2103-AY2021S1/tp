package seedu.taskmaster;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.taskmaster.commons.core.Config;
import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.commons.core.Version;
import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.commons.util.ConfigUtil;
import seedu.taskmaster.commons.util.StringUtil;
import seedu.taskmaster.logic.Logic;
import seedu.taskmaster.logic.LogicManager;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.ReadOnlyUserPrefs;
import seedu.taskmaster.model.Taskmaster;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.session.SessionList;
import seedu.taskmaster.model.session.SessionListManager;
import seedu.taskmaster.model.util.SampleDataUtil;
import seedu.taskmaster.storage.JsonTaskmasterStorage;
import seedu.taskmaster.storage.JsonUserPrefsStorage;
import seedu.taskmaster.storage.Storage;
import seedu.taskmaster.storage.StorageManager;
import seedu.taskmaster.storage.TaskmasterStorage;
import seedu.taskmaster.storage.UserPrefsStorage;
import seedu.taskmaster.ui.Ui;
import seedu.taskmaster.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Taskmaster ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TaskmasterStorage taskmasterStorage = new JsonTaskmasterStorage(userPrefs.getTaskmasterFilePath(),
                userPrefs.getSessionListFilePath());
        storage = new StorageManager(taskmasterStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s student list and {@code userPrefs}. <br>
     * The data from the sample student list will be used instead if {@code storage}'s student list is not found,
     * or an empty student list will be used instead if errors occur when reading {@code storage}'s student list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTaskmaster> taskmasterOptional;
        Optional<SessionList> sessionListOptional;
        ReadOnlyTaskmaster initialData;
        SessionList initialSessionList;
        try {
            taskmasterOptional = storage.readTaskmaster();
            if (!taskmasterOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Taskmaster");
            }
            initialData = taskmasterOptional.orElseGet(SampleDataUtil::getSampleTaskmaster);

            sessionListOptional = storage.readSessionList();
            if (!sessionListOptional.isPresent()) {
                logger.info("Session List file not found.");
            }
            initialSessionList = sessionListOptional.orElseGet(SessionListManager::new);
            initialData.setSessions(initialSessionList.asUnmodifiableObservableList());

        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Taskmaster");
            initialData = new Taskmaster();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Taskmaster");
            initialData = new Taskmaster();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Taskmaster");
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
        logger.info("Starting Taskmaster " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
