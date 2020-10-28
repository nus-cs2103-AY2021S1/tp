package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CliniCal;
import seedu.address.testutil.TypicalPatients;

public class JsonSerializableCliniCalTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCliniCalTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsCliniCal.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientCliniCal.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientCliniCal.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableCliniCal dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializableCliniCal.class).get();
        CliniCal cliniCalFromFile = dataFromFile.toModelType();
        CliniCal typicalPatientsCliniCal = TypicalPatients.getTypicalCliniCal();
        assertEquals(cliniCalFromFile.getPatientList(), typicalPatientsCliniCal.getPatientList());
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCliniCal dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableCliniCal.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableCliniCal dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableCliniCal.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCliniCal.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
