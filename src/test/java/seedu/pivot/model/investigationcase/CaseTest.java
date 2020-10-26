package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_NAME_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE;
import static seedu.pivot.testutil.TypicalCases.BOB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.model.tag.Tag;
import seedu.pivot.testutil.CaseBuilder;
import seedu.pivot.testutil.CasePersonBuilder;

public class CaseTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Case investigationCase = new CaseBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> investigationCase.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameCase(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCase(null));

        // different title -> returns false
        Case editedAlice = new CaseBuilder(ALICE).withTitle(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCase(editedAlice));

        // different status -> returns false
        editedAlice = new CaseBuilder(ALICE).withStatus("closed").build();
        assertFalse(ALICE.isSameCase(editedAlice));

        // same name, different attributes -> returns true
        editedAlice = new CaseBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND)
                .withDescription("Some Random Description").build();
        assertTrue(ALICE.isSameCase(editedAlice));

        //TODO: Might want to test for permutations of different attributes if multiple fields in future.
        // Currently isSamePerson tests for equality in title and status.
    }

    @Test
    public void equals() {
        // same values -> returns true
        Case aliceCopy = new CaseBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different title -> returns false
        Case editedAlice = new CaseBuilder(ALICE).withTitle(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new CaseBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description
        editedAlice = new CaseBuilder(ALICE).withDescription("Some Random Description").build();
        assertFalse(ALICE.equals(editedAlice));

        // different status
        editedAlice = new CaseBuilder(ALICE).withStatus("CLOSED").build();
        assertFalse(ALICE.equals(editedAlice));

        // different suspect
        Suspect suspect = new CasePersonBuilder().buildSuspect();
        editedAlice = new CaseBuilder(ALICE).withSuspects(suspect).build();
        assertFalse(ALICE.equals(editedAlice));

        // different witness
        Witness witness = new CasePersonBuilder().buildWitness();
        editedAlice = new CaseBuilder(ALICE).withWitnesses(witness).build();
        assertFalse(ALICE.equals(editedAlice));

        // different victims
        Victim victim = new CasePersonBuilder().buildVictim();
        editedAlice = new CaseBuilder(ALICE).withVictims(victim).build();
        assertFalse(ALICE.equals(editedAlice));

        // different document
        editedAlice = new CaseBuilder(ALICE).withDocument("Random Name", "test1.txt").build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void testGetters() {
        // Title
        Title title = new Title("Hello");
        Case testCase = new CaseBuilder(ALICE).withTitle("Hello").build();
        assertEquals(title, testCase.getTitle());

        // Description
        Description description = new Description("Testing");
        testCase = new CaseBuilder(ALICE).withDescription("Testing").build();
        assertEquals(description, testCase.getDescription());

        // Status
        Status status = Status.ACTIVE;
        testCase = new CaseBuilder(ALICE).withStatus("active").build();
        assertEquals(status, testCase.getStatus());

        // Suspect
        Suspect suspect = new CasePersonBuilder().buildSuspect();
        List<Suspect> suspects = new ArrayList<>();
        suspects.add(suspect);
        testCase = new CaseBuilder().withSuspects(suspect).build();
        assertEquals(suspects, testCase.getSuspects());

        // Victim
        Victim victim = new CasePersonBuilder().buildVictim();
        List<Victim> victims = new ArrayList<>();
        victims.add(victim);
        testCase = new CaseBuilder().withVictims(victim).build();
        assertEquals(victims, testCase.getVictims());

        // Witness
        Witness witness = new CasePersonBuilder().buildWitness();
        List<Witness> witnesses = new ArrayList<>();
        witnesses.add(witness);
        testCase = new CaseBuilder().withWitnesses(witness).build();
        assertEquals(witnesses, testCase.getWitnesses());

        // Document
        Document document = new Document(new Name("Test"), new Reference("test1.txt"));
        List<Document> docs = new ArrayList<>();
        docs.add(document);
        testCase = new CaseBuilder().withDocument("Test", "test1.txt").build();
        assertEquals(docs, testCase.getDocuments());

        // Tags
        Tag tag = new Tag("Test");
        HashSet<Tag> tags = new HashSet<>();
        tags.add(tag);
        testCase = new CaseBuilder().withTags("Test").build();
        assertEquals(tags, testCase.getTags());
    }
}
