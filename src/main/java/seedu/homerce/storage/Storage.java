package seedu.homerce.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.ReadOnlyUserPrefs;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.storage.appointment.AppointmentStorage;
import seedu.homerce.storage.client.ClientStorage;
import seedu.homerce.storage.expense.ExpenseStorage;
import seedu.homerce.storage.revenue.RevenueStorage;
import seedu.homerce.storage.service.ServiceStorage;

/**
 * API of the Storage component
 */
public interface Storage extends ClientStorage, UserPrefsStorage, ServiceStorage, RevenueStorage, ExpenseStorage,
    AppointmentStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClientManagerFilePath();

    @Override
    Optional<ReadOnlyClientManager> readClientManager() throws DataConversionException, IOException;

    @Override
    void saveClientManager(ReadOnlyClientManager clientManager) throws IOException;

}
