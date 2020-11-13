package seedu.bagel.model.flashcard;

import static seedu.bagel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FlashcardSetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FlashcardSet(null));
    }

    @Test
    public void constructor_invalidSetNumber_throwsIllegalArgumentException() {
        String invalidSetNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new FlashcardSet(invalidSetNumber));
    }

    @Test
    public void isValidSetNumber() {
        // null set number
        assertThrows(NullPointerException.class, () -> FlashcardSet.isValidSetNumber(null));
    }

}
