package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_TWO;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FLASHCARD_1;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TABLE;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class FlashCardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        FlashCard flashCard = new FlashCardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashCard.getTags().remove(0));
    }

    @Test
    public void isSameFlashCard() {
        // same object -> returns true
        assertTrue(FLASHCARD_1.isSameFlashCard(FLASHCARD_1));

        // null -> returns false
        assertFalse(FLASHCARD_1.isSameFlashCard(null));

        // different phone and email -> returns false
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE)
                .build();
        assertFalse(FLASHCARD_1.isSameFlashCard(editedAlice));

        // different name -> returns false
        editedAlice = new FlashCardBuilder(FLASHCARD_1).withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(FLASHCARD_1.isSameFlashCard(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new FlashCardBuilder(FLASHCARD_1)
                .withTags(VALID_TAG_CHAPTER_ONE).build();
        assertTrue(FLASHCARD_1.isSameFlashCard(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_ONE).build();
        assertTrue(FLASHCARD_1.isSameFlashCard(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        FlashCard aliceCopy = new FlashCardBuilder(FLASHCARD_1).build();
        assertTrue(FLASHCARD_1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(FLASHCARD_1.equals(FLASHCARD_1));

        // null -> returns false
        assertFalse(FLASHCARD_1.equals(null));

        // different type -> returns false
        assertFalse(FLASHCARD_1.equals(5));

        // different flashcard -> returns false
        assertFalse(FLASHCARD_1.equals(TABLE));

        // different name -> returns false
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1).withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(FLASHCARD_1.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new FlashCardBuilder(FLASHCARD_1).withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE).build();
        assertFalse(FLASHCARD_1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_TWO).build();
        assertFalse(FLASHCARD_1.equals(editedAlice));
    }
}
