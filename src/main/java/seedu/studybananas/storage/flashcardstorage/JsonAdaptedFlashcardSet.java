package seedu.studybananas.storage.flashcardstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.studybananas.commons.exceptions.IllegalValueException;
import seedu.studybananas.model.flashcard.Flashcard;
import seedu.studybananas.model.flashcard.FlashcardSet;
import seedu.studybananas.model.flashcard.FlashcardSetName;

public class JsonAdaptedFlashcardSet {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "FlashcardSet's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFlashcardSet} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedFlashcardSet(@JsonProperty("name") String name,
                           @JsonProperty("flashcards") List<JsonAdaptedFlashcard> flashcards) {
        this.name = name;
        if (flashcards != null) {
            this.flashcards.addAll(flashcards);
        }
    }

    /**
     * Converts a given {@code FlashcardSet} into this class for Jackson use.
     */
    public JsonAdaptedFlashcardSet(FlashcardSet source) {
        name = source.getFlashcardSetName().toString();
        flashcards.addAll(source.getFlashcards().stream()
            .map(JsonAdaptedFlashcard::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code FlashcardSet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public FlashcardSet toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, FlashcardSetName.class.getSimpleName()));
        }
        final FlashcardSetName modelName = new FlashcardSetName(name);
        final List<Flashcard> modelFlashcards = new ArrayList<>();
        for (JsonAdaptedFlashcard flashcard : flashcards) {
            modelFlashcards.add(flashcard.toModelType());
        }

        return new FlashcardSet(modelName, modelFlashcards);
    }
}
