package seedu.homerce.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class JsonAppointmentStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src", "test", "data",
        "Appointment", "JsonAppointmentStorageTest"
    );

    @TempDir
    public Path testFolder;

    @Test
    public void readAppointmentManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAppointmentManager(null));
    }

    private Optional<ReadOnlyAppointmentManager> readAppointmentManager(String filePath) throws Exception {
        return new JsonAppointmentStorage(Paths.get(filePath))
            .readAppointmentManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAppointmentManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAppointmentManager("notJsonFormatAppointment.json"));
    }

    @Test
    public void readAppointmentManager_invalidAppointmentManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAppointmentManager("invalidAppointmentManager.json"));
    }

    @Test
    public void readAppointmentManager_invalidAndValidAppointmentManager_throwDataConversionException() {
        assertThrows(
            DataConversionException.class, () ->
            readAppointmentManager("invalidAndValidAppointmentManager.json")
        );
    }

    @Test
    public void readAndSaveAppointmentManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAppointmentManager.json");
        AppointmentManager original = getTypicalAppointmentManager();
        JsonAppointmentStorage jsonAppointmentStorage = new JsonAppointmentStorage(filePath);

        // Save in new file and read back
        jsonAppointmentStorage.saveAppointmentManager(original, filePath);
        ReadOnlyAppointmentManager readBack = jsonAppointmentStorage.readAppointmentManager(filePath).get();
        assertEquals(original, new AppointmentManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAppointment(new AppointmentBuilder().withDate("1-1-2021").build());
        original.removeAppointment(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        jsonAppointmentStorage.saveAppointmentManager(original, filePath);
        readBack = jsonAppointmentStorage.readAppointmentManager(filePath).get();
        assertEquals(original, new AppointmentManager(readBack));

        // Save and read without specifying file path
        original.addAppointment(new AppointmentBuilder().withDate("2-2-2021").build());
        jsonAppointmentStorage.saveAppointmentManager(original); // file path not specified
        readBack = jsonAppointmentStorage.readAppointmentManager().get(); // file path not specified
        assertEquals(original, new AppointmentManager(readBack));
    }

    @Test
    public void saveAppointmentManager_nullAppointmentManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code appointmentManager} at the specified {@code filePath}.
     */
    private void saveAppointmentManager(ReadOnlyAppointmentManager appointmentManager, String filePath) {
        try {
            new JsonAppointmentStorage(Paths.get(filePath))
                .saveAppointmentManager(appointmentManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAppointmentManager_nullFilePath_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, () ->
            saveAppointmentManager(new AppointmentManager(), null)
        );
    }
}
