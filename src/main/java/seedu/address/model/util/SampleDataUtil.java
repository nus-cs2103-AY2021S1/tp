package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Document;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reference;
import seedu.address.model.person.Status;
import seedu.address.model.person.Suspect;
import seedu.address.model.person.Victim;
import seedu.address.model.person.Witness;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static List<Witness> sampleWitnesses = new ArrayList<>(
            List.of(new Witness(new Name("Mary")), new Witness(new Name("Janice"))));
    private static List<Document> sampleDocuments =
            List.of(new Document(new Name("name"), new Reference("test1.txt")));
    private static List<Victim> sampleVictims = getVictimList("Tom", "John");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                Status.createStatus("active"), sampleDocuments, new Address("Blk 30 Geylang Street 29, #06-40"),
                new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                    Status.createStatus("closed"), sampleDocuments,
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    Status.createStatus("cold"), sampleDocuments, new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"),
                    Status.createStatus("active"), sampleDocuments,
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    Status.createStatus("active"), sampleDocuments, new Address("Blk 47 Tampines Street 20, #17-35"),
                    new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    Status.createStatus("active"), sampleDocuments, new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new ArrayList<>(), sampleVictims, sampleWitnesses, getTagSet("colleagues"))
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

    /**
     * Returns a Witness list containing the list of strings given.
     */
    public static List<Witness> getWitnessList(String... strings) {
        return Arrays.stream(strings)
                .map(string -> new Witness(new Name(string)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a suspect list containing the list of strings given.
     */
    public static List<Suspect> getSuspectList(String[] suspects) {
        return Arrays.stream(suspects)
                .map(string -> new Suspect(new Name(string)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a victim list containing the list of strings given.
     */
    public static List<Victim> getVictimList(String... strings) {
        return Arrays.stream(strings)
                .map(string -> new Victim(new Name(string)))
                .collect(Collectors.toList());
    }

}
