package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.flashcard.Flashcard;
import seedu.address.model.QuickCache;
import seedu.address.model.ReadOnlyQuickCache;

/**
 * An Immutable QuickCache that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedPerson> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("flashcards") List<JsonAdaptedPerson> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyQuickCache} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyQuickCache source) {
        flashcards.addAll(source.getFlashcardList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code QuickCache} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public QuickCache toModelType() throws IllegalValueException {
        QuickCache quickCache = new QuickCache();
        for (JsonAdaptedPerson jsonAdaptedPerson : flashcards) {
            Flashcard flashcard = jsonAdaptedPerson.toModelType();
            if (quickCache.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            quickCache.addFlashcard(flashcard);
        }
        return quickCache;
    }

}
