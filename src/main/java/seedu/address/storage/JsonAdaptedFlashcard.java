package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Priority;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final String title;
    private final String definition;
    private String priority;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("title") String tile,
                                @JsonProperty("definition") String definition,
                                @JsonProperty("priority") String priority,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = tile;
        this.priority = priority;
        this.definition = definition;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        title = source.getTitle().fullTitle;
        definition = source.getDefinition().value;

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        priority = source.getPriority().toString();

    }

    /**
     * Converts this Jackson-friendly adapted flashcard object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidName(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelName = new Title(title);

        if (definition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Definition.class.getSimpleName()));
        }

        if (!Definition.isValidDefinition(definition)) {
            throw new IllegalValueException(Definition.MESSAGE_CONSTRAINTS);
        }

        final Definition modelDefinition = new Definition(definition);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Priority priorityEnum = Priority.identifyPriority(priority);

        return new Flashcard(modelName, modelDefinition, modelTags, priorityEnum);
    }

}
