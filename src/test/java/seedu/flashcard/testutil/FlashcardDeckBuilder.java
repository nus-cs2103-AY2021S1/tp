package seedu.flashcard.testutil;


import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * A utility class to help with building FlashcardDeck objects.
 */
public class FlashcardDeckBuilder {

    private FlashcardDeck flashcardDeck;

    /**
     * Creates a {@code FlashcardDeckBuilder} with an empty FlashcardDeck object.
     */
    public FlashcardDeckBuilder() {
        this.flashcardDeck = new FlashcardDeck();
    }

    public FlashcardDeckBuilder(FlashcardDeck flashcardDeckToCopy) {
        this.flashcardDeck = flashcardDeckToCopy;
    }

    /**
     * Sets the {@code Flashcard} of the {@code FlashcardDeck} that we are building.
     */
    public FlashcardDeckBuilder withFlashcard(Flashcard flashcard) {
        this.flashcardDeck.addFlashcard(flashcard);
        return new FlashcardDeckBuilder(this.flashcardDeck);
    }

    public FlashcardDeck build() {
        return new FlashcardDeck(this.flashcardDeck);
    }
}
