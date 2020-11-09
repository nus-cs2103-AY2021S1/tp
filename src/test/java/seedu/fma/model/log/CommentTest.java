package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    void isValidComment_validComment_returnTrue() {
        // valid Comment
        assertTrue(Comment.isValidComment("abc")); // minimal
        assertTrue(Comment.isValidComment("exercising")); // alphabets only
        assertTrue(Comment.isValidComment("testexercise123")); // alphabets + numbers only
        assertTrue(Comment.isValidComment("!#$%&'*+/=?`{|}~^.-@exercise.org")); // extensive use of special characters
        assertTrue(Comment.isValidComment("a1+be!@exercisemanz.com")); // mixture of alphanumeric and special characters
        assertTrue(Comment.isValidComment("peter_jack@very-very-very-long-exercise")); // long comment
    }

    @Test
    void testEquals() {
        Comment commentA = new Comment("comment A");
        Comment commentB = new Comment("comment A");
        Comment commentC = new Comment("comment C");

        // same object -> return true
        assertTrue(commentA.equals(commentA));

        // different values -> return false
        assertFalse(commentA.equals(commentC));

        // different object, same values -> return true
        assertTrue(commentA.equals(commentB));

        // null -> returns false
        assertFalse(commentA.equals(null));
    }

    @Test
    void testHashCode() {
        Comment commentA = new Comment("comment A");
        assertTrue(commentA.hashCode() == commentA.hashCode());
    }
}
