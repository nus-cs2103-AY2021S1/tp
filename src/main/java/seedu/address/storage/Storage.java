package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.storage.patient.PatientRecordsStorage;
import seedu.address.storage.rooms.RoomRecordsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends PatientRecordsStorage, UserPrefsStorage, RoomRecordsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPatientRecordsFilePath();

    @Override
    Optional<ReadOnlyList<Patient>> readPatientRecords() throws DataConversionException, IOException;

    @Override
    void savePatientRecords(ReadOnlyList<Patient> patientRecords) throws IOException;

    @Override
    Optional<ReadOnlyList<Room>> readOnlyRoomOccupancy() throws DataConversionException, IOException;

    @Override
    void saveRoomsInformation(ReadOnlyList<Room> roomList) throws IOException;
}
