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
import seedu.address.model.ClientList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyClientList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.util.SampleClientDataUtil;
import seedu.address.model.util.SamplePolicyDataUtil;
import seedu.address.storage.ClientListStorage;
import seedu.address.storage.JsonClientListStorage;
import seedu.address.storage.JsonPolicyListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.PolicyListStorage;
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
        logger.info("=============================[ Initializing ClientList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClientListStorage clientListStorage = new JsonClientListStorage(userPrefs.getClientListFilePath());
        PolicyListStorage policyListStorage = new JsonPolicyListStorage(userPrefs.getPolicyListFilePath());
        storage = new StorageManager(clientListStorage, userPrefsStorage, policyListStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s client list and {@code userPrefs}. <br>
     * The data from the sample client list will be used instead if {@code storage}'s client list is not found,
     * or an empty client list will be used instead if errors occur when reading {@code storage}'s client list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyClientList> clientListOptional;
        ReadOnlyClientList initialClientData;
        try {
            clientListOptional = storage.readClientList();
            if (!clientListOptional.isPresent()) {
                logger.info("Client List data file not found. Will be starting with a sample ClientList");
            }
            initialClientData = clientListOptional.orElseGet(SampleClientDataUtil::getSampleClientList);
        } catch (DataConversionException e) {
            logger.warning(
                    "Client List data file not in the correct format. Will be starting with an empty ClientList");
            initialClientData = new ClientList();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the Client List data file. Will be starting with an empty ClientList");
            initialClientData = new ClientList();
        }

        Optional<PolicyList> policyListOptional;
        PolicyList initialPolicyData;
        try {
            policyListOptional = storage.readPolicyList();
            if (!policyListOptional.isPresent()) {
                logger.info("Policy List data file not found. Will be starting with a sample PolicyList");
            }
            initialPolicyData = policyListOptional.orElseGet(SamplePolicyDataUtil::getSamplePolicyList);
        } catch (DataConversionException e) {
            logger.warning(
                    "Policy List data file not in the correct format. Will be starting with an empty PolicyList");
            initialPolicyData = new PolicyList();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the Policy List data file. Will be starting with an empty PolicyList");
            initialPolicyData = new PolicyList();
        }

        initialClientData.updateClientListWithPolicyList(initialPolicyData);

        return new ModelManager(initialClientData, userPrefs, initialPolicyData);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ClientList");
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
        logger.info("Starting ClientList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Client List ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
