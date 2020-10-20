package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.description.Description;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;


/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {

    public static final String DEFAULT_TAG_NAME = "testTag";
    public static final String DEFAULT_FILE_ADDRESS = "./src/test/java/seedu/address/testutil/testFile.bat";

    private TagName tagName;
    private FileAddress fileAddress;
    //TODO:NEED TO MODIFY DESCRIPTIONS
    private Set<Description> descriptions = new HashSet<Description>();


    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        tagName = new TagName(DEFAULT_TAG_NAME);
        fileAddress = new FileAddress(DEFAULT_FILE_ADDRESS);
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        tagName = tagToCopy.getTagName();
        fileAddress = tagToCopy.getFileAddress();

    }

    /**
     * Sets the {@code Name} of the {@code Tag} that we are building.
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

    //TODO:NEED TO MODIFY DESCRIPTION
    public Tag build() {
        return new Tag(tagName, fileAddress, descriptions);
    }
}
