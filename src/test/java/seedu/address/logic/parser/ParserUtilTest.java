package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_ALPHAINDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_LOWERCASEINDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_MISSING_ARGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramAddress;
import seedu.address.model.tag.TagName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM_ADDRESS = " ";
    private static final String INVALID_TAGNAME = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "1234567";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TELEGRAM_ADDRESS = "rachel_walker";
    private static final String VALID_TAGNAME_1 = "friend";
    private static final String VALID_TAGNAME_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseAlphaIndex_invalidInput_throwsParseException() {
        // Wrong input type
        assertThrows(ParseException.class, MESSAGE_INVALID_ALPHAINDEX, () ->
                        ParserUtil.parseAlphaIndex(Long.toString(Integer.MAX_VALUE + 1)));

        // More than one input required
        assertThrows(ParseException.class, () -> ParserUtil.parseAlphaIndex("10 a"));
    }

    @Test
    public void parseTwoIndex_invalidInput_throwsParseException() {
        // Only a single input
        assertThrows(ParseException.class, MESSAGE_MISSING_ARGS, (() ->
                        ParserUtil.parseTwoIndex("a")));
    }

    @Test
    public void parseTwoIndex_validInput_success() throws Exception {
        // Two inputs
        assertEquals(2, ParserUtil.parseTwoIndex("1 a").length);

        // Leading and trailing whitespaces
        assertEquals(2, ParserUtil.parseTwoIndex("  1 a  ").length);
    }

    @Test
    public void parseAlphaIndex_outOfRangeInput_throwsParseException() {
        // More than one letter
        assertThrows(ParseException.class, MESSAGE_INVALID_ALPHAINDEX, (() ->
                        ParserUtil.parseAlphaIndex("aa")));

        // Input is not within the range of a to z
        assertThrows(ParseException.class, MESSAGE_INVALID_LOWERCASEINDEX, () ->
                        ParserUtil.parseAlphaIndex("A"));
    }

    @Test
    public void parseAlphaIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseAlphaIndex("a"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseAlphaIndex("  a  "));
    }

    @Test
    public void parsePersonName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parsePersonName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parsePersonName_validValueWithoutWhitespace_returnsName() throws Exception {
        PersonName expectedName = new PersonName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parsePersonName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        PersonName expectedName = new PersonName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTelegramAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTelegramAddress((String) null));
    }

    @Test
    public void parseTelegramAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegramAddress(INVALID_TELEGRAM_ADDRESS));
    }

    @Test
    public void parseTelegramAddress_validValueWithoutWhitespace_returnsTelegramAddress() throws Exception {
        TelegramAddress expectedTelegramAddress = new TelegramAddress(VALID_TELEGRAM_ADDRESS);
        assertEquals(expectedTelegramAddress, ParserUtil.parseTelegramAddress(VALID_TELEGRAM_ADDRESS));
    }

    @Test
    public void parseTelegramAddress_validValueWithWhitespace_returnsTrimmedTelegramAddress() throws Exception {
        String telegramAddressWithWhitespace = WHITESPACE + VALID_TELEGRAM_ADDRESS + WHITESPACE;
        TelegramAddress expectedTelegramAddress = new TelegramAddress(VALID_TELEGRAM_ADDRESS);
        assertEquals(expectedTelegramAddress, ParserUtil.parseTelegramAddress(telegramAddressWithWhitespace));
    }

    @Test
    public void parseTagName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagName(null));
    }

    @Test
    public void parseTagName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagName(INVALID_TAGNAME));
    }

    @Test
    public void parseTagName_validValueWithoutWhitespace_returnsTag() throws Exception {
        TagName expectedTagName = new TagName(VALID_TAGNAME_1);
        assertEquals(expectedTagName, ParserUtil.parseTagName(VALID_TAGNAME_1));
    }

    @Test
    public void parseTagName_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagNameWithWhitespace = WHITESPACE + VALID_TAGNAME_1 + WHITESPACE;
        TagName expectedTagName = new TagName(VALID_TAGNAME_1);
        assertEquals(expectedTagName, ParserUtil.parseTagName(tagNameWithWhitespace));
    }

    @Test
    public void parseTagNames_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagNames(null));
    }

    @Test
    public void parseTagNames_collectionWithInvalidTagNames_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTagNames(Arrays.asList(VALID_TAGNAME_1, INVALID_TAGNAME)));
    }

    @Test
    public void parseTagNames_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTagNames(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTagNames_collectionWithValidTagNames_returnsTagSet() throws Exception {
        Set<TagName> actualTagSet = ParserUtil.parseTagNames(Arrays.asList(VALID_TAGNAME_1, VALID_TAGNAME_2));
        Set<TagName> expectedTagSet = new HashSet<TagName>(
                Arrays.asList(new TagName(VALID_TAGNAME_1), new TagName(VALID_TAGNAME_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
