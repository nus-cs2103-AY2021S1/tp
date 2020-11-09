package quickcache.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import quickcache.commons.exceptions.IllegalValueException;
import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.flashcard.Flashcard;

/**
 * An Immutable QuickCache that is serializable to JSON format.
 */
@JsonRootName(value = "quickCache")
class JsonSerializableQuickCache {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedQuickCache> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableQuickCache} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableQuickCache(@JsonProperty("flashcards") List<JsonAdaptedQuickCache> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyQuickCache} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableQuickCache}.
     */
    public JsonSerializableQuickCache(ReadOnlyQuickCache source) {
        flashcards.addAll(
            source.getFlashcardList().stream().map(JsonAdaptedQuickCache::new).collect(Collectors.toList()));
    }

    /**
     * Converts this quick cache into the model's {@code QuickCache} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public QuickCache toModelType() throws IllegalValueException {
        QuickCache quickCache = new QuickCache();
        for (JsonAdaptedQuickCache jsonAdaptedQuickCache : flashcards) {
            Flashcard flashcard = jsonAdaptedQuickCache.toModelType();
            if (quickCache.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            quickCache.addFlashcard(flashcard);
        }
        return quickCache;
    }

}
