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
import seedu.address.model.medicalcondition.MedicalCondition;

/**
 * Contains utility methods for populating {@code ZooKeep} with sample data.
 */
public class SampleDataUtil {
    public static Animal[] getSampleAnimals() {
        return new Animal[] {
            new Animal(new Name("Letho"), new Id("000325"),
                new Species("Blue Tongue Skink"),
                getMedicalConditionSet("Healthy")),
            new Animal(new Name("Sulyvahn"), new Id("029381"),
                new Species("Boa Constrictor"),
                getMedicalConditionSet("Inclusion Body Disease")),
            new Animal(new Name("Nemo"), new Id("000001"),
                new Species("Clownfish"),
                getMedicalConditionSet("Healthy")),
            new Animal(new Name("Ivan"), new Id("0242111"),
                new Species("Badger"),
                getMedicalConditionSet("Healthy"))
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
     * Returns a medicalCondition set containing the list of strings given.
     */
    public static Set<MedicalCondition> getMedicalConditionSet(String... strings) {
        return Arrays.stream(strings)
                .map(MedicalCondition::new)
                .collect(Collectors.toSet());
    }

}
