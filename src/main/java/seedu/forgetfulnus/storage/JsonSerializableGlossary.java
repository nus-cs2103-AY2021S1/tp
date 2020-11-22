package seedu.forgetfulnus.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * An Immutable Glossary that is serializable to JSON format.
 */
@JsonRootName(value = "glossary")
class JsonSerializableGlossary {

    public static final String MESSAGE_DUPLICATE_FLASHCARD =
            "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedFlashCard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGlossary} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableGlossary(@JsonProperty("flashcards") List<JsonAdaptedFlashCard> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     * {@code JsonSerializableGlossary}.
     */
    public JsonSerializableGlossary(ReadOnlyGlossary source) {
        flashcards.addAll(source.getFlashCardList()
                .stream()
                .map(JsonAdaptedFlashCard::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Glossary toModelType() throws IllegalValueException {
        Glossary glossary = new Glossary();
        for (JsonAdaptedFlashCard jsonAdaptedFlashCard : flashcards) {
            FlashCard flashCard = jsonAdaptedFlashCard.toModelType();
            if (glossary.hasFlashCard(flashCard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            glossary.addFlashCard(flashCard);
        }
        return glossary;
    }

}
