package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Animal[] getSampleAnimals() {
        return new Animal[] {
            new Animal(new Name("Alex Yeoh"), new Id("87438807"),
                new Species("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Animal(new Name("Bernice Yu"), new Id("99272758"),
                new Species("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Animal(new Name("Charlotte Oliveiro"), new Id("93210283"),
                new Species("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Animal(new Name("David Li"), new Id("91031282"),
                new Species("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Animal(new Name("Irfan Ibrahim"), new Id("92492021"),
                new Species("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Animal(new Name("Roy Balakrishnan"), new Id("92624417"),
                new Species("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Animal sampleAnimal : getSampleAnimals()) {
            sampleAb.addAnimal(sampleAnimal);
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
