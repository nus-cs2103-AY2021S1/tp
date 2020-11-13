package seedu.bagel.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.bagel.commons.exceptions.IllegalValueException;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final String title;
    private final String description;
    private final String link;
    private final String flashcardSet;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("title") String title, @JsonProperty("description") String description,
                                @JsonProperty("link") String link,
                                @JsonProperty("flashcardSet") String flashcardSet,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.flashcardSet = flashcardSet;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        title = source.getTitle().fullTitle;
        description = source.getDescription().value;
        link = source.getLink().value;
        flashcardSet = source.getFlashcardSet().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted flashcard object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Flashcard toModelType() throws IllegalValueException {

        final List<Tag> flashcardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            flashcardTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (link == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Link.class.getSimpleName()));
        }
        if (!Link.isValidLink(link)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
        }
        final Link modelLink = new Link(link);

        if (flashcardSet == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FlashcardSet.class.getSimpleName()));
        }
        if (!FlashcardSet.isValidSetNumber(flashcardSet)) {
            throw new IllegalValueException(FlashcardSet.MESSAGE_CONSTRAINTS);
        }
        final FlashcardSet modelFlashcardSet = new FlashcardSet(flashcardSet);

        final Set<Tag> modelTags = new HashSet<>(flashcardTags);
        return new Flashcard(modelTitle, modelDescription, modelLink, modelFlashcardSet, modelTags);
    }

}
