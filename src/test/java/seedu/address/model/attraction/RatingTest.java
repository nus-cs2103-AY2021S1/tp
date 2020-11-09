package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "5.1";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid ratings
        assertFalse(Rating.isValidRating("5.1"));
        assertFalse(Rating.isValidRating("0"));
        assertFalse(Rating.isValidRating(".5"));
        assertFalse(Rating.isValidRating("4.50"));
        assertFalse(Rating.isValidRating(" 0.5")); // leading space
        assertFalse(Rating.isValidRating("0.5 ")); // trailing space
        assertFalse(Rating.isValidRating(" ")); // spaces only

        // valid ratings
        assertTrue(Rating.isValidRating("0.0")); // lowest rating
        assertTrue(Rating.isValidRating("5.0")); // highest rating
        assertTrue(Rating.isValidRating("2.5"));
        assertTrue(Rating.isValidRating("")); // empty string
    }
}
