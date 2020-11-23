package seedu.address.model.deck;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deck.exceptions.DeckNotFoundException;
import seedu.address.model.deck.exceptions.DuplicateDeckException;

/**
 * A list of decks that is comprised of unique decks Decks are considered unique if no two decks
 * share the same deck name A deck is determined to be unique by comparing using {@code
 * Deck#isSameDeck(Deck)}. Supports a minimal set of list operations
 */
public class UniqueDeckList implements Iterable<Deck> {

    private final ObservableList<Deck> internalList = FXCollections.observableArrayList();
    private final ObservableList<Deck> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the deckList contains an equivalent deck as the given argument.
     *
     * @param toCheck Deck to check if it exists in the deckList.
     * @return True if the deck already exists in the deckList.
     */
    public boolean contains(Deck toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeck);
    }

    /**
     * Adds a deck {@code toAdd} to the deckList. The deck must not already exist in the deckList.
     *
     * @param toAdd Deck to be added to the deckList.
     */
    public void add(Deck toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDeckException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the deck {@code target} in the deckList with {@code editedDeck}. {@code target} must
     * exist in the list. The deck identity of {@code editedDeck} must not be the same as another
     * existing deck in the list.
     *
     * @param target     Deck to be replaced.
     * @param editedDeck Deck to replace the target deck.
     */
    public void setDeck(Deck target, Deck editedDeck) {
        requireAllNonNull(target, editedDeck);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeckNotFoundException();
        }

        if (!target.isSameDeck(editedDeck) && contains(editedDeck)) {
            throw new DuplicateDeckException();
        }

        internalList.set(index, editedDeck);
    }

    /**
     * Removes the equivalent deck {@code toRemove} from the deckList. The deck must exist in the
     * list.
     *
     * @param toRemove Deck to be removed from the deckList.
     */
    public void remove(Deck toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeckNotFoundException();
        }
    }

    /**
     * Replaces the current list with the contents of the given list {@code replacement}.
     *
     * @param replacement List to replace the current list.
     */
    public void setDecks(UniqueDeckList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code decks}. {@code decks} must not contain
     * duplicate decks.
     *
     * @param decks List of decks to replace the contents of the current list.
     */
    public void setDecks(List<Deck> decks) {
        requireAllNonNull(decks);
        if (!decksAreUnique(decks)) {
            throw new DuplicateDeckException();
        }

        internalList.setAll(decks);
    }

    /**
     * Returns the entry list as an unmodifiable {@code ObservableList}.
     *
     * @return Entry list as an unmodifiable list.
     */
    public ObservableList<Deck> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Deck> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueDeckList // instanceof handles nulls
            && internalList.equals(((UniqueDeckList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code decks} contains only unique decks.
     *
     * @param decks List of decks to check if all decks are different.
     * @return True if all the decks in the given list are different.
     */
    private boolean decksAreUnique(List<Deck> decks) {
        for (int i = 0; i < decks.size() - 1; i++) {
            for (int j = i + 1; j < decks.size(); j++) {
                if (decks.get(i).isSameDeck(decks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
