package seedu.flashnotes.storage;
import static seedu.flashnotes.logic.commands.AddDeckCommand.MESSAGE_DUPLICATE_DECK;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyFlashNotes;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.UniqueDeckList;
import seedu.flashnotes.model.flashcard.Flashcard;


/**
 * An Immutable FlashNotes that is serializable to JSON format.
 */
@JsonRootName(value = "flashnotes")
class JsonSerializableFlashNotes {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();
    private final List<JsonAdaptedDeck> decks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashNotes} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashNotes(@JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards,
                                      @JsonProperty("decks") List<JsonAdaptedDeck> decks) {
        this.flashcards.addAll(flashcards);
        this.decks.addAll(decks);
    }


    /**
     * Converts a given {@code ReadOnlyFlashNotes} into this class for Jackson use.
     * Converts a given {@code UniqueDeckList} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashNotes}.
     */
    public JsonSerializableFlashNotes(ReadOnlyFlashNotes source, UniqueDeckList deckList) {
        flashcards.addAll(source.getFlashcardList().stream()
                .map(JsonAdaptedFlashcard::new).collect(Collectors.toList()));
        decks.addAll(deckList.asUnmodifiableObservableList().stream()
                .map(JsonAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this flashnotes into the model's {@code FlashNotes} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashNotes toModelType() throws IllegalValueException {
        FlashNotes flashNotes = new FlashNotes();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashNotes.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            flashNotes.addFlashcard(flashcard);
        }
        // For each deck info read
        for (JsonAdaptedDeck jsonAdaptedDeck : decks) {
            Deck theDeck = jsonAdaptedDeck.toModelType();
            if (flashNotes.hasDeck(theDeck)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DECK);
            }
            flashNotes.addDeck(theDeck);
        }

        return flashNotes;
    }

}
