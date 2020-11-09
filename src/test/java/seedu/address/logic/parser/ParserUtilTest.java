package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITY = "urgent";
    private static final String INVALID_EXPERIENCE = "-3";
    private static final String INVALID_URL = "linkedin";
    private static final String INVALID_SALARY = "-2000";
    private static final String INVALID_DATE = "2 Dec 2121";
    private static final String INVALID_BLACKLIST_STATUS = "no";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_PRIORITY = "high";
    private static final String VALID_MODERATE_PRIORITY = "moderate";
    private static final String VALID_EXPERIENCE = "3";
    private static final String VALID_URL_LINK = "linkedin.com";
    private static final String VALID_SALARY = "13000";
    private static final String VALID_DATE = "08-08-19";
    private static final String VALID_BLACKLIST_STATUS = "true";

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
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parsePriorityString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriorityString((String) null));
    }

    @Test
    public void parsePriorityString_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriorityString(INVALID_PRIORITY));
    }

    @Test
    public void parsePriorityString_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriorityString(VALID_PRIORITY));
    }

    @Test
    public void parsePriorityString_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriorityString(priorityWithWhitespace));
    }

    @Test
    public void parsePriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriority(null));
    }

    @Test
    public void parsePriority_listWithInvalidPriority_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(Arrays.asList(INVALID_PRIORITY)));
    }

    @Test
    public void parsePriority_emptyList_returnsModeratePriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_MODERATE_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(Collections.emptyList()));
    }

    @Test
    public void parsePriority_listWithValidPriority_returnsPriority() throws Exception {
        Priority actualPriority = ParserUtil.parsePriority(Arrays.asList(VALID_PRIORITY));
        Priority expectedPriority = new Priority(VALID_PRIORITY);

        assertEquals(expectedPriority, actualPriority);
    }

    @Test
    public void parseExperience_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExperience((String) null));
    }

    @Test
    public void parseExperience_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseExperience(INVALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithoutWhitespace_returnsExperience() throws Exception {
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(VALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String experienceWithWhitespace = WHITESPACE + VALID_EXPERIENCE + WHITESPACE;
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(experienceWithWhitespace));
    }

    @Test
    public void parseUrlLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseUrlLink((String) null));
    }

    @Test
    public void parseUrlLink_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseUrlLink(INVALID_URL));
    }

    @Test
    public void parseUrlLink_validValueWithoutWhitespace_returnsExperience() throws Exception {
        UrlLink expectedUrlLink = new UrlLink(VALID_URL_LINK);
        assertEquals(expectedUrlLink, ParserUtil.parseUrlLink(VALID_URL_LINK));
    }

    @Test
    public void parseUrlLink_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String urlWithWhitespace = WHITESPACE + VALID_URL_LINK + WHITESPACE;
        UrlLink expectedUrlLink = new UrlLink(VALID_URL_LINK);
        assertEquals(expectedUrlLink, ParserUtil.parseUrlLink(urlWithWhitespace));
    }

    @Test
    public void parseSalary_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSalary(((String) null)));
    }

    @Test
    public void parseSalary_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseSalary(INVALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithoutWhitespace_returnsExperience() throws Exception {
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(VALID_SALARY));
    }

    @Test
    public void parseSalary_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String salaryWithWhitespace = WHITESPACE + VALID_SALARY + WHITESPACE;
        Salary expectedSalary = new Salary(VALID_SALARY);
        assertEquals(expectedSalary, ParserUtil.parseSalary(salaryWithWhitespace));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(((String) null)));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsExperience() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseBlacklistStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBlacklistStatus(((String) null)));
    }

    @Test
    public void parseBlacklistStatus_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseBlacklistStatus(INVALID_BLACKLIST_STATUS));
    }

    @Test
    public void parseBlacklistStatus_validValueWithoutWhitespace_returnsExperience() throws Exception {
        BlacklistStatus expectedBlacklistStatus = new BlacklistStatus(VALID_BLACKLIST_STATUS);
        assertEquals(expectedBlacklistStatus, ParserUtil.parseBlacklistStatus(VALID_BLACKLIST_STATUS));
    }

    @Test
    public void parseBlacklistStatus_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String blacklistStatusWithWhitespace = WHITESPACE + VALID_BLACKLIST_STATUS + WHITESPACE;
        BlacklistStatus expectedBlacklistStatus = new BlacklistStatus(VALID_BLACKLIST_STATUS);
        assertEquals(expectedBlacklistStatus, ParserUtil.parseBlacklistStatus(blacklistStatusWithWhitespace));
    }
}
