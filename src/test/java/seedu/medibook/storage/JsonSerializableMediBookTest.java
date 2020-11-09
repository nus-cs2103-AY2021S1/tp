package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.MediBook;
import seedu.medibook.testutil.TypicalPatients;

public class JsonSerializableMediBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMediBookTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientMediBook.json");
    private static final Path INVALID_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("invalidPatientMediBook.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientMediBook.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableMediBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializableMediBook.class).get();
        MediBook mediBookFromFile = dataFromFile.toModelType();
        MediBook typicalPatientsMediBookWithAllEmptyMedicalNoteList =
                TypicalPatients.getTypicalMediBookWithAllEmptyMedicalNoteList();
        assertEquals(mediBookFromFile, typicalPatientsMediBookWithAllEmptyMedicalNoteList);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMediBook dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENTS_FILE,
                JsonSerializableMediBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableMediBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableMediBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMediBook.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
