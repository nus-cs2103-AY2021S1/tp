package seedu.address.model.util;

import java.util.HashSet;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.description.Description;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tag[] getSampleTags() {
        //TODO: NEED TO MODIFY DESCRIPTION
        return new Tag[] {
            new Tag(new TagName("Alex Yeoh"),
                new FileAddress("c:\\a\\b\\alex.txt"),
                    new HashSet<Description>()),
            new Tag(new TagName("Bernice Yu"),
                new FileAddress("c:\\a\\b\\bernice.txt"),
                new HashSet<Description>()),
            new Tag(new TagName("Charlotte Oliveiro"),
                new FileAddress("c:\\a\\b\\charlotte.txt"),
                new HashSet<Description>()),
            new Tag(new TagName("David Li"),
                new FileAddress("c:\\a\\b\\david.txt"),
                new HashSet<Description>()),
            new Tag(new TagName("Irfan Ibrahim"),
                new FileAddress("c:\\a\\b\\irfan.txt"),
                new HashSet<Description>()),
            new Tag(new TagName("Roy Balakrishnan"),
                new FileAddress("c:\\a\\b\\roy.txt"),
                    new HashSet<Description>())
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
