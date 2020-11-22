package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GenderMatchPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.studentgroup.StudentGroup;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_STUDENT_GROUP = "#basketball";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_STUDENT_GROUP_1 = "basketball";
    private static final String VALID_STUDENT_GROUP_2 = "dance";

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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
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
    public void parseStudentGroup_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentGroup(null));
    }

    @Test
    public void parseStudentGroup_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStudentGroup(INVALID_STUDENT_GROUP));
    }

    @Test
    public void parseStudentGroup_validValueWithoutWhitespace_returnsStudentGroup() throws Exception {
        StudentGroup expectedStudentGroup = new StudentGroup(VALID_STUDENT_GROUP_1);
        assertEquals(expectedStudentGroup, ParserUtil.parseStudentGroup(VALID_STUDENT_GROUP_1));
    }

    @Test
    public void parseStudentGroup_validValueWithWhitespace_returnsTrimmedStudentGroup() throws Exception {
        String studentGroupWithWhitespace = WHITESPACE + VALID_STUDENT_GROUP_1 + WHITESPACE;
        StudentGroup expectedStudentGroup = new StudentGroup(VALID_STUDENT_GROUP_1);
        assertEquals(expectedStudentGroup, ParserUtil.parseStudentGroup(studentGroupWithWhitespace));
    }

    @Test
    public void parseStudentGroups_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStudentGroups(null));
    }

    @Test
    public void parseStudentGroups_collectionWithInvalidStudentGroups_throwsParseException() {
        assertThrows(ParseException.class, () ->
            ParserUtil.parseStudentGroups(Arrays.asList(VALID_STUDENT_GROUP_1, INVALID_STUDENT_GROUP)));
    }

    @Test
    public void parseStudentGroups_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseStudentGroups(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseStudentGroups_collectionWithValidStudentGroups_returnsStudentGroupSet() throws Exception {
        Set<StudentGroup> actualStudentGroupSet = ParserUtil.parseStudentGroups(
            Arrays.asList(VALID_STUDENT_GROUP_1, VALID_STUDENT_GROUP_2));
        Set<StudentGroup> expectedStudentGroupSet = new HashSet<>(Arrays
                .asList(new StudentGroup(VALID_STUDENT_GROUP_1), new StudentGroup(VALID_STUDENT_GROUP_2)));

        assertEquals(expectedStudentGroupSet, actualStudentGroupSet);
    }

    @Test
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender(null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER_BOB);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER_BOB));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER_BOB + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER_BOB);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseGenderMatchPredicate_maleGender() throws Exception {
        GenderMatchPredicate expectedGenderMatchPredicate = new GenderMatchPredicate(new Gender(VALID_GENDER_BOB));
        assertEquals(ParserUtil.parseGenderMatchPredicate(VALID_GENDER_BOB), expectedGenderMatchPredicate);
    }


    @Test
    public void parseGenderMatchPredicate_femaleGender() throws Exception {
        GenderMatchPredicate expectedGenderMatchPredicate = new GenderMatchPredicate(new Gender(VALID_GENDER_AMY));
        assertEquals(ParserUtil.parseGenderMatchPredicate(VALID_GENDER_AMY), expectedGenderMatchPredicate);
    }
}
