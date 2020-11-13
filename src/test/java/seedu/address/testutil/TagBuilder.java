package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {

    public static final String DEFAULT_TAG_NAME = "testTag";
    public static final String DEFAULT_FILE_ADDRESS = "./src/test/java/seedu/address/testutil/testFile.bat";
    public static final String DEFAULT_LABEL = "testLabel";

    private TagName tagName;
    private FileAddress fileAddress;
    private Set<Label> labels;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        this.tagName = new TagName(DEFAULT_TAG_NAME);
        this.fileAddress = new FileAddress(DEFAULT_FILE_ADDRESS);
        Set<Label> labels = new HashSet<>();
        labels.add(new Label(DEFAULT_LABEL));
        this.labels = labels;
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        tagName = tagToCopy.getTagName();
        fileAddress = tagToCopy.getFileAddress();
        labels = tagToCopy.getLabels();
    }

    /**
     * Sets the {@code TagName} of the {@code Tag} that we are building.
     */
    public TagBuilder withTagName(String tagName) {
        this.tagName = new TagName(tagName);
        return this;
    }


    /**
     * Sets the {@code FileAddress} of the {@code Tag} that we are building.
     */
    public TagBuilder withFileAddress(String fileAddress) {
        this.fileAddress = new FileAddress(fileAddress);
        return this;
    }

    /**
     * Parses the {@code labels} into a {@code Set<Label>} and set it to the {@code Tag} that we are building.
     */
    public TagBuilder withLabels(String ... labels) {
        this.labels = SampleDataUtil.getLabelSet(labels);
        return this;
    }

    public Tag build() {
        return new Tag(tagName, fileAddress, labels);
    }
}
