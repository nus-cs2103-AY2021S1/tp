package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OpeningHoursTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OpeningHours(null));
    }

    @Test
    public void constructor_invalidOpeningHours_throwsIllegalArgumentException() {
        String invalidOpeningHours = "4-8";
        assertThrows(IllegalArgumentException.class, () -> new OpeningHours(invalidOpeningHours));
    }

    @Test
    public void isValidOpeningHours() {
        // null openingHours
        assertThrows(NullPointerException.class, () -> OpeningHours.isValidOpeningHours(null));

        // invalid openingHours
        assertFalse(OpeningHours.isValidOpeningHours(" ")); // spaces only

        // missing parts
        assertFalse(OpeningHours.isValidOpeningHours("-2359")); // missing opening time
        assertFalse(OpeningHours.isValidOpeningHours("00002359")); // missing '-' symbol
        assertFalse(OpeningHours.isValidOpeningHours("0000-")); // missing closing time

        // invalid parts
        assertFalse(OpeningHours.isValidOpeningHours("7am-2359")); // invalid opening time
        assertFalse(OpeningHours.isValidOpeningHours("333-2359")); // invalid opening time
        assertFalse(OpeningHours.isValidOpeningHours("3333-2359")); // invalid opening time
        assertFalse(OpeningHours.isValidOpeningHours("1200-11pm")); // invalid closing time
        assertFalse(OpeningHours.isValidOpeningHours("1200-240")); // invalid closing time
        assertFalse(OpeningHours.isValidOpeningHours("1200-2400")); // invalid closing time
        assertFalse(OpeningHours.isValidOpeningHours("1.30-2359")); // dot in opening time
        assertFalse(OpeningHours.isValidOpeningHours("1330-11.59")); // dot in closing time
        assertFalse(OpeningHours.isValidOpeningHours("13 30-2359")); // spaces in opening time
        assertFalse(OpeningHours.isValidOpeningHours("1330- 23 59")); // spaces in closing time
        assertFalse(OpeningHours.isValidOpeningHours(" 1330-2359")); // leading space
        assertFalse(OpeningHours.isValidOpeningHours("1330-2359 ")); // trailing space
        assertFalse(OpeningHours.isValidOpeningHours("1330--2359")); // double '-' symbol
        assertFalse(OpeningHours.isValidOpeningHours("13-30-2359")); // '-' symbol in opening time
        assertFalse(OpeningHours.isValidOpeningHours("1330-23-59")); // '-' symbol in closing time

        // valid email
        assertTrue(OpeningHours.isValidOpeningHours("")); // empty string
        assertTrue(OpeningHours.isValidOpeningHours("0000-2359"));
        assertTrue(OpeningHours.isValidOpeningHours("2300-1000")); //opening time can be more than closing time

    }
}
