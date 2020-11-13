package seedu.bagel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Title;
// import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_TITLE = "";
    private static final String INVALID_DESC = "";

    private static final String VALID_TITLE = "Rachel Walker";
    private static final String VALID_DESC = "123 Main Street #0505";
    // private static final String VALID_TAG_1 = "friend";
    // private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_FLASHCARD, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedName = new Title(VALID_TITLE);
        assertEquals(expectedName, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedName = new Title(VALID_TITLE);
        assertEquals(expectedName, ParserUtil.parseTitle(nameWithWhitespace));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_DESC));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedPhone = new Description(VALID_DESC);
        assertEquals(expectedPhone, ParserUtil.parseDescription(VALID_DESC));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_DESC + WHITESPACE;
        Description expectedPhone = new Description(VALID_DESC);
        assertEquals(expectedPhone, ParserUtil.parseDescription(phoneWithWhitespace));
    }

    // @Test
    // public void parseTag_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    // }

    // @Test
    // public void parseTag_invalidValue_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    // }

    // @Test
    // public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
    //     Tag expectedTag = new Tag(VALID_TAG_1);
    //     assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    // }

    // @Test
    // public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
    //     String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
    //     Tag expectedTag = new Tag(VALID_TAG_1);
    //     assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    // }

    // @Test
    // public void parseTags_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    // }

    // @Test
    // public void parseTags_collectionWithInvalidTags_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    // }

    // @Test
    // public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
    //     assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    // }

    // @Test
    // public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
    //     Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
    //     Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

    //     assertEquals(expectedTagSet, actualTagSet);
    // }
}
