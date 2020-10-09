package seedu.address.model.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidString_throwsIllegalArgumentException() {
        String invalidString = " ";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidString));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid question
        assertFalse(Question.isValidQuestion(" "));
        assertFalse(Question.isValidQuestion(""));

        // valid questions
        assertTrue(Question.isValidQuestion("what is 1 + 1?"));
        assertTrue(Question.isValidQuestion("Hello"));
        assertTrue(Question.isValidQuestion("Explain Newton's Second Law"));
    }

    @Test
    public void toString_equals() {
        assertEquals(new Question("Hello").toString(), "[Hello]");
        assertEquals(new Question("1+1=?").toString(), "[1+1=?]");
    }
}
