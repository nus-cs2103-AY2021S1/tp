package seedu.address.model.deck;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.UniqueEntryList;
import seedu.address.model.play.scoring.QuizAttempt;

/**
 * A deck represents a list of entries. Each deck has a unique deck name. Users will be able to
 * attempt quizzes using these decks.
 */
public class Deck {

    private final DeckName deckName;
    private UniqueEntryList entries;
    private FilteredList<Entry> filteredEntries;
    private List<QuizAttempt> quizAttempts;

    /**
     * Constructs a deck object. Each deck is initialized with a given name {@code deckName}. Other
     * fields in the deck class are all initialized to empty lists upon initialization.
     */
    public Deck(DeckName deckName) {
        requireNonNull(deckName);
        this.deckName = deckName;
        this.entries = new UniqueEntryList();
        this.filteredEntries = new FilteredList<>(getEntryList());
        this.quizAttempts = new ArrayList<>();
    }

    /**
     * Returns the name of the deck.
     *
     * @return Name of the deck.
     */
    public DeckName getDeckName() {
        return this.deckName;
    }

    public FilteredList<Entry> getFilteredEntries() {
        return filteredEntries;
    }

    public void setFilteredEntries(
        FilteredList<Entry> filteredEntries) {
        this.filteredEntries = filteredEntries;
    }

    /**
     * Returns the list of entries in the deck.
     *
     * @return List of entries in the deck.
     */
    public UniqueEntryList getEntries() {
        return this.entries;
    }

    /**
     * Return the list of quiz attempts.
     *
     * @return List of quiz attempts.
     */
    public List<QuizAttempt> getQuizAttempts() {
        return this.quizAttempts;
    }

    /**
     * Adds a quiz attempt {@code quizAttempt} to the list of quiz attempts.
     *
     * @param quizAttempt Quiz attempt to add to the list.
     */
    public void addQuizAttempt(QuizAttempt quizAttempt) {
        this.quizAttempts.add(quizAttempt);
    }

    /**
     * Sets the current unique entry list to the given list of entries {@code entries}.
     *
     * @param entries List of entries to replace current entry list.
     */
    public void setEntries(UniqueEntryList entries) {
        this.entries = entries;
        this.filteredEntries = new FilteredList<>(getEntryList());
    }

    /**
     * Sets the current list of quiz attempts to the given list {@code quizAttempts}.
     *
     * @param quizAttempts List of quiz attempts to replace the current quiz attempt list.
     */
    public void setQuizAttempts(List<QuizAttempt> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }

    /**
     * Returns the current list of entries as an unmodifiable list.
     *
     * @return List of entries as unmodifiable list.
     */
    public ObservableList<Entry> getEntryList() {
        return entries.asUnmodifiableObservableList();
    }

    /**
     * Returns the current list of filtered entries.
     *
     * @return List of filtered entries.
     */
    public ObservableList<Entry> getFilteredEntryList() {
        return filteredEntries;
    }

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredEntries.setPredicate(predicate);
    }

    /**
     * Returns true if the entry list contains the specified entry {@code entry}.
     *
     * @param entry Entry to be checked whether or not it exists in the entry list.
     * @return True if the entry list contains the specified entry.
     */
    public boolean hasEntry(Entry entry) {
        return entries.contains(entry);
    }

    /**
     * Adds an entry {@code entry} to the list of entries.
     *
     * @param entry Entry to be added to the list of entries.
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    /**
     * Removed the specified entry {@code target} from the list of entries. The entry must already
     * exist in the list of entries.
     *
     * @param target Entry to be removed from the list of entries.
     */
    public void removeEntry(Entry target) {
        entries.remove(target);
    }

    /**
     * Replaces the specified entry {@code target} with another entry {@code editedEntry}. The
     * specified entry must already exist in the list of entries.
     *
     * @param target      Entry to be replaced.
     * @param editedEntry Entry to replace the target entry.
     */
    public void setEntry(Entry target, Entry editedEntry) {
        entries.setEntry(target, editedEntry);
    }

    /**
     * Two decks are considered to be the same if they have the same name This defines a notion of
     * equality between the two decks.
     *
     * @param otherDeck Deck to be compared with the current deck.
     * @return True if both decks have the same name.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }
        return otherDeck != null
            && otherDeck.getDeckName().equals(getDeckName());
    }

    /**
     * Returns true if both decks have the same name This defines a notion of equality between two
     * deck objects.
     *
     * @param other Other object to be compared with the current deck.
     * @return True if both deck objects have the same deck name.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Deck // instanceof handles nulls
            && getDeckName().equals(((Deck) other).getDeckName())); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(deckName, entries);
    }

    @Override
    public String toString() {
        return String.valueOf(getDeckName());
    }
}
