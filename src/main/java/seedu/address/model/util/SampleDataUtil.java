package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.FileAddress;
import seedu.address.model.person.Email;
import seedu.address.model.person.TagName;
import seedu.address.model.person.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Tag[] getSamplePersons() {
        return new Tag[] {
            new Tag(new TagName("Alex Yeoh"), new Email("alexyeoh@example.com"),
                new FileAddress("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Tag(new TagName("Bernice Yu"), new Email("berniceyu@example.com"),
                new FileAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Tag(new TagName("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                new FileAddress("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Tag(new TagName("David Li"),  new Email("lidavid@example.com"),
                new FileAddress("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Tag(new TagName("Irfan Ibrahim"), new Email("irfan@example.com"),
                new FileAddress("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Tag(new TagName("Roy Balakrishnan"), new Email("royb@example.com"),
                new FileAddress("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Tag samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<seedu.address.model.tag.Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(seedu.address.model.tag.Tag::new)
                .collect(Collectors.toSet());
    }

}
