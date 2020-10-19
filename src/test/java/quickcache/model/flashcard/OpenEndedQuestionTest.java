package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class OpenEndedQuestionTest {

    @Test
    public void isValidQuestion() {
        // null choice
        assertThrows(NullPointerException.class, () -> OpenEndedQuestion.isValidQuestion(null));

        // blank choice
        assertFalse(OpenEndedQuestion.isValidQuestion(""));
        assertFalse(OpenEndedQuestion.isValidQuestion(" "));

        // missing parts
        assertTrue(OpenEndedQuestion.isValidQuestion("How much is the cost of 1 DVD?"));
        assertTrue(OpenEndedQuestion.isValidQuestion("How much is a DVD!")); // with punctuation
        assertTrue(OpenEndedQuestion.isValidQuestion("DVDs 12345")); // with numbers
    }

    @Test
    public void getChoices() {
        OpenEndedQuestion openEndedQuestion = new OpenEndedQuestion("Test question?",
                new Answer("Sample Answer"));
        assertTrue(openEndedQuestion.getChoices().isEmpty());
    }

}
