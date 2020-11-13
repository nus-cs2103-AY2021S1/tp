package seedu.jarvis;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.jarvis.commons.core.Config;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.commons.core.Version;
import seedu.jarvis.commons.exceptions.DataConversionException;
import seedu.jarvis.commons.exceptions.ScraperParsingException;
import seedu.jarvis.commons.util.ConfigUtil;
import seedu.jarvis.commons.util.StringUtil;
import seedu.jarvis.logic.Logic;
import seedu.jarvis.logic.LogicManager;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.ReadOnlyUserLogin;
import seedu.jarvis.model.ReadOnlyUserPrefs;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.UserPrefs;
import seedu.jarvis.model.util.SampleDataUtil;
import seedu.jarvis.scraper.Scraper;
import seedu.jarvis.scraper.ScraperManager;
import seedu.jarvis.storage.AddressBookStorage;
import seedu.jarvis.storage.JsonAddressBookStorage;
import seedu.jarvis.storage.JsonUserLoginStorage;
import seedu.jarvis.storage.JsonUserPrefsStorage;
import seedu.jarvis.storage.Storage;
import seedu.jarvis.storage.StorageManager;
import seedu.jarvis.storage.UserLoginStorage;
import seedu.jarvis.storage.UserPrefsStorage;
import seedu.jarvis.ui.Ui;
import seedu.jarvis.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 7, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Scraper scraper;
    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        UserLoginStorage userLoginStorage = new JsonUserLoginStorage(config.getUserLoginDetails());
        UserLogin userLogin = initLogin(userLoginStorage);

        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage, userLoginStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs, userLogin);

        scraper = initScraper(userLogin, model, storage);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a UserLogin object corresponding to the data stored.
     * @param storage The place where UserLogin data is stored
     * @return A UserLogin object
     */
    private UserLogin initLogin(UserLoginStorage storage) {
        Path loginFilePath = storage.getUserLoginFilePath();
        logger.info("Using login file : " + loginFilePath);

        UserLogin initializedLogin;
        try {
            Optional<UserLogin> loginOptional = storage.readUserLogin();

            if (loginOptional.isEmpty()) {
                logger.warning("Please edit username and password details and restart again.");
            }
            initializedLogin = loginOptional.orElse(new UserLogin());

        } catch (DataConversionException e) {
            logger.warning("UserLogin file at " + loginFilePath + " is not in the correct format. "
                    + "Asking user to edit login information and restart again.");
            initializedLogin = new UserLogin();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with empty login information");
            initializedLogin = new UserLogin();
        }

        return initializedLogin;
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s jarvis book and {@code userPrefs}. <br>
     * The data from the sample jarvis book will be used instead if {@code storage}'s jarvis book is not found,
     * or an empty jarvis book will be used instead if errors occur when reading {@code storage}'s jarvis book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs, ReadOnlyUserLogin userLogin) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        }

        return new ModelManager(initialData, userPrefs, userLogin);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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

    protected Scraper initScraper(UserLogin userLogin, Model model, Storage storage)
            throws ScraperParsingException, IOException {
        logger.info("Starting scraper to scrape SourceAcademy");
        Scraper scraper = new ScraperManager(userLogin, model, storage);
        model.addPropertyChangeListener((PropertyChangeListener) scraper);
        scraper.startScraping();

        return scraper;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
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
