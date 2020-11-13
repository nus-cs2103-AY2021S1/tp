package seedu.address.storage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;


/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tag's %s field is missing!";

    private final String tagName;

    private final String fileAddress;

    private final Set<JsonAdaptedLabel> labels = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedTag} with the given tag details.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("tagName") String tagName,
                          @JsonProperty("fileAddress") String fileAddress,
                          @JsonProperty("labels") Set<JsonAdaptedLabel> labels) {
        this.tagName = tagName;
        this.fileAddress = fileAddress;
        if (labels != null) {
            this.labels.addAll(labels);
        }
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.getTagName().tagName;
        fileAddress = source.getFileAddress().value;
        labels.addAll(source.getLabels().stream()
                .map(JsonAdaptedLabel::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        final Set<Label> tagLabels = new HashSet<>();
        for (JsonAdaptedLabel label : labels) {
            tagLabels.add(label.toModelType());
        }

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

        final Set<Label> modelLabels = new HashSet<>(tagLabels);

        return new Tag(modelName, modelFileAddress, modelLabels);
    }

}
