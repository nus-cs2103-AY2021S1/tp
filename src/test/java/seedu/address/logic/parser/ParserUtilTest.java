package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

public class ParserUtilTest {
    private static final String INVALID_WORD = "";
    private static final String INVALID_TRANSLATION = "      ";

    private static final String VALID_WORD = "Rachel Walker";
    private static final String VALID_TRANSLATION = "rachel@example.com";

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
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseWord_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWord((String) null));
    }

    @Test
    public void parseWord_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWord(INVALID_WORD));
    }

    @Test
    public void parseWord_validValueWithoutWhitespace_returnsWord() throws Exception {
        Word expectedWord = new Word(VALID_WORD);
        assertEquals(expectedWord, ParserUtil.parseWord(VALID_WORD));
    }

    @Test
    public void parseWord_validValueWithWhitespace_returnsTrimmedWord() throws Exception {
        String wordWithWhitespace = WHITESPACE + VALID_WORD + WHITESPACE;
        Word expectedWord = new Word(VALID_WORD);
        assertEquals(expectedWord, ParserUtil.parseWord(wordWithWhitespace));
    }

    @Test
    public void parseTranslation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTranslation((String) null));
    }

    @Test
    public void parseTranslation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTranslation(INVALID_TRANSLATION));
    }

    @Test
    public void parseTranslation_validValueWithoutWhitespace_returnsTranslation() throws Exception {
        Translation expectedTranslation = new Translation(VALID_TRANSLATION);
        assertEquals(expectedTranslation, ParserUtil.parseTranslation(VALID_TRANSLATION));
    }

    @Test
    public void parseTranslation_validValueWithWhitespace_returnsTrimmedTranslation() throws Exception {
        String translationWithWhitespace = WHITESPACE + VALID_TRANSLATION + WHITESPACE;
        Translation expectedTranslation = new Translation(VALID_TRANSLATION);
        assertEquals(expectedTranslation, ParserUtil.parseTranslation(translationWithWhitespace));
    }
}
