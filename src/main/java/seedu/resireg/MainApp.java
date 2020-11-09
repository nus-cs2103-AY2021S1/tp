package seedu.resireg;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.resireg.commons.core.Config;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.commons.core.Version;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.commons.util.ConfigUtil;
import seedu.resireg.commons.util.StringUtil;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.LogicManager;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.util.SampleDataUtil;
import seedu.resireg.storage.JsonResiRegStorage;
import seedu.resireg.storage.JsonUserPrefsStorage;
import seedu.resireg.storage.ResiRegStorage;
import seedu.resireg.storage.Storage;
import seedu.resireg.storage.StorageManager;
import seedu.resireg.storage.UserPrefsStorage;
import seedu.resireg.ui.Ui;
import seedu.resireg.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing ResiReg ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ResiRegStorage resiRegStorage = new JsonResiRegStorage(userPrefs.getResiRegFilePath());
        storage = new StorageManager(resiRegStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s ResiReg and {@code userPrefs}. <br>
     * The data from the sample ResiReg will be used instead if {@code storage}'s ResiReg is not found,
     * or an empty ResiReg model will be used instead if errors occur when reading {@code storage}'s ResiReg.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyResiReg> resiRegOptional;
        ReadOnlyResiReg initialData;
        try {
            resiRegOptional = storage.readResiReg();
            if (!resiRegOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ResiReg");
            }
            initialData = resiRegOptional.orElseGet(SampleDataUtil::getSampleResiReg);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ResiReg");
            initialData = new ResiReg();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ResiReg");
            initialData = new ResiReg();
        }

        // delete expired items on start up
        ModelManager modelManager = new ModelManager(initialData, userPrefs);
        modelManager.deleteExpiredBinItems();
        return modelManager;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ResiReg");
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
        logger.info("Starting ResiReg " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ResiReg ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
