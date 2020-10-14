package seedu.flashcard.testutil;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FlashcardDeck addressBook;

    public AddressBookBuilder() {
        addressBook = new FlashcardDeck();
    }

    public AddressBookBuilder(FlashcardDeck addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Flashcard flashcard) {
        addressBook.addFlashcard(flashcard);
        return this;
    }

    public FlashcardDeck build() {
        return addressBook;
    }
}
