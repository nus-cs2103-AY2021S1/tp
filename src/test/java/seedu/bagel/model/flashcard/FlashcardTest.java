package seedu.bagel.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_LINK_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.ALICE;
import static seedu.bagel.testutil.TypicalFlashcards.BOB;

import org.junit.jupiter.api.Test;

import seedu.bagel.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Flashcard flashcard = new FlashcardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashcard.getTags().remove(0));
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(ALICE.isSameFlashcard(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameFlashcard(null));

        // different title -> returns false
        Flashcard editedAlice = new FlashcardBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.isSameFlashcard(editedAlice));

        // same title, different description -> returns true
        editedAlice = new FlashcardBuilder(ALICE).withDescription(VALID_DESC_BOB).build();
        assertTrue(ALICE.isSameFlashcard(editedAlice));

        // same title, different link -> returns true
        editedAlice = new FlashcardBuilder(ALICE).withLink(VALID_LINK_BOB).build();
        assertTrue(ALICE.isSameFlashcard(editedAlice));

        // same title, same description, different attributes -> returns true
        editedAlice = new FlashcardBuilder(ALICE).withTags(VALID_TAG_HUSBAND).withLink(VALID_LINK_BOB).build();
        assertTrue(ALICE.isSameFlashcard(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard aliceCopy = new FlashcardBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different flashcard -> returns false
        assertFalse(ALICE.equals(BOB));

        // different title -> returns false
        Flashcard editedAlice = new FlashcardBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> returns false
        editedAlice = new FlashcardBuilder(ALICE).withDescription(VALID_DESC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different link -> returns false
        editedAlice = new FlashcardBuilder(ALICE).withLink(VALID_LINK_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different sets -> returns false
        editedAlice = new FlashcardBuilder(ALICE).withSet("3").build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FlashcardBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
