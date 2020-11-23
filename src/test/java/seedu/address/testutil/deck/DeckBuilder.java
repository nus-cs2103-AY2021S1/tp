package seedu.address.testutil.deck;

import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;

public class DeckBuilder {
    public static final String DEFAULT_DECK_NAME = "Japanese";
    private DeckName deckName;

    /**
     * Creates a {@code DeckBuilder} with the default details.
     */
    public DeckBuilder() {
        deckName = new DeckName(DEFAULT_DECK_NAME);
    }

    /**
     * Initializes the DeckBuilder with the data of {@code deckToCopy}.
     */
    public DeckBuilder(Deck deckToCopy) {
        deckName = deckToCopy.getDeckName();
    }

    /**
     * Sets the {@code DeckName} of the {@code DeckName} that we are building.
     */
    public DeckBuilder withDeckName(String deckName) {
        this.deckName = new DeckName(deckName);
        return this;
    }

    public Deck build() {
        return new Deck(deckName);
    }
}
