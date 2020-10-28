package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.PolicyDescription;
import seedu.address.model.policy.PolicyName;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CLIENTSOURCE =
            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private static final String INVALID_NOTE = " ";
    private static final String INVALID_PRIORITY = "x";
    private static final String INVALID_POLICYNAME = "L!f3 T!me";
    private static final String INVALID_POLICYDESCRIPTION = " ";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_CLIENTSOURCE_1 = "friend from work";
    private static final String VALID_CLIENTSOURCE_2 = "neighbour";
    private static final String VALID_NOTE = "friend";
    private static final String VALID_SHORT_PRIORITY = "l";
    private static final String VALID_LONG_PRIORITY = "low";
    private static final String VALID_POLICYNAME = "Life Time";
    private static final String VALID_POLICYDESCRIPTION = "Covers death, serious illnesses, and disabilities.";


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
    public void parsePhone_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parsePhone((String) null));
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
    public void parseAddress_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseAddress((String) null));
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
    public void parseEmail_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseEmail((String) null));
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
    public void parseClientSource_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientSource(null));
    }

    @Test
    public void parseClientSource_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClientSource(INVALID_CLIENTSOURCE));
    }

    @Test
    public void parseClientSource_validValueWithoutWhitespace_returnsClientSource() throws Exception {
        ClientSource expectedClientSource = new ClientSource(VALID_CLIENTSOURCE_1);
        assertEquals(expectedClientSource, ParserUtil.parseClientSource(VALID_CLIENTSOURCE_1));
    }

    @Test
    public void parseClientSource_validValueWithWhitespace_returnsTrimmedClientSource() throws Exception {
        String clientSourceWithWhitespace = WHITESPACE + VALID_CLIENTSOURCE_1 + WHITESPACE;
        ClientSource expectedClientSource = new ClientSource(VALID_CLIENTSOURCE_1);
        assertEquals(expectedClientSource, ParserUtil.parseClientSource(clientSourceWithWhitespace));
    }

    @Test
    public void parseClientSources_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseClientSources(null));
    }

    @Test
    public void parseClientSources_collectionWithInvalidClientSources_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseClientSources(Arrays.asList(VALID_CLIENTSOURCE_1, INVALID_CLIENTSOURCE)));
    }

    @Test
    public void parseClientSources_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseClientSources(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseClientSources_collectionWithValidClientSources_returnsClientSourceSet() throws Exception {
        Set<ClientSource> actualClientSourceSet = ParserUtil
                .parseClientSources(Arrays.asList(VALID_CLIENTSOURCE_1, VALID_CLIENTSOURCE_2));
        Set<ClientSource> expectedClientSourceSet = new HashSet<ClientSource>(Arrays.asList(
                new ClientSource(VALID_CLIENTSOURCE_1), new ClientSource(VALID_CLIENTSOURCE_2)));

        assertEquals(expectedClientSourceSet, actualClientSourceSet);
    }

    @Test
    public void parseNote_null_returnsNull() throws Exception {
        assertNull(ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    // This does not give a null, but a priority made from null so it is undefined
    @Test
    public void parsePriority_null_returnsNullPriority() throws Exception {
        assertEquals(ParserUtil.parsePriority((String) null), new Priority(null));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_SHORT_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(VALID_SHORT_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_LONG_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_LONG_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(priorityWithWhitespace));
    }

    @Test
    public void parsePriority_validShortenedPriority_returnsFullPriority() throws Exception {
        String priorityShort = VALID_SHORT_PRIORITY;
        Priority expectedPriority = new Priority(VALID_LONG_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(priorityShort));
    }

    @Test
    public void parsePolicyName_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parsePolicyName(null));
    }

    @Test
    public void parsePolicyName_validValueWithWhitespace_returnsTrimmedPolicyName() throws Exception {
        String policyNameWithWhiteSpace = WHITESPACE + VALID_POLICYNAME + WHITESPACE;
        PolicyName expectedPolicyName = new PolicyName(VALID_POLICYNAME);
        assertEquals(expectedPolicyName, ParserUtil.parsePolicyName(policyNameWithWhiteSpace));
    }

    @Test
    public void parsePolicyName_validValueWithoutWhiteSpace_returnsPolicyName() throws Exception {
        String policyNameWithoutWhiteSpace = VALID_POLICYNAME;
        PolicyName expectedPolicyName = new PolicyName(VALID_POLICYNAME);
        assertEquals(expectedPolicyName, ParserUtil.parsePolicyName(policyNameWithoutWhiteSpace));
    }

    @Test
    public void parsePolicyName_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parsePolicyName(INVALID_POLICYNAME));
    }

    //
    @Test
    public void parsePolicyDescription_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parsePolicyDescription(null));
    }

    @Test
    public void parsePolicyDescription_validValueWithWhitespace_returnsTrimmedPolicyDescription() throws Exception {
        String policyDescriptionWithWhiteSpace = WHITESPACE + VALID_POLICYDESCRIPTION + WHITESPACE;
        PolicyDescription expectedPolicyDescription = new PolicyDescription(VALID_POLICYDESCRIPTION);
        assertEquals(expectedPolicyDescription, ParserUtil.parsePolicyDescription(policyDescriptionWithWhiteSpace));
    }

    @Test
    public void parsePolicyDescription_validValueWithoutWhiteSpace_returnsPolicyDescription() throws Exception {
        String policyDescriptionWithoutWhiteSpace = VALID_POLICYDESCRIPTION;
        PolicyDescription expectedPolicyDescription = new PolicyDescription(VALID_POLICYDESCRIPTION);
        assertEquals(expectedPolicyDescription, ParserUtil.parsePolicyDescription(policyDescriptionWithoutWhiteSpace));
    }

    @Test
    public void parsePolicyDescription_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parsePolicyDescription(INVALID_POLICYDESCRIPTION));
    }
}
