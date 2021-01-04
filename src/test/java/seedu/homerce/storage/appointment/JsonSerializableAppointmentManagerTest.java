package seedu.homerce.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.testutil.appointment.TypicalAppointments;

public class JsonSerializableAppointmentManagerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Appointment",
        "JsonSerializableAppointmentManagerTest");
    private static final Path TYPICAL_APPOINTMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalAppointmentManager.json");
    private static final Path INVALID_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAppointmentManager.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateAppointment.json");

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableAppointmentManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
            JsonSerializableAppointmentManager.class).get();
        AppointmentManager appointmentManagerFromFile = dataFromFile.toModelType();
        AppointmentManager typicalAppointmentsAppointmentManager = TypicalAppointments.getTypicalAppointmentManager();
        assertEquals(appointmentManagerFromFile, typicalAppointmentsAppointmentManager);
    }

    @Test
    public void toModelType_invalidAppointmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentManager dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
            JsonSerializableAppointmentManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
            JsonSerializableAppointmentManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAppointmentManager.MESSAGE_DUPLICATE_APPOINTMENT,
            dataFromFile::toModelType);
    }
}
