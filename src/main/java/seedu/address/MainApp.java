package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import seedu.address.model.*;
import seedu.address.model.room.Double;
import seedu.address.model.room.Room;
import seedu.address.model.room.Single;
import seedu.address.model.room.Suite;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.BookingBookStorage;
import seedu.address.storage.JsonBookingBookStorage;
import seedu.address.storage.JsonPersonBookStorage;
import seedu.address.storage.JsonRoomServiceBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.PersonBookStorage;
import seedu.address.storage.RoomServiceBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=========================[ Initializing PersonBook & BookingBook ]=======================");
        logger.info("=============================[ Initializing RoomServiceBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PersonBookStorage personBookStorage = new JsonPersonBookStorage(userPrefs.getPersonBookFilePath());
        BookingBookStorage bookingBookStorage = new JsonBookingBookStorage(userPrefs.getBookingBookFilePath());
        RoomServiceBookStorage roomServiceBookStorage =
                new JsonRoomServiceBookStorage(userPrefs.getRoomServiceBookFilePath());
        storage = new StorageManager(personBookStorage, bookingBookStorage, userPrefsStorage, roomServiceBookStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    private ReadOnlyRoomBook initRooms() {
        logger.info("=============================[ Initializing roomData ]===========================");
        RoomBook ret = new RoomBook();
        final List<Room> roomData = IntStream.rangeClosed(2103, 2132)
                                                .mapToObj(x -> initRoomsHelper(x))
                                                .collect(Collectors.toList());

        ret.setRooms(roomData);
        return ret;
    }

    private Room initRoomsHelper(int x) {
        if (x >= 2103 && x < 2113) {
            return new Single(x);
        } else if (x < 2123) {
            return new Double(x);
        } else {
            return new Suite(x);
        }
    }
    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPersonBook> personBookOptional;
        Optional<ReadOnlyBookingBook> bookingBookOptional;
        Optional<ReadOnlyRoomServiceBook> roomServiceBookOptional;
        ReadOnlyPersonBook initialData;
        ReadOnlyBookingBook bookingData;
        ReadOnlyRoomServiceBook roomServiceData;
        ReadOnlyRoomBook roomData = initRooms();

        try {
            personBookOptional = storage.readPersonBook();
            if (!personBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PersonBook");
            }
            initialData = personBookOptional.orElseGet(SampleDataUtil::getSamplePersonBook);

            bookingBookOptional = storage.readBookingBook();
            if (!bookingBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample BookingBook");
            }
            bookingData = bookingBookOptional.orElseGet(SampleDataUtil::getSampleBookingBook);

        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PersonBook");
            initialData = new PersonBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PersonBook");
            initialData = new PersonBook();
        }


        // read bookingBook data
        try {
            bookingBookOptional = storage.readBookingBook();
            if (!bookingBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample BookingBook");
            }
            bookingData = bookingBookOptional.orElseGet(SampleDataUtil::getSampleBookingBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty BookingBook");
            bookingData = new BookingBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty BookingBook");
            bookingData = new BookingBook();
        }

        // read roomServiceBook data
        try {
            roomServiceBookOptional = storage.readRoomServiceBook();
            if (!roomServiceBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample RoomServiceBook");
            }
            roomServiceData = roomServiceBookOptional.orElseGet(SampleDataUtil::getSampleRoomServiceBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty RoomServiceBook");
            roomServiceData = new RoomServiceBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty RoomServiceBook");
            roomServiceData = new RoomServiceBook();
        }

        return new ModelManager(initialData, userPrefs, roomData, bookingData, roomServiceData);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PersonBook");
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
        logger.info("Starting ConciergeBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Concierge Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
