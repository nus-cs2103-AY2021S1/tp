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
import seedu.address.model.AttractionList;
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AttractionListStorage;
import seedu.address.storage.ItineraryListStorage;
import seedu.address.storage.JsonAttractionListStorage;
import seedu.address.storage.JsonItineraryListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
        logger.info("=============================[ Initializing TrackPad ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AttractionListStorage attractionListStorage =
                new JsonAttractionListStorage(userPrefs.getAttractionListFilePath());
        ItineraryListStorage itineraryListStorage =
                new JsonItineraryListStorage(userPrefs.getItineraryListFilePath());
        storage = new StorageManager(attractionListStorage, itineraryListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s trackpad and {@code userPrefs}. <br>
     * The data from the sample trackpad will be used instead if {@code storage}'s trackpad is not found,
     * or an empty trackpad will be used instead if errors occur when reading {@code storage}'s trackpad.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAttractionList> attractionListOptional;
        ReadOnlyAttractionList initialAttractionList;
        Optional<ReadOnlyItineraryList> itineraryListOptional;
        ReadOnlyItineraryList initialItineraryList;

        // attraction list
        try {
            attractionListOptional = storage.readAttractionList();
            if (attractionListOptional.isEmpty()) {
                logger.info("Attraction data file not found. Will be starting with a sample AttractionList");
            }
            initialAttractionList = attractionListOptional.orElseGet(SampleDataUtil::getSampleAttractionsList);
        } catch (DataConversionException e) {
            logger.warning("Attraction data file not in the correct format. Will be starting with an empty"
                    + " AttractionList");
            initialAttractionList = new AttractionList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the attraction data file. Will be starting with an"
                    + " empty AttractionList");
            initialAttractionList = new AttractionList();
        }

        // itinerary list
        try {
            itineraryListOptional = storage.readItineraryList();
            if (itineraryListOptional.isEmpty()) {
                logger.info("Itinerary data file not found. Will be starting with a sample ItineraryList");
            }
            initialItineraryList = itineraryListOptional.orElseGet(SampleDataUtil::getSampleItineraryList);
        } catch (DataConversionException e) {
            logger.warning("Itinerary data file not in the correct format. Will be starting with an empty"
                    + " ItineraryList");
            initialItineraryList = new ItineraryList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the itinerary data file. Will be starting with an"
                    + " empty ItineraryList");
            initialItineraryList = new ItineraryList();
        }

        return new ModelManager(initialAttractionList, initialItineraryList, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AttractionList"
                    + " and empty ItineraryList");
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
        logger.info("Starting TrackPad " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping TrackPad ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
