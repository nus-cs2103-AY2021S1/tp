package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.FileAddress;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TagName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tag[] getSamplePersons() {
        return new Tag[] {
            new Tag(new TagName("Alex Yeoh"),
                new FileAddress("Blk 30 Geylang Street 29, #06-40")),
            new Tag(new TagName("Bernice Yu"),
                new FileAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Tag(new TagName("Charlotte Oliveiro"),
                new FileAddress("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Tag(new TagName("David Li"),
                new FileAddress("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Tag(new TagName("Irfan Ibrahim"),
                new FileAddress("Blk 47 Tampines Street 20, #17-35")),
            new Tag(new TagName("Roy Balakrishnan"),
                new FileAddress("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Tag sampleTag : getSamplePersons()) {
            sampleAb.addPerson(sampleTag);
        }
        return sampleAb;
    }


}
