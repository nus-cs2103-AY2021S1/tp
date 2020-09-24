package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Question;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Question("When was the last time you tried something new?"),
                    new Answer("87438807"), getTagSet("friends")),
            new Person(new Question("What’s the most sensible thing you’ve ever heard someone say?"),
                    new Answer("99272758"), getTagSet("colleagues", "friends")),
            new Person(new Question("What gets you excited about life?"),
                    new Answer("93210283"), getTagSet("neighbours")),
            new Person(new Question("What do you wish you spent more time doing five years ago?"),
                    new Answer("91031282"), getTagSet("family")),
            new Person(new Question("Do you ask enough questions or do you settle for what you know?"),
                    new Answer("92492021"), getTagSet("classmates")),
            new Person(new Question("Who do you love and what are you doing about it?"), new Answer("92624417"),
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
