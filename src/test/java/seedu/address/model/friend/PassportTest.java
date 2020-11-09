package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PassportTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Passport(null));
    }

    @Test
    public void constructor_invalidPassport_throwsIllegalArgumentException() {
        String invalidPassport = "";
        assertThrows(IllegalArgumentException.class, () -> new Passport(invalidPassport));
    }

    @Test
    void isValidPassport() {
        assertThrows(NullPointerException.class, () -> Passport.isValidPassport(null));

        // invalid passport numbers
        assertFalse(Passport.isValidPassport("")); // empty string
        assertFalse(Passport.isValidPassport(" ")); // spaces only
        assertFalse(Passport.isValidPassport("S9138")); // less than 7 digits
        assertFalse(Passport.isValidPassport("passport")); // too many character and absent numbers
        assertFalse(Passport.isValidPassport("S9011p04")); // alphabets within digits
        assertFalse(Passport.isValidPassport("S312 1534")); // spaces within passport number
        assertFalse(Passport.isValidPassport("S124293842033123")); // more than 7 digits
        assertFalse(Passport.isValidPassport("1242A93842033123")); //character is not at the start

        // valid passport numbers
        assertTrue(Passport.isValidPassport("E6249932A")); // [E] + 7 numbers + [A-Z]
        assertTrue(Passport.isValidPassport("E3121534B"));

    }
}
