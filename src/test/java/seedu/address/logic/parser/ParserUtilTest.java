package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_CODE = "CS23!3";
    private static final String INVALID_CREDITS = "4!";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_CODE = "CS1231";
    private static final String VALID_CREDITS = "4";
    private static final String VALID_TAG_1 = "core";
    private static final String VALID_TAG_2 = "nonCore";
    private static final String VALID_LOWERCASE_TAG = "foundation";
    private static final String VALID_UPPERCASE_TAG = "FOUNDATION";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCode((String) null));
    }

    @Test
    public void parseCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(INVALID_CODE));
    }

    @Test
    public void parseCode_validValueWithoutWhitespace_returnsCode() throws Exception {
        ModuleCode expectedCode = new ModuleCode(VALID_CODE);
        assertEquals(expectedCode, ParserUtil.parseModuleCode(VALID_CODE));
    }

    @Test
    public void parseCode_validValueWithWhitespace_returnsTrimmedModuleCode() throws Exception {
        String moduleCodeWithWhitespace = WHITESPACE + VALID_CODE + WHITESPACE;
        ModuleCode expectedCode = new ModuleCode(VALID_CODE);
        assertEquals(expectedCode, ParserUtil.parseModuleCode(moduleCodeWithWhitespace));
    }

    @Test
    public void parseCredits_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModularCredits((String) null));
    }

    @Test
    public void parseCredits_validValueWithoutWhitespace_returnCredits() throws Exception {
        ModularCredits expectedCredits = new ModularCredits(VALID_CREDITS);
        assertEquals(expectedCredits, ParserUtil.parseModularCredits(VALID_CREDITS));
    }

    @Test
    public void parseCredits_validValueWithWhitespace_returnsTrimmedCredits() throws Exception {
        String creditsWithWhitespace = WHITESPACE + VALID_CREDITS + WHITESPACE;
        ModularCredits expectedCredits = new ModularCredits(VALID_CREDITS);
        assertEquals(expectedCredits, ParserUtil.parseModularCredits(creditsWithWhitespace));
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
    public void parseTags_collectionWithDuplicateLettercaseTags_tagSetOnlyIncludesFirst() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_LOWERCASE_TAG, VALID_UPPERCASE_TAG));
        Set<Tag> expectedTagSet = new HashSet<>(Collections.singletonList(new Tag(VALID_LOWERCASE_TAG)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
