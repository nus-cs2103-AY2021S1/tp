package seedu.forgetfulnus;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.forgetfulnus.commons.core.Config;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.commons.core.Version;
import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.commons.util.ConfigUtil;
import seedu.forgetfulnus.commons.util.StringUtil;
import seedu.forgetfulnus.logic.Logic;
import seedu.forgetfulnus.logic.LogicManager;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.ModelManager;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.model.util.SampleDataUtil;
import seedu.forgetfulnus.storage.JsonGlossaryStorage;
import seedu.forgetfulnus.storage.JsonScoreStorage;
import seedu.forgetfulnus.storage.JsonUserPrefsStorage;
import seedu.forgetfulnus.storage.Storage;
import seedu.forgetfulnus.storage.StorageManager;
import seedu.forgetfulnus.storage.UserPrefsStorage;
import seedu.forgetfulnus.storage.interfaces.ObjectStorage;
import seedu.forgetfulnus.ui.Ui;
import seedu.forgetfulnus.ui.UiManager;

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
        logger.info("=============================[ Initialising Glossary ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ObjectStorage<ReadOnlyGlossary> glossaryStorage = new JsonGlossaryStorage(userPrefs.getGlossaryFilePath());
        ObjectStorage<ReadOnlyScoreList> scoreStorage = new JsonScoreStorage(userPrefs.getScoresFilePath());
        storage = new StorageManager(glossaryStorage, scoreStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s glossary and {@code userPrefs}. <br>
     * The data from the sample glossary will be used instead if {@code storage}'s glossary is not found,
     * or an empty glossary will be used instead if errors occur when reading {@code storage}'s glossary.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyGlossary initialData = initialiseGlossary(storage);
        ReadOnlyScoreList initialScores = initialiseScoreList(storage);
        return new ModelManager(initialData, initialScores, userPrefs);
    }

    private ReadOnlyGlossary initialiseGlossary(Storage storage) {
        try {
            Optional<ReadOnlyGlossary> glossaryOptional = storage.readGlossary();
            if (glossaryOptional.isEmpty()) {
                logger.info("Glossary data file not found. Will be starting with a sample Glossary");
            }
            return glossaryOptional.orElseGet(SampleDataUtil::getSampleGlossary);
        } catch (DataConversionException e) {
            logger.warning("Glossary data file not in the correct format. Will be starting with an empty Glossary");
            return new Glossary();
        } catch (IOException e) {
            logger.warning("Problem while reading from the glossary file. Will be starting with an empty Glossary");
            return new Glossary();
        }
    }

    private ReadOnlyScoreList initialiseScoreList(Storage storage) {
        try {
            Optional<ReadOnlyScoreList> scoreListOptional = storage.readScores();
            if (scoreListOptional.isEmpty()) {
                logger.info("Score data file not found. Will be starting with an empty score list");
            }
            return scoreListOptional.orElseGet(ScoreList::new);
        } catch (DataConversionException e) {
            logger.warning("Score data file not in the correct format. Will be starting with an empty score list");
            return new ScoreList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the score file. Will be starting with an empty score list");
            return new ScoreList();
        }
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
        Config initialisedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initialisedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initialisedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initialisedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initialisedConfig;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Glossary");
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
        logger.info("Starting Glossary " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Glossary ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
