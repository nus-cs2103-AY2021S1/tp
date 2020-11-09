package seedu.address.model.review;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RatingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    void isValidRating_inRange_success() {
        Assertions.assertTrue(Rating.isValidRating("5"));
        Assertions.assertTrue(Rating.isValidRating("2"));
        Assertions.assertTrue(Rating.isValidRating("0"));
    }

    @Test
    void isValidRating_outOfRange_failure() {
        Assertions.assertFalse(Rating.isValidRating("6"));
        Assertions.assertFalse(Rating.isValidRating("-1"));
        Assertions.assertFalse(Rating.isValidRating("100"));
    }

    @Test
    void isValidRating_wrongType_failure() {
        Assertions.assertFalse(Rating.isValidRating("a"));
        Assertions.assertFalse(Rating.isValidRating("\n"));
        Assertions.assertFalse(Rating.isValidRating("1.0"));
    }
}
