package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class CasePersonTest {
    private static final Name DEFAULT_NAME = new Name("Test Name");
    private static final Phone DEFAULT_PHONE = new Phone("91234567");
    private static final Address DEFAULT_ADDRESS = new Address("Blk 123");
    private static final Email DEFAULT_EMAIL = new Email("abc@kmail.com");
    private static final Gender DEFAULT_GENDER = Gender.M;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(null, null, null, null, null));
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(null, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(DEFAULT_NAME, null, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(DEFAULT_NAME, DEFAULT_GENDER, null, DEFAULT_EMAIL, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, null, DEFAULT_ADDRESS));
        assertThrows(NullPointerException.class, () ->
                new CasePersonStub(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, null));
    }

    @Test
    public void testGetter() {
        CasePerson person =
                new CasePersonStub(DEFAULT_NAME, DEFAULT_GENDER, DEFAULT_PHONE, DEFAULT_EMAIL, DEFAULT_ADDRESS);
        assertEquals(DEFAULT_NAME, person.getName());
        assertEquals(DEFAULT_GENDER, person.getGender());
        assertEquals(DEFAULT_PHONE, person.getPhone());
        assertEquals(DEFAULT_EMAIL, person.getEmail());
        assertEquals(DEFAULT_ADDRESS, person.getAddress());
    }

    private class CasePersonStub extends CasePerson {

        /**
         * Every field must be present and not null.
         *
         * @param name
         * @param gender
         * @param phone
         * @param email
         * @param address
         */
        public CasePersonStub(Name name, Gender gender, Phone phone, Email email, Address address) {
            super(name, gender, phone, email, address);
        }
    }
}
