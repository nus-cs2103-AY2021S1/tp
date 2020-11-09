package seedu.address.model.patient;

import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.COMMENT;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_AGE_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PERIOD_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_TEMP_BOB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

//@@author chiamyunqing
/**
 * Test cases for patient.
 */
public class PatientTest {

    @Test
    public void isSamePatient() { //patient is same as long as they have same name
        // same object -> returns true
        Assertions.assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        Assertions.assertFalse(ALICE.isSamePatient(null));

        // different name -> false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(ALICE.isSamePatient(editedAlice));

        //different phone but other attributes same -> returns true
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertTrue(ALICE.isSamePatient(editedAlice));

        //different period of stay but other attributes same -> returns true
        editedAlice = new PatientBuilder(ALICE).withPeriodOfStay(VALID_PERIOD_BOB).build();
        Assertions.assertTrue(ALICE.isSamePatient(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withTemperature(VALID_TEMP_BOB)
                .withAge(VALID_AGE_BOB).withPeriodOfStay(VALID_PERIOD_BOB).build();
        Assertions.assertTrue(ALICE.isSamePatient(editedAlice));

        //differnet temperature but other attributes same -> returns true
        editedAlice = new PatientBuilder(ALICE).withTemperature(VALID_TEMP_BOB).build();
        Assertions.assertTrue(ALICE.isSamePatient(editedAlice));

        //diff comments but all attributes same -> returns true
        editedAlice = new PatientBuilder(ALICE).withComment(COMMENT).build();
        Assertions.assertTrue(ALICE.isSamePatient(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        Assertions.assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        Assertions.assertFalse(ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(ALICE.equals(5));

        // different patient -> returns false
        Assertions.assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different period of stay -> returns false
        editedAlice = new PatientBuilder(ALICE).withPeriodOfStay(VALID_PERIOD_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));

        // different age -> returns false
        editedAlice = new PatientBuilder(ALICE).withAge(VALID_AGE_BOB).build();
        Assertions.assertFalse(ALICE.equals(editedAlice));
    }
}
