package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_DIABETES;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalPatients.ALICE;
import static seedu.medibook.testutil.TypicalPatients.ALICE_MEDICAL_NOTE_1;
import static seedu.medibook.testutil.TypicalPatients.ALICE_MEDICAL_NOTE_2;
import static seedu.medibook.testutil.TypicalPatients.ALICE_NUM_OF_MEDICAL_NOTES;
import static seedu.medibook.testutil.TypicalPatients.BENSON_MEDICAL_NOTE_1;
import static seedu.medibook.testutil.TypicalPatients.BENSON_MEDICAL_NOTE_2;
import static seedu.medibook.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.medibook.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getAllergies().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> patient.getConditions().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> patient.getTreatments().remove(0));
    }

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        //different ic -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withIc(VALID_IC_BOB).build();
        assertFalse(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same phone, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withAllergies(VALID_ALLERGY_SHELLFISH)
                .withConditions(VALID_CONDITION_BACK).withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same email, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withAllergies(VALID_ALLERGY_SHELLFISH)
                .withConditions(VALID_CONDITION_BACK).withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same phone, same email, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withAddress(VALID_ADDRESS_BOB)
                .withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB).withBloodType(VALID_BLOOD_TYPE_BOB)
                .withAllergies(VALID_ALLERGY_SHELLFISH).withConditions(VALID_CONDITION_BACK)
                .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same date of birth, same height, same weight, same address, different phone,
        // different email -> returns true
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, different name, different phone, different email -> returns true
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different patient -> returns false
        assertFalse(ALICE.equals(BOB));

        // different ic -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withIc(VALID_IC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date of birth -> returns false
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PatientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different height -> returns false
        editedAlice = new PatientBuilder(ALICE).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different weight -> returns false
        editedAlice = new PatientBuilder(ALICE).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different blood type -> returns false
        editedAlice = new PatientBuilder(ALICE).withBloodType(VALID_BLOOD_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different allergies -> returns false
        editedAlice = new PatientBuilder(ALICE).withAllergies(VALID_ALLERGY_PENICILLIN).build();
        assertFalse(ALICE.equals(editedAlice));

        // different conditions -> returns false
        editedAlice = new PatientBuilder(ALICE).withConditions(VALID_CONDITION_DIABETES).build();
        assertFalse(ALICE.equals(editedAlice));

        // different treatments -> returns false
        editedAlice = new PatientBuilder(ALICE).withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different medical note list -> returns false
        editedAlice = new PatientBuilder(ALICE).build();
        editedAlice.addMedicalNote(BENSON_MEDICAL_NOTE_1);
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void alreadyHasMedicalNote_duplicatedNote_true() {
        Patient alice = new PatientBuilder(ALICE).build();
        assertTrue(alice.alreadyHasMedicalNote(ALICE_MEDICAL_NOTE_1));
        assertTrue(alice.alreadyHasMedicalNote(ALICE_MEDICAL_NOTE_2));
    }

    @Test
    public void alreadyHasMedicalNote_newNote_false() {
        Patient alice = new PatientBuilder(ALICE).build();
        assertFalse(alice.alreadyHasMedicalNote(BENSON_MEDICAL_NOTE_1));
        assertFalse(alice.alreadyHasMedicalNote(BENSON_MEDICAL_NOTE_2));
    }

    @Test
    public void getNumOfMedicalNotes() {
        Patient alice = new PatientBuilder(ALICE).build();
        assertTrue(alice.getNumOfMedicalNotes() == ALICE_NUM_OF_MEDICAL_NOTES);
    }
}
