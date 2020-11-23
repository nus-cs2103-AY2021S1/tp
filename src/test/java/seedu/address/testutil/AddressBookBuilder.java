package seedu.address.testutil;

import seedu.address.model.WordBank;
import seedu.address.model.deck.Deck;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code WordBank ab = new AddressBookBuilder().withEntry("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private WordBank wordBank;

    public AddressBookBuilder() {
        wordBank = new WordBank();
    }

    public AddressBookBuilder(WordBank wordBank) {
        this.wordBank = wordBank;
    }

    /**
     * Adds a new {@code Deck} to the {@code WordBank} that we are building.
     */
    public AddressBookBuilder withDeck(Deck deck) {
        wordBank.addDeck(deck);
        return this;
    }

    public WordBank build() {
        return wordBank;
    }
}
