package seedu.flashcard.model.flashcard;

import static seedu.flashcard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

}
