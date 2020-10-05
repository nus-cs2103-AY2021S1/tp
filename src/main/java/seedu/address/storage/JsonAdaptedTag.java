package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.ProjectTag;

/**
 * Jackson-friendly version of {@link ProjectTag}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code ProjectTag} into this class for Jackson use.
     */
    public JsonAdaptedTag(ProjectTag source) {
        tagName = source.projectTagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code ProjectTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ProjectTag toModelType() throws IllegalValueException {
        if (!ProjectTag.isValidProjectTagName(tagName)) {
            throw new IllegalValueException(ProjectTag.MESSAGE_CONSTRAINTS);
        }
        return new ProjectTag(tagName);
    }

}
