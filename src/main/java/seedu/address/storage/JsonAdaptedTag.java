package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.FileAddress;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TagName;


/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tag's %s field is missing!";

    private final String tagName;

    private final String fileAddress;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("tagName") String tagName,
                          @JsonProperty("fileAddress") String fileAddress) {
        this.tagName = tagName;
        this.fileAddress = fileAddress;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.getTagName().tagName;
        fileAddress = source.getFileAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Tag toModelType() throws IllegalValueException {

        if (tagName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TagName.class.getSimpleName()));
        }
        if (!TagName.isValidTagName(tagName)) {
            throw new IllegalValueException(TagName.MESSAGE_CONSTRAINTS);
        }
        final TagName modelName = new TagName(tagName);


        if (fileAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FileAddress.class.getSimpleName()));
        }
        if (!FileAddress.isValidFileAddress(fileAddress)) {
            throw new IllegalValueException(FileAddress.MESSAGE_CONSTRAINTS);
        }
        final FileAddress modelFileAddress = new FileAddress(fileAddress);

        return new Tag(modelName, modelFileAddress);
    }

}
