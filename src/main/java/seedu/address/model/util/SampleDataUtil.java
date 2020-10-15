package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSamplePersons() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Contact(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Contact(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Contact(new Name("David Li"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Contact(new Name("Irfan Ibrahim"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Contact(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        ModuleList sampleAb = new ModuleList();
        for (Contact samplePerson : getSamplePersons()) {
            // sampleAb.addPerson(samplePerson);
        }
        // return sampleAb;
        return null;
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
