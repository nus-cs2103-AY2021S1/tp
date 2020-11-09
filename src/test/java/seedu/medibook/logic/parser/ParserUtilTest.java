package seedu.medibook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Treatment;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;

public class ParserUtilTest {
    private static final String INVALID_IC = "S99999w9Q";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DOB = "23/09/11";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HEIGHT = "18o";
    private static final String INVALID_WEIGHT = "20.15";
    private static final String INVALID_BLOOD_TYPE = "C+";
    private static final String INVALID_ALLERGY = "[];";
    private static final String INVALID_CONDITION = "*^£";
    private static final String INVALID_TREATMENT = "/+=";

    private static final String VALID_IC = "S9999999R";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_DOB = "13-04-1999";
    private static final String VALID_PHONE = "1234567";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_HEIGHT = "178";
    private static final String VALID_WEIGHT = "65.0";
    private static final String VALID_BLOOD_TYPE = "O+";
    private static final String VALID_ALLERGY_1 = "peanuts";
    private static final String VALID_ALLERGY_2 = "pollen";
    private static final String VALID_CONDITION_1 = "asthma";
    private static final String VALID_CONDITION_2 = "ear infection";
    private static final String VALID_TREATMENT_1 = "Ibuprofen";
    private static final String VALID_TREATMENT_2 = "Cephalexin (capsule)";

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
    public void parseIc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIc((String) null));
    }

    @Test
    public void parseIc_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIc(INVALID_IC));
    }

    @Test
    public void parseIc_validValueWithoutWhitespace_returnsIc() throws Exception {
        Ic expectedIc = new Ic(VALID_IC);
        assertEquals(expectedIc, ParserUtil.parseIc(VALID_IC));
    }

    @Test
    public void parseIc_validValueWithWhitespace_returnsTrimmedIc() throws Exception {
        String icWithWhitespace = WHITESPACE + VALID_IC + WHITESPACE;
        Ic expectedIc = new Ic(VALID_IC);
        assertEquals(expectedIc, ParserUtil.parseIc(icWithWhitespace));
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
    public void parseDateOfBirth_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateOfBirth((String) null));
    }

    @Test
    public void parseDateOfBirth_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateOfBirth(INVALID_DOB));
    }

    @Test
    public void parseDateOfBirth_validValueWithoutWhitespace_returnsName() throws Exception {
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DOB);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(VALID_DOB));
    }

    @Test
    public void parseDateOfBirth_validValueWithWhitespace_returnsTrimmedDateOfBirth() throws Exception {
        String dateOfBirthWithWhitespace = WHITESPACE + VALID_DOB + WHITESPACE;
        DateOfBirth expectedDateOfBirth = new DateOfBirth(VALID_DOB);
        assertEquals(expectedDateOfBirth, ParserUtil.parseDateOfBirth(dateOfBirthWithWhitespace));
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
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(Optional.of(INVALID_ADDRESS)));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(Optional.of(VALID_ADDRESS)).get());
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(Optional.of(addressWithWhitespace)).get());
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(Optional.of(INVALID_EMAIL)));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(Optional.of(VALID_EMAIL)).get());
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(Optional.of(emailWithWhitespace)).get());
    }

    @Test
    public void parseHeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight(Optional.of(INVALID_HEIGHT)));
    }

    @Test
    public void parseHeight_validValueWithoutWhitespace_returnsHeight() throws Exception {
        Height expectedHeight = new Height(VALID_HEIGHT);
        assertEquals(expectedHeight, ParserUtil.parseHeight(Optional.of(VALID_HEIGHT)).get());
    }

    @Test
    public void parseHeight_validValueWithWhitespace_returnsTrimmedHeight() throws Exception {
        String heightWithWhitespace = WHITESPACE + VALID_HEIGHT + WHITESPACE;
        Height expectedHeight = new Height(VALID_HEIGHT);
        assertEquals(expectedHeight, ParserUtil.parseHeight(Optional.of(heightWithWhitespace)).get());
    }

    @Test
    public void parseWeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(Optional.of(INVALID_WEIGHT)));
    }

    @Test
    public void parseWeight_validValueWithoutWhitespace_returnsWeight() throws Exception {
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(Optional.of(VALID_WEIGHT)).get());
    }

    @Test
    public void parseWeight_validValueWithWhitespace_returnsTrimmedWeight() throws Exception {
        String weightWithWhitespace = WHITESPACE + VALID_WEIGHT + WHITESPACE;
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(Optional.of(weightWithWhitespace)).get());
    }

    @Test
    public void parseBloodType_null_returnsEmptyOptional() throws Exception {
        Optional<BloodType> expectedBloodType = Optional.empty();
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(Optional.ofNullable(null)));
    }

    @Test
    public void parseBloodType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(Optional.of(INVALID_BLOOD_TYPE)));
    }

    @Test
    public void parseBloodType_validValueWithoutWhitespace_returnsBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOOD_TYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(Optional.of(VALID_BLOOD_TYPE)).get());
    }

    @Test
    public void parseBloodType_validValueWithWhitespace_returnsTrimmedBloodType() throws Exception {
        String bloodTypeWithWhitespace = WHITESPACE + VALID_BLOOD_TYPE + WHITESPACE;
        BloodType expectedBloodType = new BloodType(VALID_BLOOD_TYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(Optional.of(bloodTypeWithWhitespace)).get());
    }

    @Test
    public void parseAllergy_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergy(null));
    }

    @Test
    public void parseCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCondition(null));
    }

    @Test
    public void parseTreatment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTreatment(null));
    }

    @Test
    public void parseAllergy_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergy(INVALID_ALLERGY));
    }

    @Test
    public void parseCondition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCondition(INVALID_CONDITION));
    }

    @Test
    public void parseTreatment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTreatment(INVALID_TREATMENT));
    }

    @Test
    public void parseAllergy_validValueWithoutWhitespace_returnsAllergy() throws Exception {
        Allergy expectedAllergy = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedAllergy, ParserUtil.parseAllergy(VALID_ALLERGY_1));
    }

    @Test
    public void parseCondition_validValueWithoutWhitespace_returnsCondition() throws Exception {
        Condition expectedCondition = new Condition(VALID_CONDITION_1);
        assertEquals(expectedCondition, ParserUtil.parseCondition(VALID_CONDITION_1));
    }

    @Test
    public void parseTreatment_validValueWithoutWhitespace_returnsTreatment() throws Exception {
        Treatment expectedTreatment = new Treatment(VALID_TREATMENT_1);
        assertEquals(expectedTreatment, ParserUtil.parseTreatment(VALID_TREATMENT_1));
    }

    @Test
    public void parseAllergy_validValueWithWhitespace_returnsTrimmedAllergy() throws Exception {
        String allergyWithWhitespace = WHITESPACE + VALID_ALLERGY_1 + WHITESPACE;
        Allergy expectedTag = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedTag, ParserUtil.parseAllergy(allergyWithWhitespace));
    }

    @Test
    public void parseCondition_validValueWithWhitespace_returnsTrimmedCondition() throws Exception {
        String conditionWithWhitespace = WHITESPACE + VALID_CONDITION_1 + WHITESPACE;
        Condition expectedTag = new Condition(VALID_CONDITION_1);
        assertEquals(expectedTag, ParserUtil.parseCondition(conditionWithWhitespace));
    }

    @Test
    public void parseTreatment_validValueWithWhitespace_returnsTrimmedTreatment() throws Exception {
        String treatmentWithWhitespace = WHITESPACE + VALID_TREATMENT_1 + WHITESPACE;
        Treatment expectedTag = new Treatment(VALID_TREATMENT_1);
        assertEquals(expectedTag, ParserUtil.parseTreatment(treatmentWithWhitespace));
    }

    @Test
    public void parseAllergies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergies(null));
    }

    @Test
    public void parseConditions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseConditions(null));
    }

    @Test
    public void parseTreatments_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTreatments(null));
    }

    @Test
    public void parseAllergies_collectionWithInvalidAllergies_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergies(
            Arrays.asList(VALID_ALLERGY_1, INVALID_ALLERGY)));
    }

    @Test
    public void parseConditions_collectionWithInvalidConditions_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseConditions(
            Arrays.asList(VALID_CONDITION_1, INVALID_CONDITION)));
    }

    @Test
    public void parseTreatments_collectionWithInvalidTreatments_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTreatments(
                Arrays.asList(VALID_TREATMENT_1, INVALID_TREATMENT)));
    }

    @Test
    public void parseAllergies_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAllergies(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseConditions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseConditions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTreatments_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTreatments(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAllergies_collectionWithValidAllergies_returnsAllergySet() throws Exception {
        Set<Allergy> actualAllergySet = ParserUtil.parseAllergies(
            Arrays.asList(VALID_ALLERGY_1, VALID_ALLERGY_2));
        Set<Allergy> expectedAllergySet = new HashSet<Allergy>(
            Arrays.asList(new Allergy(VALID_ALLERGY_1), new Allergy(VALID_ALLERGY_2)));

        assertEquals(expectedAllergySet, actualAllergySet);
    }

    @Test
    public void parseConditions_collectionWithValidConditions_returnsConditionSet() throws Exception {
        Set<Condition> actualConditionSet = ParserUtil.parseConditions(
            Arrays.asList(VALID_CONDITION_1, VALID_CONDITION_2));
        Set<Condition> expectedConditionSet = new HashSet<Condition>(
            Arrays.asList(new Condition(VALID_CONDITION_1), new Condition(VALID_CONDITION_2)));

        assertEquals(expectedConditionSet, actualConditionSet);
    }

    @Test
    public void parseTreatments_collectionWithValidTreatments_returnsTreatmentSet() throws Exception {
        Set<Treatment> actualTreatmentSet = ParserUtil.parseTreatments(
                Arrays.asList(VALID_TREATMENT_1, VALID_TREATMENT_2));
        Set<Treatment> expectedTreatmentSet = new HashSet<Treatment>(
                Arrays.asList(new Treatment(VALID_TREATMENT_1), new Treatment(VALID_TREATMENT_2)));

        assertEquals(expectedTreatmentSet, actualTreatmentSet);
    }
}
