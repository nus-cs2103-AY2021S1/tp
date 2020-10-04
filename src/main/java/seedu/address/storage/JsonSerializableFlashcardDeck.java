package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FlashcardDeck;
import seedu.address.model.ReadOnlyFlashcardDeck;
import seedu.address.model.flashcard.Flashcard;

/**
 * An Immutable FlashcardDeck that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableFlashcardDeck {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcard list contains duplicate flashcard(s).";

    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashcardDeck} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashcardDeck(@JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyFlashcardDeck} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashcardDeck}.
     */
    public JsonSerializableFlashcardDeck(ReadOnlyFlashcardDeck source) {
        flashcards.addAll(source.getFlashcardList().stream().map(JsonAdaptedFlashcard::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this flash card deck into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashcardDeck toModelType() throws IllegalValueException {
        FlashcardDeck flashcardDeck = new FlashcardDeck();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (flashcardDeck.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            flashcardDeck.addFlashcard(flashcard);
        }
        return flashcardDeck;
    }

}
