package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag(new TagName("Alex Yeoh"),
                new FileAddress("c:\\a\\b\\alex.txt")),
            new Tag(new TagName("Bernice Yu"),
                new FileAddress("c:\\a\\b\\bernice.txt")),
            new Tag(new TagName("Charlotte Oliveiro"),
                new FileAddress("c:\\a\\b\\charlotte.txt")),
            new Tag(new TagName("David Li"),
                new FileAddress("c:\\a\\b\\david.txt")),
            new Tag(new TagName("Irfan Ibrahim"),
                new FileAddress("c:\\a\\b\\irfan.txt")),
            new Tag(new TagName("Roy Balakrishnan"),
                new FileAddress("c:\\a\\b\\roy.txt"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
        }
        return sampleAb;
    }


}
