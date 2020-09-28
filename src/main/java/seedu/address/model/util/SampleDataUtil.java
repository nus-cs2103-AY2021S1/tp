package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Reeve;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.student.*;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new MeetingLink("alexyeoh@example.com"),
                new ClassVenue("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new MeetingLink("berniceyu@example.com"),
                new ClassVenue("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new MeetingLink("charlotte@example.com"),
                new ClassVenue("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new Phone("91031282"), new MeetingLink("lidavid@example.com"),
                new ClassVenue("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new MeetingLink("irfan@example.com"),
                new ClassVenue("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new MeetingLink("royb@example.com"),
                new ClassVenue("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyReeve getSampleAddressBook() {
        Reeve sampleAb = new Reeve();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
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
