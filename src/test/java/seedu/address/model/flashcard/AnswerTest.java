package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AnswerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Answer(null));
    }

    @Test
    public void constructor_invalidAnswer_throwsIllegalArgumentException() {
        String invalidAnswer = "";
        assertThrows(IllegalArgumentException.class, () -> new Answer(invalidAnswer));
    }

    @Test
    public void isValidAnswer() {
        // null answer
        assertThrows(NullPointerException.class, () -> Answer.isValidAnswer(null));

        // invalid answers
        assertFalse(Answer.isValidAnswer("")); // empty string
        assertFalse(Answer.isValidAnswer(" ")); // spaces only

        // valid answers
        assertTrue(Answer.isValidAnswer("Object-oriented programming"));
        assertTrue(Answer.isValidAnswer("-")); // one character
        assertTrue(Answer.isValidAnswer("Object-oriented programming (OOP) is a computer programming model that "
                + "organizes software design around data, or objects, rather than functions and logic."));
    }

}
