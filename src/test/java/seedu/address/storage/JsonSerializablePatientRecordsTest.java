package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PatientRecords;
import seedu.address.storage.patient.JsonSerializablePatientRecords;
import seedu.address.testutil.TypicalPatients;

public class JsonSerializablePatientRecordsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePatientRecordsTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsPatientRecords.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientPatientRecords.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientPatientRecords.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializablePatientRecords.class).get();
        PatientRecords covigentAppFromFile = dataFromFile.toModelType();
        PatientRecords typicalPatientsCovigentApp = TypicalPatients.getTypicalPatientRecords();
        assertEquals(covigentAppFromFile, typicalPatientsCovigentApp);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializablePatientRecords.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializablePatientRecords.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePatientRecords.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
