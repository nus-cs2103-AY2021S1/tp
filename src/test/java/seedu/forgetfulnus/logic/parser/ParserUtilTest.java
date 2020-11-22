package seedu.forgetfulnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.logic.parser.exceptions.ParseZeroException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_GERMAN_PHRASE = "Deutschl@nd";
    private static final String INVALID_ENGLISH_PHRASE = "+Germany";
    private static final String INVALID_TAG = "#country";

    private static final String VALID_GERMAN_PHRASE = "Deutschland";
    private static final String VALID_ENGLISH_PHRASE = "Germany";
    private static final String VALID_TAG_1 = "country";
    private static final String VALID_TAG_2 = "name";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_invalidInput_throwsParseZeroException() {
        assertThrows(ParseZeroException.class, () -> ParserUtil.parseIndex("0"));
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
    public void parseGermanPhrase_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGermanPhrase((String) null));
    }

    @Test
    public void parseGermanPhrase_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGermanPhrase(INVALID_GERMAN_PHRASE));
    }

    @Test
    public void parseGermanPhrase_validValueWithoutWhitespace_returnsGermanPhrase() throws Exception {
        GermanPhrase expectedGermanPhrase = new GermanPhrase(VALID_GERMAN_PHRASE);
        assertEquals(expectedGermanPhrase, ParserUtil.parseGermanPhrase(VALID_GERMAN_PHRASE));
    }

    @Test
    public void parseGermanPhrase_validValueWithWhitespace_returnsTrimmedGermanPhrase() throws Exception {
        String germanPhraseWithWhitespace = WHITESPACE + VALID_GERMAN_PHRASE + WHITESPACE;
        GermanPhrase expectedGermanPhrase = new GermanPhrase(VALID_GERMAN_PHRASE);
        assertEquals(expectedGermanPhrase, ParserUtil.parseGermanPhrase(germanPhraseWithWhitespace));
    }

    @Test
    public void parseEnglishPhrase_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEnglishPhrase((String) null));
    }

    @Test
    public void parseEnglishPhrase_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEnglishPhrase(INVALID_ENGLISH_PHRASE));
    }

    @Test
    public void parseEnglishPhrase_validValueWithoutWhitespace_returnsEnglishPhrase() throws Exception {
        EnglishPhrase expectedEnglishPhrase = new EnglishPhrase(VALID_ENGLISH_PHRASE);
        assertEquals(expectedEnglishPhrase, ParserUtil.parseEnglishPhrase(VALID_ENGLISH_PHRASE));
    }

    @Test
    public void parseEnglishPhrase_validValueWithWhitespace_returnsTrimmedEnglishPhrase() throws Exception {
        String englishPhraseWithWhitespace = WHITESPACE + VALID_ENGLISH_PHRASE + WHITESPACE;
        EnglishPhrase expectedEnglishPhrase = new EnglishPhrase(VALID_ENGLISH_PHRASE);
        assertEquals(expectedEnglishPhrase, ParserUtil.parseEnglishPhrase(englishPhraseWithWhitespace));
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
}
