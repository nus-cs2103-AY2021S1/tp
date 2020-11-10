//@@author
package seedu.momentum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.momentum.commons.core.Config;
import seedu.momentum.commons.core.LogsCenter;
import seedu.momentum.commons.core.Version;
import seedu.momentum.commons.exceptions.DataConversionException;
import seedu.momentum.commons.util.ConfigUtil;
import seedu.momentum.commons.util.StringUtil;
import seedu.momentum.logic.Logic;
import seedu.momentum.logic.LogicManager;
import seedu.momentum.logic.SettingsUpdateManager;
import seedu.momentum.model.Model;
import seedu.momentum.model.ModelManager;
import seedu.momentum.model.ProjectBook;
import seedu.momentum.model.ReadOnlyProjectBook;
import seedu.momentum.model.ReadOnlyUserPrefs;
import seedu.momentum.model.UserPrefs;
import seedu.momentum.model.util.SampleDataUtil;
import seedu.momentum.storage.JsonProjectBookStorage;
import seedu.momentum.storage.JsonUserPrefsStorage;
import seedu.momentum.storage.ProjectBookStorage;
import seedu.momentum.storage.Storage;
import seedu.momentum.storage.StorageManager;
import seedu.momentum.storage.UserPrefsStorage;
import seedu.momentum.ui.Ui;
import seedu.momentum.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        LOGGER.info("=============================[ Initializing ProjectBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ProjectBookStorage projectBookStorage = new JsonProjectBookStorage(userPrefs.getProjectBookFilePath());
        storage = new StorageManager(projectBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        //@@author khoodehui
        SettingsUpdateManager.initSettingsUpdateManager(ui, logic.getStatistic());
        //@@author
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s project book and {@code userPrefs}. <br>
     * The data from the sample project book will be used instead if {@code storage}'s project book is not found,
     * or an empty project book will be used instead if errors occur when reading {@code storage}'s project book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyProjectBook> projectBookOptional;
        ReadOnlyProjectBook initialData;
        try {
            projectBookOptional = storage.readProjectBook();
            if (!projectBookOptional.isPresent()) {
                LOGGER.info("Data file not found. Will be starting with a sample ProjectBook");
            }

            Optional<ReadOnlyProjectBook> defaultProjectBookOptional = getDefaultData();
            if (!projectBookOptional.isPresent()) {
                LOGGER.info("Default data file not found. Will start with bare bones sample data");
            }

            initialData = projectBookOptional
                    .or(() -> defaultProjectBookOptional)
                    .orElseGet(SampleDataUtil::getSampleProjectBook);

        } catch (DataConversionException e) {
            LOGGER.warning("Data file not in the correct format. Will be starting with an empty ProjectBook");
            initialData = new ProjectBook();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file. Will be starting with an empty ProjectBook");
            initialData = new ProjectBook();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private Optional<ReadOnlyProjectBook> getDefaultData() throws IOException, DataConversionException {
        File file = null;
        String resource = "/data/defaultData.json";
        URL res = getClass().getResource(resource);
        if (res.getProtocol().equals("jar")) {
            InputStream input = getClass().getResourceAsStream(resource);
            file = File.createTempFile("tempfile", ".tmp");
            OutputStream out = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];

            while ((read = input.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
            file.deleteOnExit();
        } else {
            file = new File(URLDecoder.decode(res.getFile(), Charset.defaultCharset()));
        }

        if (!file.exists()) {
            throw new IOException();
        }

        ProjectBookStorage defaultStorage = new JsonProjectBookStorage(file.toPath());
        return defaultStorage.readProjectBook();
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
            LOGGER.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        LOGGER.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            LOGGER.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            LOGGER.warning("Failed to save config file : " + StringUtil.getDetails(e));
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
        LOGGER.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            LOGGER.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file. Will be starting with an empty ProjectBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            LOGGER.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting ProjectBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        LOGGER.info("============================ [ Stopping Project Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
            LOGGER.info("Saved preferences. Quitting application now.");
            Platform.exit();
            System.exit(0);
        } catch (IOException e) {
            LOGGER.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
