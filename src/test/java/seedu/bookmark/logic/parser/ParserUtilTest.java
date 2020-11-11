package seedu.bookmark.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "a".repeat(Name.MAX_NAME_LENGTH + 1);
    private static final String INVALID_GENRE = "F@ACTS0nLY";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TOTAL_PAGES = "-500";
    private static final String INVALID_BOOKMARK = "-100";
    private static final String INVALID_NOTE_TITLE = " ";
    private static final String INVALID_NOTE_TEXT = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_GENRE = "Fiction";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_TOTAL_PAGES = "500";
    private static final String VALID_BOOKMARK = "1";
    private static final String VALID_NOTE_TITLE = "Hello!";
    private static final String VALID_NOTE_TEXT = "World!";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_BOOK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_BOOK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseGenre_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGenre((String) null));
    }

    @Test
    public void parseGenre_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGenre(INVALID_GENRE));
    }

    @Test
    public void parseGenre_validValueWithoutWhitespace_returnsGenre() throws Exception {
        Genre expectedGenre = new Genre(VALID_GENRE);
        assertEquals(expectedGenre, ParserUtil.parseGenre(VALID_GENRE));
    }

    @Test
    public void parseGenre_validValueWithWhitespace_returnsTrimmedGenre() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_GENRE + WHITESPACE;
        Genre expectedGenre = new Genre(VALID_GENRE);
        assertEquals(expectedGenre, ParserUtil.parseGenre(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTotalPages_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTotalPages((String) null));
    }

    @Test
    public void parseTotalPages_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTotalPages(INVALID_TOTAL_PAGES));
    }

    @Test
    public void parseTotalPages_validValueWithoutWhitespace_returnsTotalPages() throws Exception {
        TotalPages expectedTotalPages = new TotalPages(VALID_TOTAL_PAGES);
        assertEquals(expectedTotalPages, ParserUtil.parseTotalPages(VALID_TOTAL_PAGES));
    }

    @Test
    public void parseTotalPages_validValueWithWhitespace_returnsTrimmedTotalPages() throws Exception {
        String totalPagesWithWhitespace = WHITESPACE + VALID_TOTAL_PAGES + WHITESPACE;
        TotalPages expectedTotalPages = new TotalPages(VALID_TOTAL_PAGES);
        assertEquals(expectedTotalPages, ParserUtil.parseTotalPages(totalPagesWithWhitespace));
    }

    @Test
    public void parseBookmark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBookmark((Optional<String>) null));
    }

    @Test
    public void parseBookmark_invalidValueNegativeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBookmark(Optional.of(INVALID_BOOKMARK)));
    }

    @Test
    public void parseBookmark_validValueWithoutWhitespace_returnsBookmark() throws Exception {
        Bookmark expectedBookmark = new Bookmark(VALID_BOOKMARK);
        assertEquals(expectedBookmark, ParserUtil.parseBookmark(Optional.of(VALID_BOOKMARK)));
    }

    @Test
    public void parseBookmark_validValueWithWhitespace_returnsTrimmedBookmark() throws Exception {
        String bookmarkWithWhitespace = WHITESPACE + VALID_BOOKMARK + WHITESPACE;
        Bookmark expectedBookmark = new Bookmark(VALID_BOOKMARK);
        assertEquals(expectedBookmark, ParserUtil.parseBookmark(Optional.of(bookmarkWithWhitespace)));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote(null, null));
    }

    @Test
    public void parseNote_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE_TITLE, INVALID_NOTE_TEXT));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE_TITLE, VALID_NOTE_TEXT);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE_TITLE, VALID_NOTE_TEXT));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteTitleWithWhitespace = WHITESPACE + VALID_NOTE_TITLE + WHITESPACE;
        String noteTextWithWhitespace = WHITESPACE + VALID_NOTE_TEXT + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE_TITLE, VALID_NOTE_TEXT);
        assertEquals(expectedNote, ParserUtil.parseNote(noteTitleWithWhitespace, noteTextWithWhitespace));
    }
}
