package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        /* Details on phone regex:
        *=========================================================
        * useful visualisation tool: https://www.regextester.com/
        * international standard: https://en.wikipedia.org/wiki/E.164, modified such that we don't allow any spaces
        * within the phone number field. Refer to the tests below on expected behaviour.
        * =========================================================*/

        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("65 81867742")); // country code not prepended with `+` char
        assertFalse(Phone.isValidPhone("+1234 81867752")); // more than 3 digits for country code
        assertFalse(Phone.isValidPhone("+ 81867752")); // invalid country code passed in
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhone("+65 9312 1534")); // spaces within the phone number
        assertFalse(Phone.isValidPhone("1234567891234567")); // exceeds 12 digits total
        assertFalse(Phone.isValidPhone("+65123456789123456")); // exceeds 15 digits total
        assertFalse(Phone.isValidPhone("+(65) 81867752")); // uses parenthesis, not allowed
        assertFalse(Phone.isValidPhone("+0 00")); // doesn't meet min 3 digits for phone number


        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("+6 3456")); // min country code is 1 digit
        assertTrue(Phone.isValidPhone("+65 81867752")); // bracketing and spacing
        assertTrue(Phone.isValidPhone("+6581867752"));
        assertTrue(Phone.isValidPhone("+6581867752")); // without any spacing
        assertTrue(Phone.isValidPhone("+123 81867752")); // max country code is 3 digits
        assertTrue(Phone.isValidPhone("+12381867752"));
        assertTrue(Phone.isValidPhone("+12381867752"));
        assertTrue(Phone.isValidPhone("81867752"));
        assertTrue(Phone.isValidPhone("123456789123")); // max length of 12 numbers if just phone num
        assertTrue(Phone.isValidPhone("+65 123456789123")); // max length of 15 numbers if countrycode + phone num

        // weird but valid numbers:
        assertTrue(Phone.isValidPhone("+65-81867752"));
        assertTrue(Phone.isValidPhone("+1.203493")); // delimited by `.`
        assertTrue(Phone.isValidPhone("+65 3456"));
        assertTrue(Phone.isValidPhone("+653456"));
    }

    @Test
    public void hashCode_test() {
        assertEquals(new Phone("911").hashCode(), new Phone("911").hashCode());
        assertEquals(new Phone("93121534").hashCode(), new Phone("93121534").hashCode());
        assertEquals(new Phone("293842033123").hashCode(), new Phone("293842033123").hashCode());

        assertNotEquals(new Phone("911").hashCode(), new Phone("912").hashCode());
        assertNotEquals(new Phone("911").hashCode(), new Phone("93121534").hashCode());
        assertNotEquals(new Phone("911").hashCode(), new Phone("12429384203").hashCode());
    }
}
