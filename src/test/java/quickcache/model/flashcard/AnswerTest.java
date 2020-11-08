package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(Answer.isValidAnswer("Photosynthesis"));
        assertTrue(Answer.isValidAnswer("-")); // one character
        assertTrue(Answer.isValidAnswer("Cook for a further minute if you "
            + "like your soft boiled eggs a little firmer.")); // long address
    }

    @Test
    public void checkAnswer() {
        // example answers
        Answer answer1 = new Answer("Photosynthesis is good");
        Answer answer2 = new Answer("Photosynthesis is good");
        Answer answerLowerCase = new Answer("photosynthesis is good");

        // same object
        assertTrue(answer1.checkAnswerIgnoreCase(answer1));

        // same answer
        assertTrue(answer1.checkAnswer(answer2));

        // different cased answer
        assertFalse(answer1.checkAnswer(answerLowerCase));
    }

    @Test
    public void checkAnswerIgnoreCase() {
        // example answers
        Answer answer1 = new Answer("Photosynthesis is good");
        Answer answer2 = new Answer("Photosynthesis is good");
        Answer answerLowerCase = new Answer("photosynthesis is good");

        // same object
        assertTrue(answer1.checkAnswerIgnoreCase(answer1));

        // same answer
        assertTrue(answer1.checkAnswerIgnoreCase(answer2));

        // different cased answer
        assertTrue(answer1.checkAnswerIgnoreCase(answerLowerCase));
    }

    @Test
    public void equals() {
        Answer answer1 = new Answer("Photosynthesis is good");
        // null -> returns false
        assertFalse(answer1.equals(null));
    }

}
