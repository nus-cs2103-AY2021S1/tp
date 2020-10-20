package seedu.flashcard.model.tag;

import static seedu.flashcard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    //    @Test
    //    public void isValidTagName() {
    //        // null tag name
    //        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    //    }
}
