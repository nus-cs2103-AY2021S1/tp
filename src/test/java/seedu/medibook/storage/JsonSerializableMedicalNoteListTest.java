package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.testutil.TypicalMedicalNotes;

public class JsonSerializableMedicalNoteListTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableMedicalNoteListTest");
    private static final Path TYPICAL_PATIENTS_MEDICAL_NOTES =
            TEST_DATA_FOLDER.resolve("typicalPatientMedicalNotes.json");
    private static final Path INVALID_PATIENTS_MEDICAL_NOTES =
            TEST_DATA_FOLDER.resolve("invalidPatientMedicalNotes.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializableMedicalNoteList dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_MEDICAL_NOTES,
                JsonSerializableMedicalNoteList.class).get();
        MedicalNoteList medicalNotesFromFile = dataFromFile.toModelType();
        MedicalNoteList typicalPatientsMedicalNotes = TypicalMedicalNotes.getTypicalMedicalNoteList();
        assertEquals(medicalNotesFromFile, typicalPatientsMedicalNotes);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMedicalNoteList dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENTS_MEDICAL_NOTES,
                JsonSerializableMedicalNoteList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
