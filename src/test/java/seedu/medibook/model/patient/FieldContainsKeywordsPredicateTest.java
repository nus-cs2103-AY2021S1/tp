package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.medibook.testutil.PatientBuilder;

public class FieldContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("first", "second");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second", "third");

        FieldContainsKeywordsPredicate firstPredicate =
                new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        FieldContainsKeywordsPredicate secondPredicate =
                new FieldContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_IC);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same keywords, prefix -> returns true
        FieldContainsKeywordsPredicate firstPredicateCopy =
                new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords, prefix -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different keywords, same prefix -> returns false
        FieldContainsKeywordsPredicate thirdPredicate =
                new FieldContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_NAME);
        assertFalse(firstPredicate.equals(thirdPredicate));

        // same keywords, different prefix -> returns false
        firstPredicateCopy = new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_DATE);
        assertFalse(firstPredicate.equals(firstPredicateCopy));

        // same keywords, different prefix (test with different prefix) -> returns false
        firstPredicateCopy = new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_PHONE);
        assertFalse(firstPredicate.equals(firstPredicateCopy));
    }

    @Test
    public void test_containsOneKeyword_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(Collections.singletonList("Alice"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("S9123456Z"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9123456Z").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("08-09-2010"), PREFIX_DATE);
        assertTrue(predicate.test(new PatientBuilder().withDateOfBirth("08-09-2010").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("81234567"), PREFIX_PHONE);
        assertTrue(predicate.test(new PatientBuilder().withPhone("81234567").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("Clementi"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("cornelia@example.com"), PREFIX_EMAIL);
        assertTrue(predicate.test(new PatientBuilder().withEmail("cornelia@example.com").build()));

        // height field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("168"), PREFIX_HEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withHeight("168").build()));

        // weight field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("66.6"), PREFIX_WEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withWeight("66.6").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("AB+"), PREFIX_BLOOD_TYPE);
        assertTrue(predicate.test(new PatientBuilder().withBloodType("AB+").build()));
    }

    @Test
    public void test_containsKeywordAsSubstring_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(Collections.singletonList("lic"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("3456Z"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9123456Z").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("2010"), PREFIX_DATE);
        assertTrue(predicate.test(new PatientBuilder().withDateOfBirth("08-09-2010").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("8123"), PREFIX_PHONE);
        assertTrue(predicate.test(new PatientBuilder().withPhone("81234567").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("Clement"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("@example.com"), PREFIX_EMAIL);
        assertTrue(predicate.test(new PatientBuilder().withEmail("cornelia@example.com").build()));

        // height field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("16"), PREFIX_HEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withHeight("168").build()));

        // weight field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("6.6"), PREFIX_WEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withWeight("66.6").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("+"), PREFIX_BLOOD_TYPE);
        assertTrue(predicate.test(new PatientBuilder().withBloodType("AB+").build()));
    }

    @Test
    public void test_containsMultipleKeywords_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
            new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "Zach"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // address
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("311", "Street"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // no tests for other fields since the other fields are not separated by spaces
    }

    @Test
    public void test_matchesOneKeyword_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("S9123456Z", "S9876543C"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("15-03-2002", "08-09-2010"), PREFIX_DATE);
        assertTrue(predicate.test(new PatientBuilder().withDateOfBirth("15-03-2002").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("81234567", "66669876"), PREFIX_PHONE);
        assertTrue(predicate.test(new PatientBuilder().withPhone("66669876").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Clementi", "Hougang"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("cornelia@example.com", "johnd@example.com"),
                PREFIX_EMAIL);
        assertTrue(predicate.test(new PatientBuilder().withEmail("cornelia@example.com").build()));

        // height field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("168", "177"), PREFIX_HEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withHeight("177").build()));

        // weight field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("66.6", "80.4"), PREFIX_WEIGHT);
        assertTrue(predicate.test(new PatientBuilder().withWeight("66.6").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("B+", "AB+"), PREFIX_BLOOD_TYPE);
        assertTrue(predicate.test(new PatientBuilder().withBloodType("B+").build()));
    }

    @Test
    public void test_mixedCaseKeywords_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                predicate = new FieldContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("s9876543c"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("clemENTI"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("CoRnElIa@ExAmPlE.cOm"),
                PREFIX_EMAIL);
        assertTrue(predicate.test(new PatientBuilder().withEmail("cornelia@example.com").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("b+"), PREFIX_BLOOD_TYPE);
        assertTrue(predicate.test(new PatientBuilder().withBloodType("B+").build()));

        // no tests for dob, phone, height, weight fields since these fields are numeric.
    }

    @Test
    public void test_zeroKeywords_returnsFalse() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_DATE);
        assertFalse(predicate.test(new PatientBuilder().withDateOfBirth("15-03-2002").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withPhone("66669876").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_ADDRESS);
        assertFalse(predicate.test(new PatientBuilder().withAddress("311, Clementi Ave 2, #02-25").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(),
                PREFIX_EMAIL);
        assertFalse(predicate.test(new PatientBuilder().withEmail("cornelia@example.com").build()));

        // height field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_HEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withHeight("177").build()));

        // weight field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_WEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withWeight("66.6").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_BLOOD_TYPE);
        assertFalse(predicate.test(new PatientBuilder().withBloodType("B+").build()));
    }

    @Test
    public void test_nonMatchingKeywords_returnsFalse() {
        // Non-matching keyword
        FieldContainsKeywordsPredicate predicate =
            predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Carol"), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("S9123456Z", "S9876543C"), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S8642024G").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("15-03-2002", "08-09-2010"), PREFIX_DATE);
        assertFalse(predicate.test(new PatientBuilder().withDateOfBirth("13-04-1985").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("81234567", "66669876"), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withPhone("94210412").build()));

        // address field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Orchard", "Bedok"), PREFIX_ADDRESS);
        assertFalse(predicate.test(new PatientBuilder().withAddress("618, Newton Street 52, #01-21").build()));

        // email field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList(".edu", ".org"),
                PREFIX_EMAIL);
        assertFalse(predicate.test(new PatientBuilder().withEmail("test@foobar.com").build()));

        // height field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("159", "169"), PREFIX_HEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withHeight("172").build()));

        // weight field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("77.7", "53.2"), PREFIX_WEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withWeight("68.9").build()));

        // blood type field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("A-", "B-"), PREFIX_BLOOD_TYPE);
        assertFalse(predicate.test(new PatientBuilder().withBloodType("A+").build()));
    }

    @Test
    public void test_keywordMatchesOtherFields_returnsFalse() {
        // keywords match all fields except name
        FieldContainsKeywordsPredicate predicate =
            new FieldContainsKeywordsPredicate(Arrays.asList("S9999999R", "1234567", "alice@email.com", "Main",
                    "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except ic
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "1234567", "alice@email.com", "Main",
                        "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except date of birth
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "1234567", "alice@email.com",
                        "Main", "Street", "171", "61.2", "A+"), PREFIX_DATE);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except phone
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "alice@email.com", "Main",
                        "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except address
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "1234567", "alice@email.com",
                        "171", "61.2", "A+", "12-08-1999"), PREFIX_ADDRESS);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except email
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alicia", "S9999999R", "1234567",
                        "Main", "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_EMAIL);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alicia").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except height
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "1234567", "alice@email.com",
                        "Main", "Street", "61.2", "A+", "12-08-1999"), PREFIX_HEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except weight
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "1234567", "alice@email.com",
                        "Main", "Street", "171", "A+", "12-08-1999"), PREFIX_WEIGHT);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except blood type
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "1234567", "alice@email.com",
                        "Main", "Street", "171", "61.2", "12-08-1999"), PREFIX_BLOOD_TYPE);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("1234567")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));
    }
}
