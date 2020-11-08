package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;

/**
 * API of the Storage component
 */
public interface Storage extends PatientRecordsStorage, UserPrefsStorage {

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

    /** Reads the data of number of rooms and occupied rooms into RoomList **/
    Optional<ReadOnlyList<Room>> readRoomOccupancyStorage() throws DataConversionException, IOException;

    /**
     * Saves the information given by user into a hard disk. Such information includes number of rooms and room number
     * of occupied rooms
     * @param roomList contains user inputs
     * @throws IOException
     */
    void saveRoomList(RoomList roomList) throws IOException;

}
