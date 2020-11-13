package seedu.bagel.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.bagel.commons.exceptions.IllegalValueException;
import seedu.bagel.model.Bagel;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * An Immutable Bagel that is serializable to JSON format.
 */
@JsonRootName(value = "bagel")
class JsonSerializableBagel {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcard list contains duplicate flashcards.";

    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBagel} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableBagel(@JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyBagel} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBagel}.
     */
    public JsonSerializableBagel(ReadOnlyBagel source) {
        flashcards.addAll(source.getFlashcardList().stream().map(JsonAdaptedFlashcard::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this bagel into the model's {@code Bagel} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Bagel toModelType() throws IllegalValueException {
        Bagel bagel = new Bagel();
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (bagel.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            bagel.addFlashcard(flashcard);
        }
        return bagel;
    }

}
