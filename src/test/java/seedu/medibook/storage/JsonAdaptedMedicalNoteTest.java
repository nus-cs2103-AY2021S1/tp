package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.model.commonfields.Date.MESSAGE_CONSTRAINTS;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.ERROR_MESSAGE_NULL_FIELD;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE1;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Mcr;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;

public class JsonAdaptedMedicalNoteTest {
    @Test
    public void toModelType_validMedicalNotesDetails_returnsMedicalNote() throws Exception {
        MedicalNote medicalNote = VALID_MEDICAL_NOTE1;
        JsonAdaptedMedicalNote patient = new JsonAdaptedMedicalNote(medicalNote);
        assertEquals(medicalNote, patient.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("30-30-2020", "Dr Watson", "M84197F", "Patient is feeling well");
        String expectedMessage = MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote(null, "Dr Watson", "M84197F", "Patient is feeling well");
        String expectedMessage = ERROR_MESSAGE_NULL_FIELD;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDoctorName_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", null, "M84197F", "Patient is feeling well");
        String expectedMessage = ERROR_MESSAGE_NULL_FIELD;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorName_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "A* Doctor", "M84197F", "Patient is feeling well");
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDoctorMcr_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "Dr Watson", null, "Patient is feeling well");
        String expectedMessage = ERROR_MESSAGE_NULL_FIELD;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorMcr_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "Dr Watson", "X84197F", "Patient is feeling well");
        String expectedMessage = Mcr.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "Dr Watson", "M84197F", null);
        String expectedMessage = ERROR_MESSAGE_NULL_FIELD;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidContent_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "Dr Watson", "M84197F", "");
        String expectedMessage = Content.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

}
