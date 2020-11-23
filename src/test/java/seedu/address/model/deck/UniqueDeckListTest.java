package seedu.address.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.deck.TypicalDecks.JAPANESE_DECK;
import static seedu.address.testutil.deck.TypicalDecks.SPANISH_DECK;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.deck.exceptions.DeckNotFoundException;
import seedu.address.model.deck.exceptions.DuplicateDeckException;
import seedu.address.testutil.deck.DeckBuilder;


public class UniqueDeckListTest {
    private final UniqueDeckList uniqueDeckList = new UniqueDeckList();

    @Test
    public void contains_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.contains(null));
    }

    @Test
    public void contains_deckNotInList_returnsFalse() {
        assertFalse(uniqueDeckList.contains(JAPANESE_DECK));
    }

    @Test
    public void contains_deckInList_returnsTrue() {
        uniqueDeckList.add(JAPANESE_DECK);
        assertTrue(uniqueDeckList.contains(JAPANESE_DECK));
    }

    @Test
    public void contains_deckWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDeckList.add(JAPANESE_DECK);
        Deck editedJapaneseDeck = new DeckBuilder(JAPANESE_DECK).build();
        assertTrue(uniqueDeckList.contains(editedJapaneseDeck));
    }

    @Test
    public void add_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.add(null));
    }

    @Test
    public void add_duplicateDeck_throwsDuplicateDeckException() {
        uniqueDeckList.add(JAPANESE_DECK);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.add(JAPANESE_DECK));
    }

    @Test
    public void setDeck_nullTargetDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(null, JAPANESE_DECK));
    }

    @Test
    public void setDeck_nullEditedDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(JAPANESE_DECK, null));
    }

    @Test
    public void setDeck_targetDeckNotInList_throwsDeckNotFoundException() {
        assertThrows(DeckNotFoundException.class, () -> uniqueDeckList.setDeck(JAPANESE_DECK, JAPANESE_DECK));
    }


    @Test
    public void setDeck_editedDeckIsSameDeck_success() {
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        uniqueDeckList.add(JAPANESE_DECK);
        uniqueDeckList.setDeck(JAPANESE_DECK, JAPANESE_DECK);
        expectedUniqueDeckList.add(JAPANESE_DECK);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasSameIdentity_success() {
        Deck editedJapanese = new DeckBuilder(JAPANESE_DECK).build();
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        uniqueDeckList.add(JAPANESE_DECK);
        uniqueDeckList.setDeck(JAPANESE_DECK, editedJapanese);
        expectedUniqueDeckList.add(editedJapanese);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasDifferentIdentity_success() {
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        uniqueDeckList.add(JAPANESE_DECK);
        uniqueDeckList.setDeck(JAPANESE_DECK, SPANISH_DECK);
        expectedUniqueDeckList.add(SPANISH_DECK);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasNonUniqueIdentity_throwsDuplicateDeckException() {
        uniqueDeckList.add(JAPANESE_DECK);
        uniqueDeckList.add(SPANISH_DECK);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.setDeck(JAPANESE_DECK, SPANISH_DECK));
    }

    @Test
    public void remove_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.remove(null));
    }

    @Test
    public void remove_deckDoesNotExist_throwsDeckNotFoundException() {
        assertThrows(DeckNotFoundException.class, () -> uniqueDeckList.remove(JAPANESE_DECK));
    }

    @Test
    public void remove_existingDeck_removesDeck() {
        uniqueDeckList.add(JAPANESE_DECK);
        uniqueDeckList.remove(JAPANESE_DECK);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_nullUniqueDeckList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDecks((UniqueDeckList) null));
    }

    @Test
    public void setDecks_uniqueDeckList_replacesOwnListWithProvidedUniqueDeckList() {
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        uniqueDeckList.add(JAPANESE_DECK);
        expectedUniqueDeckList.add(SPANISH_DECK);
        uniqueDeckList.setDecks(expectedUniqueDeckList);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDecks((List<Deck>) null));
    }

    @Test
    public void setDecks_list_replacesOwnListWithProvidedList() {
        uniqueDeckList.add(JAPANESE_DECK);
        List<Deck> deckList = Collections.singletonList(SPANISH_DECK);
        uniqueDeckList.setDecks(deckList);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(SPANISH_DECK);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDecks_listWithDuplicateDecks_throwsDuplicateDeckException() {
        List<Deck> listWithDuplicateDecks = Arrays.asList(JAPANESE_DECK, JAPANESE_DECK);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.setDecks(listWithDuplicateDecks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueDeckList
                .asUnmodifiableObservableList().remove(0));
    }
}
