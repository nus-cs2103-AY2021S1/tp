package seedu.address.testutil;

import seedu.address.model.FlashcardBook;
import seedu.address.model.person.Flashcard;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new FlashcardBookBuilder().withPerson("John", "Doe").build();}
 */
public class FlashcardBookBuilder {

    private FlashcardBook flashcardBook;

    public FlashcardBookBuilder() {
        flashcardBook = new FlashcardBook();
    }

    public FlashcardBookBuilder(FlashcardBook flashcardBook) {
        this.flashcardBook = flashcardBook;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code AddressBook} that we are building.
     */
    public FlashcardBookBuilder withFlashcard(Flashcard flashcard) {
        flashcardBook.addFlashcard(flashcard);
        return this;
    }

    public FlashcardBook build() {
        return flashcardBook;
    }
}
