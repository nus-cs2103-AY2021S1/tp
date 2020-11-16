package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.testutil.TypicalConsults.ALICE_PERSONAL_CONSULT;
import static seedu.address.testutil.TypicalConsults.BENSON_GROUP_CONSULT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ConsultationBuilder;

public class ConsultationTest {
    @Test
    public void isSameConsultation() {
        // same object -> returns true
        assertTrue(ALICE_PERSONAL_CONSULT.isSameConsultation(ALICE_PERSONAL_CONSULT));

        // null -> returns false
        assertFalse(ALICE_PERSONAL_CONSULT.isSameConsultation(null));

        // different time and date -> returns false
        Consultation editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withDay(VALID_ATTENDANCE_BOB)
                .withTime(VALID_TIME_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isSameConsultation(editedConsult));

        // different name & date -> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withName(VALID_NAME_BOB)
                .withDay(VALID_ATTENDANCE_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isSameConsultation(editedConsult));

        // same name, same day, same type (GROUP)-> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withName(VALID_NAME_BOB)
                .withDay(VALID_ATTENDANCE_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isSameConsultation(editedConsult));

        // same name, same time, same day, different attributes -> returns true
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withLocation(VALID_LOCATION_BOB)
                .withType(VALID_TYPE_BOB).build();
        assertTrue(ALICE_PERSONAL_CONSULT.isSameConsultation(editedConsult));

        // same name, same time, same day, different attributes -> returns true
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withLocation(VALID_LOCATION_BOB)
                .withType(VALID_TYPE_BOB).build();
        assertTrue(ALICE_PERSONAL_CONSULT.isSameConsultation(editedConsult));

    }

    @Test
    public void isPersonalConsultationOnSameTiming() {
        // same object -> returns true
        assertTrue(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(ALICE_PERSONAL_CONSULT));

        // null -> returns false
        assertFalse(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(null));

        // different time and date -> returns false
        Consultation editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withDay(VALID_ATTENDANCE_BOB)
                .withTime(VALID_TIME_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(editedConsult));

        // different type & date -> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withDay(VALID_ATTENDANCE_BOB)
                .withType(VALID_TYPE_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(editedConsult));

        // same name, same day, same type (GROUP)-> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withName(VALID_NAME_BOB)
                .withDay(VALID_ATTENDANCE_BOB).build();
        assertFalse(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(editedConsult));

        // same type, same time, same day, different attributes -> returns true
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withName(VALID_NAME_BOB)
                .withLocation(VALID_LOCATION_BOB).build();
        assertTrue(ALICE_PERSONAL_CONSULT.isPersonalConsultationOnSameTiming(editedConsult));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Consultation personalCopy = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).build();
        assertEquals(personalCopy, ALICE_PERSONAL_CONSULT);

        // same object -> returns true
        assertEquals(ALICE_PERSONAL_CONSULT, ALICE_PERSONAL_CONSULT);
        assertEquals(ALICE_PERSONAL_CONSULT.hashCode(), ALICE_PERSONAL_CONSULT.hashCode());

        // null -> returns false
        assertNotEquals(ALICE_PERSONAL_CONSULT, null);

        // different type -> returns false
        assertNotEquals(ALICE_PERSONAL_CONSULT, 5);

        // different consultation -> returns false
        assertNotEquals(BENSON_GROUP_CONSULT, ALICE_PERSONAL_CONSULT);
        assertNotEquals(ALICE_PERSONAL_CONSULT.hashCode(), BENSON_GROUP_CONSULT.hashCode());

        // different name -> returns false
        Consultation editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withName(VALID_NAME_BOB).build();
        assertNotEquals(editedConsult, ALICE_PERSONAL_CONSULT);
        assertNotEquals(ALICE_PERSONAL_CONSULT.hashCode(), editedConsult.hashCode());

        // different day -> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withDay(VALID_ATTENDANCE_BOB).build();
        assertNotEquals(editedConsult, ALICE_PERSONAL_CONSULT);
        assertNotEquals(ALICE_PERSONAL_CONSULT.hashCode(), editedConsult.hashCode());

        // different time -> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withTime(VALID_TIME_BOB).build();
        assertNotEquals(editedConsult, ALICE_PERSONAL_CONSULT);
        assertNotEquals(ALICE_PERSONAL_CONSULT.hashCode(), editedConsult.hashCode());

        // different location -> returns false
        editedConsult = new ConsultationBuilder(ALICE_PERSONAL_CONSULT).withLocation(VALID_LOCATION_BOB).build();
        assertNotEquals(editedConsult, ALICE_PERSONAL_CONSULT);
        assertNotEquals(ALICE_PERSONAL_CONSULT.hashCode(), editedConsult.hashCode());
    }
}
