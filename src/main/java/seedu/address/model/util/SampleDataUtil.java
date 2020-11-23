package seedu.address.model.util;

import seedu.address.model.ReadOnlyWordBank;
import seedu.address.model.WordBank;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.DeckName;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

/**
 * Contains utility methods for populating {@code WordBank} with sample data.
 */
public class SampleDataUtil {
    private static Entry[] getSampleEntriesJapanese() {
        return new Entry[] {
            new Entry(new Word("Apple"), new Translation("りんご")),
            new Entry(new Word("Bridge"), new Translation("橋")),
            new Entry(new Word("Cat"), new Translation("猫")),
        };
    }

    private static Entry[] getSampleEntriesSpanish() {
        return new Entry[] {
            new Entry(new Word("Summer"), new Translation("verano")),
            new Entry(new Word("Winter"), new Translation("invierno")),
            new Entry(new Word("Spring"), new Translation("primavera")),
        };
    }

    private static Deck getSampleJapaneseDeck() {
        Deck sampleJapaneseDeck = new Deck(new DeckName("Japanese"));
        for (Entry sampleJapaneseEntry : getSampleEntriesJapanese()) {
            sampleJapaneseDeck.addEntry(sampleJapaneseEntry);
        }
        return sampleJapaneseDeck;
    }

    private static Deck getSampleSpanishDeck() {
        Deck sampleSpanishDeck = new Deck(new DeckName("Spanish"));
        for (Entry sampleSpanishEntry: getSampleEntriesSpanish()) {
            sampleSpanishDeck.addEntry(sampleSpanishEntry);
        }
        return sampleSpanishDeck;
    }

    public static ReadOnlyWordBank getSampleAddressBook() {
        WordBank sampleAb = new WordBank();
        sampleAb.addDeck(getSampleJapaneseDeck());
        sampleAb.addDeck(getSampleSpanishDeck());
        return sampleAb;
    }
}
