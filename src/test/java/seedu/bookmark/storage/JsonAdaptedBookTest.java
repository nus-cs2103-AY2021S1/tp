package seedu.bookmark.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.TO_KILL_A_MOCKINGBIRD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;

public class JsonAdaptedBookTest {
    private static final String INVALID_NAME = "a".repeat(Name.MAX_NAME_LENGTH + 1);
    private static final String INVALID_GENRE = "F@ACTS0nLY";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TO_KILL_A_MOCKINGBIRD.getName().toString();
    private static final String VALID_GENRE = TO_KILL_A_MOCKINGBIRD.getGenre().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TO_KILL_A_MOCKINGBIRD.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_TOTAL_PAGES = "190";
    private static final String VALID_BOOKMARK = "120";
    private static final String VALID_GOAL = "20 10-11-2029";
    private static final List<JsonAdaptedNote> VALID_NOTES =
            TO_KILL_A_MOCKINGBIRD.getNotes().stream()
                    .map(note -> new JsonAdaptedNote(note.title, note.text))
                    .collect(Collectors.toList());

    @Test
    public void toModelType_validBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(TO_KILL_A_MOCKINGBIRD);
        assertEquals(TO_KILL_A_MOCKINGBIRD, book.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(INVALID_NAME, VALID_GENRE, VALID_TAGS,
                        VALID_TOTAL_PAGES, VALID_BOOKMARK, VALID_GOAL, VALID_NOTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(null, VALID_GENRE, VALID_TAGS,
                VALID_TOTAL_PAGES, VALID_BOOKMARK, VALID_GOAL, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidGenre_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, INVALID_GENRE, VALID_TAGS,
                        VALID_TOTAL_PAGES, VALID_BOOKMARK, VALID_GOAL, VALID_NOTES);
        String expectedMessage = Genre.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullGenre_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, null, VALID_TAGS, VALID_TOTAL_PAGES,
                VALID_BOOKMARK, VALID_GOAL, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_GENRE, invalidTags,
                        VALID_TOTAL_PAGES, VALID_BOOKMARK, VALID_GOAL, VALID_NOTES);
        assertThrows(IllegalValueException.class, book::toModelType);
    }
}
