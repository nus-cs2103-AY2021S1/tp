package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.entry.Word;


public class JsonAdaptedWord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Word's %s field is missing!";
    private final String word;

    /**
     * Constructs a {@code JsonAdaptedWord} with the given {@code word}.
     */
    @JsonCreator
    public JsonAdaptedWord(String word) {
        this.word = word;
    }

    /**
     * Converts a given {@code Word} into this class for Jackson use.
     */
    public JsonAdaptedWord(Word source) {
        word = source.getWord();
    }

    //Dummy Constructor
    public JsonAdaptedWord() {
        word = "";
    }

    @JsonValue
    public String getWord() {
        return word;
    }

    /**
     * Converts this Jackson-friendly adapted Word object into the model's {@code Word} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               Word.
     */
    public Word toModelType() throws IllegalValueException {
        if (word == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Word.class.getSimpleName()));
        }
        if (!Word.isValidWord(word)) {
            throw new IllegalValueException(Word.MESSAGE_CONSTRAINTS);
        }
        return new Word(word);
    }

}
