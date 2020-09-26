package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BugList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.*;
import seedu.address.model.person.Bug;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code BugList} with sample data.
 */
public class SampleDataUtil {
    public static Bug[] getSamplePersons() {
        return new Bug[] {
            new Bug(new Name("Alex Yeoh"), new Phone("87438807"), new State("alexyeoh@example.com"),
                new Description("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Bug(new Name("Bernice Yu"), new Phone("99272758"), new State("berniceyu@example.com"),
                new Description("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Bug(new Name("Charlotte Oliveiro"), new Phone("93210283"), new State("charlotte@example.com"),
                new Description("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Bug(new Name("David Li"), new Phone("91031282"), new State("lidavid@example.com"),
                new Description("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Bug(new Name("Irfan Ibrahim"), new Phone("92492021"), new State("irfan@example.com"),
                new Description("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Bug(new Name("Roy Balakrishnan"), new Phone("92624417"), new State("royb@example.com"),
                new Description("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        BugList sampleAb = new BugList();
        for (Bug sampleBug : getSamplePersons()) {
            sampleAb.addPerson(sampleBug);
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
