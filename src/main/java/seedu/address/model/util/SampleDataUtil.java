package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Deadline("12-12-2020 1200"), new ModuleCode("CS2103T"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Deadline("09-10-2020 2359"), new ModuleCode("CS2100"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Deadline("08-08-2020 1900"), new ModuleCode("CS2040S"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Deadline("25-12-2020 1200"),
                    new ModuleCode("CS2030"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Deadline("13-10-2020 1300"),
                    new ModuleCode("MA1521"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Deadline("30-04-2020 1700"), new ModuleCode("CS1010X"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
