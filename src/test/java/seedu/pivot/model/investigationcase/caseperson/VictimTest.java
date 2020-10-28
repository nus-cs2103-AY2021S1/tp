package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class VictimTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@kmail.com");
    private static final Gender DEFAULT_GENDER = Gender.M;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Victim(null, null, null, null, null));
        assertThrows(NullPointerException.class, () ->
                new Victim(null, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new Victim(DEFAULT_NAME, null, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, null, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, null, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, null));
    }

    @Test
    public void equals() {
        Suspect suspect =
                new Suspect(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
        Victim victim =
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
        Witness witness =
                new Witness(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
        assertNotEquals(victim, witness);
        assertNotEquals(victim, suspect);

        // same values -> returns true
        assertTrue(victim.equals(
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS)));

        // same object -> returns true
        assertTrue(victim.equals(victim));

        // null -> returns false
        assertFalse(victim.equals(null));

        // different type -> returns false
        assertFalse(victim.equals(5));

        // different values -> returns false
        assertFalse(victim.equals(
                new Victim(new Name("Tommy"), DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS)));
        assertFalse(victim.equals(
                new Victim(DEFAULT_NAME, Gender.F, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS)));
        assertFalse(victim.equals(
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, new Phone("923"), DEFAULT_EMAIL, DEFAULT_ADDRESS)));
        assertFalse(victim.equals(new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE,
                new Email("Tommy@hello.com"), DEFAULT_ADDRESS)));
        assertFalse(victim.equals(
                new Victim(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL,
                        new Address("Blk 231231"))));
    }
}
