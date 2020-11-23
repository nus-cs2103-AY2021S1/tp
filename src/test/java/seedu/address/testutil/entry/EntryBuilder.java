package seedu.address.testutil.entry;

import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

/**
 * A utility class to help with building Entry objects.
 */
public class EntryBuilder {

    public static final String DEFAULT_WORD = "Apple Pear";
    public static final String DEFAULT_TRANSLATION = "りんご　梨";

    private Word word;
    private Translation translation;

    /**
     * Creates a {@code EntryBuilder} with the default details.
     */
    public EntryBuilder() {
        this.word = new Word(DEFAULT_WORD);
        this.translation = new Translation(DEFAULT_TRANSLATION);
    }

    /**
     * Initializes the EntryBuilder with the data of {@code entryToCopy}.
     * @param entryToCopy Entry used to initialize the entry builder
     */
    public EntryBuilder(Entry entryToCopy) {
        this.word = entryToCopy.getWord();
        this.translation = entryToCopy.getTranslation();
    }

    /**
     * Sets the {@code Word} of the {@code Entry} that we are building.
     * @param word Word to set the word field of the entry builder.
     * @return Entry builder with the given word.
     */
    public EntryBuilder withWord(String word) {
        this.word = new Word(word);
        return this;
    }

    /**
     * Sets the {@code Translation} of the {@code Entry} that we are building.
     * @param translation Translation to set the translation field of the entry builder.
     * @return Entry builder with the given translation.
     */
    public EntryBuilder withTranslation(String translation) {
        this.translation = new Translation(translation);
        return this;
    }

    /**
     * Creates an entry using the data fields in the entry builder
     * @return Entry with data from the entry builder
     */
    public Entry build() {
        return new Entry(word, translation);
    }
}
