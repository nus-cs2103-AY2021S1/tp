package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }


    @Test
    public void isValidComment_validComment_returnTrue() {
        // valid Comment
        assertTrue(Comment.isValidComment("abc")); // minimal
        assertTrue(Comment.isValidComment("exercising")); // alphabets only
        assertTrue(Comment.isValidComment("testexercise123")); // alphabets + numbers only
        assertTrue(Comment.isValidComment("!#$%&'*+/=?`{|}~^.-@exercise.org")); // extensive use of special characters
        assertTrue(Comment.isValidComment("a1+be!@exercisemanz.com")); // mixture of alphanumeric and special characters
        assertTrue(Comment.isValidComment("peter_jack@very-very-very-long-exercise")); // long comment
    }
}
