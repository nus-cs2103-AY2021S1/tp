package seedu.medibook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.medibook.commons.core.Config;
import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.core.Version;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.util.ConfigUtil;
import seedu.medibook.commons.util.StringUtil;
import seedu.medibook.logic.Logic;
import seedu.medibook.logic.LogicManager;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyUserPrefs;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.util.SampleDataUtil;
import seedu.medibook.storage.JsonMediBookStorage;
import seedu.medibook.storage.JsonMedicalNoteListStorage;
import seedu.medibook.storage.JsonUserAccountsListStorage;
import seedu.medibook.storage.JsonUserPrefsStorage;
import seedu.medibook.storage.MediBookStorage;
import seedu.medibook.storage.MedicalNoteListStorage;
import seedu.medibook.storage.Storage;
import seedu.medibook.storage.StorageManager;
import seedu.medibook.storage.UserAccountsListStorage;
import seedu.medibook.storage.UserPrefsStorage;
import seedu.medibook.ui.Ui;
import seedu.medibook.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing MediBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        MediBookStorage mediBookStorage = new JsonMediBookStorage(userPrefs.getMediBookFilePath());
        MedicalNoteListStorage medicalNoteListStorage =
                new JsonMedicalNoteListStorage(userPrefs.getMedicalNotesDirPath());
        UserAccountsListStorage userAccountsListStorage =
                new JsonUserAccountsListStorage(userPrefs.getUserAccountPath());
        storage =
                new StorageManager(mediBookStorage, userPrefsStorage, medicalNoteListStorage, userAccountsListStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s medi book and {@code userPrefs}. <br>
     * The data from the sample medi book will be used instead if {@code storage}'s medi book is not found,
     * or an empty medi book will be used instead if errors occur when reading {@code storage}'s medi book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyMediBook> mediBookOptional;
        ReadOnlyMediBook initialData;
        try {
            mediBookOptional = storage.readMediBook();
            if (!mediBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample MediBook");
            }
            initialData = mediBookOptional.orElseGet(SampleDataUtil::getSampleMediBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty MediBook");
            initialData = new MediBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty MediBook");
            initialData = new MediBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty MediBook");
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
        logger.info("Starting MediBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Medi Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
