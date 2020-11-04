package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;

public class DetailsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DetailsContainsKeywordsPredicate firstPredicate = new DetailsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        DetailsContainsKeywordsPredicate secondPredicate = new DetailsContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DetailsContainsKeywordsPredicate firstPredicateCopy = new DetailsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_caseContainsKeywords_returnsTrue() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Collections.singletonList("Bank"));
        // Multiple keywords, multiple fields
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank").withDescription("Robbery").build()));

        // Only one matching keyword, one field
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Bank", "Robbery"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank murder").build()));

        // Mixed-case keywords, one field
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("bAnK", "roBerRy"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").build()));

        // Mixed-case keywords, multiple fields
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("bAnK", "roBerRy"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank").withDescription("Robbery").build()));

        // Keywords that are a substring of details in case
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("bA", "rob"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank").withDescription("Robbery").build()));

    }

    @Test
    public void test_caseTitleContainsKeywords_returnsTrue() {
        // One keyword
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Collections.singletonList("Bank"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").build()));

        // Multiple keywords, one field
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Bank", "Robbery"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").build()));

        // Keyword is a substring of title
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("ba", "bery"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").build()));

    }

    @Test
    public void test_caseStatusContainsKeywords_returnsTrue() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Collections.singletonList("ACTIVE"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").withStatus("ACTIVE").build()));

        // Keyword is a substring of status
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("act"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery").withStatus("ACTIVE").build()));
    }

    @Test
    public void test_caseDescriptionContainsKeywords_returnsTrue() {
        // One keyword
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Collections.singletonList("Bank"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Robbery")
                .withDescription("Million dollars in XXX bank").build()));

        // Keyword is a substring of description
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("dollar"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Robbery")
                .withDescription("Million dollars in XXX bank").build()));
    }

    @Test
    public void test_caseDocumentsContainsKeywords_returnsTrue() {
        // Keywords match some fields in documents
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "evidence1.txt"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .withDocument("Evidence found at Scene", "evidence1.txt")
                .build()));

        // Keyword is a substring of document
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("evi", "txt"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .withDocument("Evidence found at Scene", "evidence1.txt")
                .build()));
    }

    @Test
    public void test_casePersonContainsKeywords_returnsTrue() {
        // Keywords match some fields in victims
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .addVictims(new CasePersonBuilder().withName("Janice").withSex("F")
                .withEmail("alice@email.com").withAddress("123 Main Street").buildVictim())
                .build()));

        // Keyword is substring of victim
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("alice"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .addVictims(new CasePersonBuilder().withName("Janice").withSex("F")
                .withEmail("alice@email.com").withAddress("123 Main Street").buildVictim())
                .build()));

        // Keywords match some fields in witness
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .addWitnesses(new CasePersonBuilder().withName("Janice").withSex("F")
                .withEmail("alice@email.com").withAddress("123 Main Street").buildWitness())
                .build()));

        // Keywords match some fields in suspect
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new CaseBuilder().withTitle("Bank Robbery")
                .addSuspects(new CasePersonBuilder().withName("Janice").withSex("F")
                .withEmail("alice@email.com").withAddress("123 Main Street").buildSuspect())
                .build()));
    }

    @Test
    public void test_caseDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CaseBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new DetailsContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CaseBuilder().withTitle("Alice Bob").build()));
        assertFalse(predicate.test(new CaseBuilder().withTitle("Alice Bob").withDescription("Riots")
                .withStatus("CLOSED").addVictims(
                        new CasePersonBuilder().withName("Janice").withSex("F").buildVictim())
                .build()));
    }

    //TODO: Test with more documents
    @Test
    public void test_getDocumentsInfo_returnsString() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        Case testCase = new CaseBuilder().withTitle("Bank Robbery")
                .withDocument("Evidence found at Scene", "evidence1.txt")
                .build();
        String expectedOutput = "Evidence found at Scene evidence1.txt ";
        assertEquals(predicate.getDocumentsInfo(testCase), expectedOutput);
    }

    @Test
    public void test_getSuspectsInfo_returnsString() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        Suspect suspectOne = new CasePersonBuilder().withName("Janice").withSex("F").withPhone("91234567")
                .withEmail("alice@gmail.com").withAddress("123 Main Street").buildSuspect();
        Suspect suspectTwo = new CasePersonBuilder().withName("Tom").withSex("M").withPhone("91234567")
                .withEmail("tom@gmail.com").withAddress("123 Main Street").buildSuspect();
        Case testCase = new CaseBuilder().withTitle("Bank Robbery")
                .addSuspects(suspectOne, suspectTwo)
                .build();
        String expectedOutput = "Janice F 91234567 alice@gmail.com 123 Main Street Tom M 91234567 tom@gmail.com "
                + "123 Main Street ";
        assertEquals(predicate.getSuspectsInfo(testCase), expectedOutput);
    }

    @Test
    public void test_getWitnessesInfo_returnsString() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        Witness witnessOne = new CasePersonBuilder().withName("Janice").withSex("F").withPhone("91234567")
                .withEmail("alice@gmail.com").withAddress("123 Main Street").buildWitness();
        Witness witnessTwo = new CasePersonBuilder().withName("Tom").withSex("M").withPhone("91234567")
                .withEmail("tom@gmail.com").withAddress("123 Main Street").buildWitness();

        Case testCase = new CaseBuilder().withTitle("Bank Robbery")
                .addWitnesses(witnessOne, witnessTwo)
                .build();
        String expectedOutput = "Janice F 91234567 alice@gmail.com 123 Main Street Tom M 91234567 tom@gmail.com "
                + "123 Main Street ";
        assertEquals(predicate.getWitnessesInfo(testCase), expectedOutput);
    }

    @Test
    public void test_getVictimsInfo_returnsString() {
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        Victim victimOne = new CasePersonBuilder().withName("Janice").withSex("F").withPhone("91234567")
                .withEmail("alice@gmail.com").withAddress("123 Main Street").buildVictim();
        Victim victimTwo = new CasePersonBuilder().withName("Tom").withSex("M").withPhone("91234567")
                .withEmail("tom@gmail.com").withAddress("123 Main Street").buildVictim();

        Case testCase = new CaseBuilder().withTitle("Bank Robbery")
                .addVictims(victimOne, victimTwo)
                .build();
        String expectedOutput = "Janice F 91234567 alice@gmail.com 123 Main Street Tom M 91234567 tom@gmail.com "
                + "123 Main Street ";
        assertEquals(predicate.getVictimsInfo(testCase), expectedOutput);
    }

    @Test
    public void test_appendPersonDetails() {
        StringBuilder test = new StringBuilder();
        DetailsContainsKeywordsPredicate predicate = new DetailsContainsKeywordsPredicate(Collections.emptyList());
        String expectedOutput = "Janice F 91234567 janice@gmail.com 123 Main Street ";

        Victim victim = new CasePersonBuilder().withName("Janice")
                .withSex("F").withPhone("91234567").withEmail("janice@gmail.com")
                        .withAddress("123 Main Street").buildVictim();
        predicate.appendPersonDetails(test, victim);
        assertEquals(test.toString(), expectedOutput);

        test = new StringBuilder();
        Suspect suspect = new CasePersonBuilder().withName("Janice")
                .withSex("F").withPhone("91234567").withEmail("janice@gmail.com")
                .withAddress("123 Main Street").buildSuspect();
        predicate.appendPersonDetails(test, suspect);
        assertEquals(test.toString(), expectedOutput);

        test = new StringBuilder();
        Witness witness = new CasePersonBuilder().withName("Janice")
                .withSex("F").withPhone("91234567").withEmail("janice@gmail.com")
                .withAddress("123 Main Street").buildWitness();
        predicate.appendPersonDetails(test, witness);
        assertEquals(test.toString(), expectedOutput);
    }
}
