package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Amount;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Description("Alex Yeoh"), new Amount("87438807"), new Date("alexyeoh@example.com"),
                    EMPTY_REMARK,
                    getTagSet("friends")),
            new Person(new Description("Bernice Yu"), new Amount("99272758"), new Date("berniceyu@example.com"),
                    EMPTY_REMARK,
                    getTagSet("colleagues", "friends")),
            new Person(new Description("Charlotte Oliveiro"), new Amount("93210283"), new Date("charlotte"
                    + "@example.com"), EMPTY_REMARK,
                    getTagSet("neighbours")),
            new Person(new Description("David Li"), new Amount("91031282"), new Date("lidavid@example.com"),
                    EMPTY_REMARK,
                    getTagSet("family")),
            new Person(new Description("Irfan Ibrahim"), new Amount("92492021"), new Date("irfan@example.com"),
                    EMPTY_REMARK,
                    getTagSet("classmates")),
            new Person(new Description("Roy Balakrishnan"), new Amount("92624417"), new Date("royb@example.com"),
                    EMPTY_REMARK,
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
