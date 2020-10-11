package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Name("Alex Yeoh"), new ZoomLink("Blk 30 Geylang Street 29, #06-40")),
            new Module(new Name("Bernice Yu"), new ZoomLink("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Module(new Name("Charlotte Oliveiro"), new ZoomLink("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Module(new Name("David Li"), new ZoomLink("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Module(new Name("Irfan Ibrahim"), new ZoomLink("Blk 47 Tampines Street 20, #17-35")),
            new Module(new Name("Roy Balakrishnan"), new ZoomLink("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
