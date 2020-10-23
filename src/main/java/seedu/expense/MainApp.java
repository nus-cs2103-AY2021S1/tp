package seedu.expense;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.expense.commons.core.Config;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.commons.core.Version;
import seedu.expense.commons.exceptions.DataConversionException;
import seedu.expense.commons.util.ConfigUtil;
import seedu.expense.commons.util.StringUtil;
import seedu.expense.logic.Logic;
import seedu.expense.logic.LogicManager;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.ReadOnlyUserPrefs;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.util.SampleDataUtil;
import seedu.expense.storage.ExpenseBookStorage;
import seedu.expense.storage.JsonAliasMapStorage;
import seedu.expense.storage.JsonExpenseBookStorage;
import seedu.expense.storage.JsonUserPrefsStorage;
import seedu.expense.storage.Storage;
import seedu.expense.storage.StorageManager;
import seedu.expense.storage.UserPrefsStorage;
import seedu.expense.ui.Ui;
import seedu.expense.ui.UiManager;

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
        logger.info("=============================[ Initializing ExpenseBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ExpenseBookStorage expenseBookStorage = new JsonExpenseBookStorage(userPrefs.getExpenseBookFilePath());
        JsonAliasMapStorage aliasMapStorage = new JsonAliasMapStorage(userPrefs.getAliasMapFilePath());
        storage = new StorageManager(expenseBookStorage, userPrefsStorage, aliasMapStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s expense book and {@code userPrefs}. <br>
     * The data from the sample expense book will be used instead if {@code storage}'s expense book is not found,
     * or an empty expense book will be used instead if errors occur when reading {@code storage}'s expense book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyExpenseBook> expenseBookOptional;
        Optional<AliasMap> aliasMapOptional;
        ReadOnlyExpenseBook initialData;
        AliasMap aliasMap;
        try {
            expenseBookOptional = storage.readExpenseBook();
            if (!expenseBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ExpenseBook");
            }
            initialData = expenseBookOptional.orElseGet(SampleDataUtil::getSampleExpenseBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ExpenseBook");
            initialData = new ExpenseBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ExpenseBook");
            initialData = new ExpenseBook();
        }
        try {
            aliasMapOptional = storage.readAliasMap();
            if (!aliasMapOptional.isPresent()) {
                logger.info("Alias file not found. Will be starting with default settings");
            }
            aliasMap = aliasMapOptional.orElseGet(SampleDataUtil::getSampleAliasMap);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with default commands");
            aliasMap = new AliasMap();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with default commands");
            aliasMap = new AliasMap();
        }

        return new ModelManager(initialData, userPrefs, aliasMap);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ExpenseBook");
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
        logger.info("Starting ExpenseBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Expense Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
