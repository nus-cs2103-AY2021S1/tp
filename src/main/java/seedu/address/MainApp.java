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
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ExerciseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.GoalBook;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.TemplateList;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.ExerciseBookStorage;
import seedu.address.storage.GoalBookStorage;
import seedu.address.storage.JsonExerciseBookStorage;
import seedu.address.storage.JsonGoalBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageForGoal;
import seedu.address.storage.StorageManager;
import seedu.address.storage.StorageManagerForGoal;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.ExerciseUiManager;
import seedu.address.ui.Ui;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected StorageForGoal goalStorage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ExerciseBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ExerciseBookStorage exerciseBookStorage = new JsonExerciseBookStorage(userPrefs.getExerciseBookFilePath());
        storage = new StorageManager(exerciseBookStorage, userPrefsStorage);
        GoalBookStorage goalBookStorage = new JsonGoalBookStorage(userPrefs.getGoalBookFilePath());
        goalStorage = new StorageManagerForGoal(goalBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, goalStorage, userPrefs);

        logic = new LogicManager(model, storage, goalStorage);

        ui = new ExerciseUiManager(logic);

        TemplateList.load();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s exercise book and {@code userPrefs}. <br>
     * The data from the sample exercise book will be used instead if {@code storage}'s exercise book is not found,
     * or an empty exercise book will be used instead if errors occur when reading {@code storage}'s exercise book.
     */
    private Model initModelManager(Storage storage,
                                   StorageForGoal goalStorage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyExerciseBook> exerciseBookOptional;
        ReadOnlyExerciseBook initialData;
        try {
            exerciseBookOptional = storage.readExerciseBook();
            if (!exerciseBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ExerciseBook");
                initialData = SampleDataUtil.getSampleExerciseBook();
            } else {
                initialData = exerciseBookOptional.get();
            }
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ExerciseBook");
            initialData = new ExerciseBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ExerciseBook");
            initialData = new ExerciseBook();
        } catch (ParseException e) {
            logger.warning("");
            initialData = new ExerciseBook();
        }

        Optional<ReadOnlyGoalBook> goalBookOptional;
        ReadOnlyGoalBook initialGoalData;
        try {
            goalBookOptional = goalStorage.readGoalBook();
            if (!goalBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample GoalBook");
            }
            initialGoalData = goalBookOptional.orElseGet(SampleDataUtil::getSampleGoalBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty GoalBook");
            initialGoalData = new GoalBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty GoalBook");
            initialGoalData = new GoalBook();
        }

        return new ModelManager(initialData, initialGoalData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ExerciseBook");
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
        logger.info("Starting ExerciseBook " + MainApp.VERSION);
        logger.info("Starting GoalBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Exercise Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }

        logger.info("============================ [ Stopping Goal Book ] =============================");
        try {
            goalStorage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }

}
