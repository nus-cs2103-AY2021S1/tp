package seedu.bagel.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.ALICE;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.SetOfFlashcardSets;
import seedu.bagel.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.bagel.testutil.FlashcardBuilder;

public class BagelTest {

    private final Bagel bagel = new Bagel();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bagel.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bagel.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBagel_replacesData() {
        Bagel newData = getTypicalBagel();
        bagel.resetData(newData);
        assertEquals(newData, bagel);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicatePersonException() {
        // Two flashcards with the same information fields
        Flashcard editedAlice = new FlashcardBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Flashcard> newFlashcards = Arrays.asList(ALICE, editedAlice);
        BagelStub newData = new BagelStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> bagel.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bagel.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInBagel_returnsFalse() {
        assertFalse(bagel.hasFlashcard(ALICE));
    }

    @Test
    public void hasFlashcard_flashcardInBagel_returnsTrue() {
        bagel.addFlashcard(ALICE);
        assertTrue(bagel.hasFlashcard(ALICE));
    }

    @Test
    public void hasFlashcard_flashcardWithSameInformationFieldsInBagel_returnsTrue() {
        bagel.addFlashcard(ALICE);
        Flashcard editedAlice = new FlashcardBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(bagel.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bagel.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyBagel whose flashcards list can violate interface constraints.
     */
    private static class BagelStub implements ReadOnlyBagel {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        BagelStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }

        @Override
        public SetOfFlashcardSets getSetOfFlashcardSets() {
            return new SetOfFlashcardSets();
        }
    }
}
