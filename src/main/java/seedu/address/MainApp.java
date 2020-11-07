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
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.bidderstorage.BidderAddressBookStorage;
import seedu.address.storage.bidderstorage.JsonBidderAddressBookStorage;
import seedu.address.storage.bidstorage.BidBookStorage;
import seedu.address.storage.bidstorage.JsonBidBookStorage;
import seedu.address.storage.meeting.JsonMeetingBookStorage;
import seedu.address.storage.meeting.MeetingBookStorage;
import seedu.address.storage.property.JsonPropertyBookStorage;
import seedu.address.storage.property.PropertyBookStorage;
import seedu.address.storage.sellerstorage.JsonSellerAddressBookStorage;
import seedu.address.storage.sellerstorage.SellerAddressBookStorage;
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
        logger.info("=============================[ Initializing PropertyFree ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PropertyBookStorage propertyBookStorage = new JsonPropertyBookStorage(userPrefs.getPropertyBookFilePath());
        BidBookStorage bidBookStorage = new JsonBidBookStorage(userPrefs.getBidBookFilePath());
        BidderAddressBookStorage bidderAddressBookStorage =
                new JsonBidderAddressBookStorage(userPrefs.getBidderAddressBookFilePath());
        SellerAddressBookStorage sellerAddressBookStorage =
                new JsonSellerAddressBookStorage(userPrefs.getSellerAddressBookFilePath());
        MeetingBookStorage meetingBookStorage =
                new JsonMeetingBookStorage(userPrefs.getMeetingBookFilePath());
        storage = new StorageManager(userPrefsStorage, bidBookStorage,
                bidderAddressBookStorage, sellerAddressBookStorage, meetingBookStorage, propertyBookStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s books are not found,
     * or an empty book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyBidBook> bidBookOptional;
        Optional<ReadOnlyBidderAddressBook> bidderAddressBookOptional;
        Optional<ReadOnlySellerAddressBook> sellerAddressBookOptional;
        Optional<ReadOnlyPropertyBook> propertyBookOptional;
        Optional<ReadOnlyMeetingBook> meetingBookOptional;

        ReadOnlyBidBook initialBidData;
        ReadOnlyBidderAddressBook initialBidderData;
        ReadOnlySellerAddressBook initialSellerData;
        ReadOnlyPropertyBook initialPropertyData;
        ReadOnlyMeetingBook initialMeetingData;

        try {
            bidBookOptional = storage.readBidBook();
            propertyBookOptional = storage.readPropertyBook();
            bidderAddressBookOptional = storage.readBidderAddressBook();
            sellerAddressBookOptional = storage.readSellerAddressBook();
            meetingBookOptional = storage.readMeetingBook();

            if (!bidBookOptional.isPresent()) {
                logger.info("BidBook Data file not found. Will be starting with a sample BidBook");
            }
            if (!bidderAddressBookOptional.isPresent()) {
                logger.info("BidderAddressBook Data file not found. Will be starting with a sample bidderAddressBook");
            }
            if (!sellerAddressBookOptional.isPresent()) {
                logger.info("SellerAddressBook Data file not found. Will be starting with a sample sellerAddressBook");
            }
            if (!meetingBookOptional.isPresent()) {
                logger.info("MeetingBook Data file not found. Will be starting with a sample meetingBook");
            }
            if (!propertyBookOptional.isPresent()) {
                logger.info("PropertyBook data file not found. Will be starting with a sample PropertyBook");
            }
            initialBidData = bidBookOptional.orElseGet(SampleDataUtil::getSampleBidBook);
            initialBidderData = bidderAddressBookOptional.orElseGet(SampleDataUtil::getSampleBidderAddressBook);
            initialSellerData = sellerAddressBookOptional.orElseGet(SampleDataUtil::getSampleSellerAddressBook);
            initialMeetingData = meetingBookOptional.orElseGet(SampleDataUtil::getSampleMeetingBook);
            initialPropertyData = propertyBookOptional.orElseGet(SampleDataUtil::getSamplePropertyBook);

        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PropertyFree");
            initialBidData = new BidBook();
            initialPropertyData = new PropertyBook();
            initialBidderData = new BidderAddressBook();
            initialSellerData = new SellerAddressBook();
            initialMeetingData = new MeetingBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PropertyFree");
            initialBidData = new BidBook();
            initialPropertyData = new PropertyBook();
            initialBidderData = new BidderAddressBook();
            initialSellerData = new SellerAddressBook();
            initialMeetingData = new MeetingBook();
        }
        return new ModelManager(userPrefs, initialBidData, initialPropertyData,
                initialBidderData, initialSellerData, initialMeetingData);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PropertyFree");
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
        logger.info("Starting PropertyFree " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping PropertyFree ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
            storage.savePropertyBook(model.getPropertyBook());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
