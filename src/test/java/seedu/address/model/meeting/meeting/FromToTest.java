package seedu.address.model.meeting.meeting;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FromToTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new To(null));
        assertThrows(NullPointerException.class, () -> new From(null));
    }

    @Test
    public void constructor_invalidFrom_throwsIllegalArgumentException() {
        String invalidFrom = "";
        assertThrows(IllegalArgumentException.class, () -> new To(invalidFrom));
        assertThrows(IllegalArgumentException.class, () -> new From(invalidFrom));
    }

    // isValidFrom and isValidTo is tested via DateTime test
}
