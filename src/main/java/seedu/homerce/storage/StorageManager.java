package seedu.homerce.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.ReadOnlyUserPrefs;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.storage.appointment.AppointmentStorage;
import seedu.homerce.storage.client.ClientStorage;
import seedu.homerce.storage.expense.ExpenseStorage;
import seedu.homerce.storage.revenue.RevenueStorage;
import seedu.homerce.storage.service.ServiceStorage;

/**
 * Manages storage of ClientManager data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClientStorage clientStorage;
    private ServiceStorage serviceStorage;
    private AppointmentStorage appointmentStorage;
    private ExpenseStorage expenseStorage;
    private UserPrefsStorage userPrefsStorage;
    private RevenueStorage revenueStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code ClientManagerStorage}, {@code UserPrefStorage}, {@code ServiceStorage} and {@code RevenueStorage}.
     */
    public StorageManager(UserPrefsStorage userPrefsStorage, ClientStorage clientStorage,
                          ServiceStorage serviceStorage, RevenueStorage revenueStorage,
                          ExpenseStorage expenseStorage, AppointmentStorage appointmentStorage) {
        super();
        this.clientStorage = clientStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.serviceStorage = serviceStorage;
        this.revenueStorage = revenueStorage;
        this.expenseStorage = expenseStorage;
        this.appointmentStorage = appointmentStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ClientManager methods ==============================

    @Override
    public Path getClientManagerFilePath() {
        return clientStorage.getClientManagerFilePath();
    }

    @Override
    public Optional<ReadOnlyClientManager> readClientManager() throws DataConversionException, IOException {
        return readClientManager(clientStorage.getClientManagerFilePath());
    }

    @Override
    public Optional<ReadOnlyClientManager> readClientManager(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clientStorage.readClientManager(filePath);
    }

    @Override
    public void saveClientManager(ReadOnlyClientManager clientManager) throws IOException {
        saveClientManager(clientManager, clientStorage.getClientManagerFilePath());
    }

    @Override
    public void saveClientManager(ReadOnlyClientManager clientManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clientStorage.saveClientManager(clientManager, filePath);
    }

    // ================ ServiceManager methods ==============================

    @Override
    public Path getServiceManagerStorageFilePath() {
        return serviceStorage.getServiceManagerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyServiceManager> readServiceManager() throws DataConversionException, IOException {
        return readServiceManager(serviceStorage.getServiceManagerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyServiceManager> readServiceManager(Path filePath) throws DataConversionException,
        IOException {
        return serviceStorage.readServiceManager(filePath);
    }

    @Override
    public void saveServiceManager(ReadOnlyServiceManager serviceManager) throws IOException {
        saveServiceManager(serviceManager, serviceStorage.getServiceManagerStorageFilePath());
    }

    @Override
    public void saveServiceManager(ReadOnlyServiceManager serviceManager, Path filePath) throws IOException {
        serviceStorage.saveServiceManager(serviceManager, filePath);
    }

    // =========================== AppointmentManager methods ===========================

    @Override
    public Path getAppointmentManagerStorageFilePath() {
        return appointmentStorage.getAppointmentManagerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentManager> readAppointmentManager() throws DataConversionException, IOException {
        return readAppointmentManager(appointmentStorage.getAppointmentManagerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentManager> readAppointmentManager(Path filePath)
            throws DataConversionException, IOException {
        return appointmentStorage.readAppointmentManager(filePath);
    }

    @Override
    public void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager) throws IOException {
        saveAppointmentManager(appointmentManager, appointmentStorage.getAppointmentManagerStorageFilePath());
    }

    @Override
    public void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager, Path filePath)
            throws IOException {
        appointmentStorage.saveAppointmentManager(appointmentManager, filePath);
    }

    // ================ RevenueTracker methods ==============================

    @Override
    public Path getRevenueTrackerStorageFilePath() {
        return revenueStorage.getRevenueTrackerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyRevenueTracker> readRevenueTracker() throws DataConversionException, IOException {
        return readRevenueTracker(revenueStorage.getRevenueTrackerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyRevenueTracker> readRevenueTracker(Path filePath) throws DataConversionException,
        IOException {
        return revenueStorage.readRevenueTracker(filePath);
    }

    @Override
    public void saveRevenueTracker(ReadOnlyRevenueTracker revenueTracker) throws IOException {
        saveRevenueTracker(revenueTracker, revenueStorage.getRevenueTrackerStorageFilePath());
    }

    @Override
    public void saveRevenueTracker(ReadOnlyRevenueTracker revenueTracker, Path filePath) throws IOException {
        revenueStorage.saveRevenueTracker(revenueTracker, filePath);
    }

    // ================ ExpenseTracker methods ==============================

    @Override
    public Path getExpenseTrackerStorageFilePath() {
        return expenseStorage.getExpenseTrackerStorageFilePath();
    }

    @Override
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker() throws DataConversionException, IOException {
        return readExpenseTracker(expenseStorage.getExpenseTrackerStorageFilePath());
    }

    @Override
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker(Path filePath) throws DataConversionException,
        IOException {
        return expenseStorage.readExpenseTracker(filePath);
    }

    @Override
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker) throws IOException {
        saveExpenseTracker(expenseTracker, expenseStorage.getExpenseTrackerStorageFilePath());
    }

    @Override
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, Path filePath) throws IOException {
        expenseStorage.saveExpenseTracker(expenseTracker, filePath);
    }
}
