package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

class MedicalNoteListTest {
    private final MedicalNote medicalNote1 = new MedicalNote(new Date("20-10-2019", true),
            new Doctor(new Name("John"), new Mcr("M52739B")), new Content("Patient is awesome."));
    private final MedicalNote medicalNote2 = new MedicalNote(new Date("25-10-2019", true),
            new Doctor(new Name("Gary"), new Mcr("M12009B")), new Content("Patient is bad."));
    private final MedicalNote medicalNote3 = new MedicalNote(new Date("22-09-2020", true),
            new Doctor(new Name("Alonso"), new Mcr("M28060B")), new Content("Patient is bad."));

    @Test
    void add_emptyMedicalNoteList_success() {
        MedicalNoteList emptyMedicalNoteList = new MedicalNoteList();
        emptyMedicalNoteList.add(medicalNote1);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote1)));
        assertEquals(expectedMedicalNoteList, emptyMedicalNoteList);
    }

    @Test
    void add_nonEmptyMedicalNoteList_successWithCorrectOrder() {
        MedicalNoteList partiallyFilledMedicalNoteList =
                new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote1)));
        partiallyFilledMedicalNoteList.add(medicalNote2);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote2, medicalNote1)));
        assertEquals(expectedMedicalNoteList, partiallyFilledMedicalNoteList);
        assertEquals(Arrays.asList(medicalNote2, medicalNote1), partiallyFilledMedicalNoteList.getMedicalNoteList());
    }

    @Test
    void makeCopy() {
        MedicalNoteList testList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote1)));
        MedicalNoteList copiedList = testList.makeCopy();
        assertEquals(testList, copiedList);
        assertTrue(copiedList != testList);
    }

    @Test
    void deleteMedicalNoteAtIndex_validIndex_success() {
        MedicalNoteList medicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote1, medicalNote2)));
        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote2)));

        medicalNoteList.deleteMedicalNoteAtIndex(1);

        assertEquals(expectedMedicalNoteList, medicalNoteList);
    }

    @Test
    void getMedicalNoteAtIndex_validIndex_success() {
        MedicalNoteList medicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote1, medicalNote2)));
        MedicalNote expectedMedicalNote = medicalNote1;

        MedicalNote actualMedicalNote = medicalNoteList.getMedicalNoteAtIndex(1);

        assertEquals(expectedMedicalNote, actualMedicalNote);
    }

    @Test
    public void alreadyHasMedicalNote_duplicateNote_true() {
        MedicalNoteList medicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote1, medicalNote2)));
        assertTrue(medicalNoteList.alreadyHasMedicalNote(
                new MedicalNote(new Date("20-10-2019", true),
                        new Doctor(new Name("John"), new Mcr("M52739B")), new Content("Patient is awesome."))));
    }

    @Test
    public void alreadyHasMedicalNote_newNote_false() {
        MedicalNoteList medicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote1, medicalNote2)));
        assertFalse(medicalNoteList.alreadyHasMedicalNote(medicalNote3));
    }
}
