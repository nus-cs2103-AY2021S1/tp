package seedu.forgetfulnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FLASHCARD_1;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.getTypicalGlossary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class GlossaryTest {

    private final Glossary glossary = new Glossary();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), glossary.getFlashCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> glossary.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGlossary_replacesData() {
        Glossary newData = getTypicalGlossary();
        glossary.resetData(newData);
        assertEquals(newData, glossary);
    }

    @Test
    public void resetData_withDuplicateFlashCards_throwsDuplicateFlashCardException() {
        // Two flashCards with the same identity fields
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        List<FlashCard> newFlashCards = Arrays.asList(FLASHCARD_1, editedAlice);
        GlossaryStub newData = new GlossaryStub(newFlashCards);

        assertThrows(DuplicateFlashCardException.class, () -> glossary.resetData(newData));
    }

    @Test
    public void hasFlashCard_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> glossary.hasFlashCard(null));
    }

    @Test
    public void hasFlashCard_flashCardNotInGlossary_returnsFalse() {
        assertFalse(glossary.hasFlashCard(FLASHCARD_1));
    }

    @Test
    public void hasFlashCard_flashCardInGlossary_returnsTrue() {
        glossary.addFlashCard(FLASHCARD_1);
        assertTrue(glossary.hasFlashCard(FLASHCARD_1));
    }

    @Test
    public void hasFlashCard_flashCardWithSameIdentityFieldsInGlossary_returnsTrue() {
        glossary.addFlashCard(FLASHCARD_1);
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        assertTrue(glossary.hasFlashCard(editedAlice));
    }

    @Test
    public void getFlashCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> glossary.getFlashCardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose flashCards list can violate interface constraints.
     */
    private static class GlossaryStub implements ReadOnlyGlossary {
        private final ObservableList<FlashCard> flashCards = FXCollections.observableArrayList();

        GlossaryStub(Collection<FlashCard> flashCards) {
            this.flashCards.setAll(flashCards);
        }

        @Override
        public ObservableList<FlashCard> getFlashCardList() {
            return flashCards;
        }
    }

}
