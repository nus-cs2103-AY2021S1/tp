package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    private final JsonAdaptedWord word;
    private final JsonAdaptedTranslation translation;

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("word") JsonAdaptedWord word,
        @JsonProperty("translation") JsonAdaptedTranslation translation) {
        this.word = word;
        this.translation = translation;
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        word = new JsonAdaptedWord(source.getWord());
        translation = new JsonAdaptedTranslation(source.getTranslation());
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               entry.
     */
    public Entry toModelType() throws IllegalValueException {

        if (word == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedWord.class.getSimpleName()));
        }
        if (!Word.isValidWord(word.getWord())) {
            throw new IllegalValueException(Word.MESSAGE_CONSTRAINTS);
        }
        final Word modelWord = new Word(word.getWord());
        if (translation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedTranslation.class.getSimpleName()));
        }
        if (!Translation.isValidTranslation(translation.getTranslation())) {
            throw new IllegalValueException(Translation.MESSAGE_CONSTRAINTS);
        }
        final Translation modelTranslation = new Translation(translation.getTranslation());
        return new Entry(modelWord, modelTranslation);
    }

}
