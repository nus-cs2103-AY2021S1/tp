package seedu.zookeep.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Contains utility methods for populating {@code ZooKeep} with sample data.
 */
public class SampleDataUtil {
    public static Animal[] getSampleAnimals() {
        return new Animal[] {
            new Animal(new Name("Letho"), new Id("325"),
                new Species("Blue Tongue Skink"),
                getMedicalConditionSet("Healthy"), getFeedTimeSet("1234")),
            new Animal(new Name("Sulyvahn"), new Id("29381"),
                new Species("Boa Constrictor"),
                getMedicalConditionSet("Inclusion Body Disease"), getFeedTimeSet("0608")),
            new Animal(new Name("Nemo"), new Id("123"),
                new Species("Clownfish"),
                getMedicalConditionSet("Healthy"), getFeedTimeSet("1307")),
            new Animal(new Name("Ivan"), new Id("242111"),
                new Species("Badger"),
                getMedicalConditionSet("Healthy"), getFeedTimeSet("1307"))
        };
    }

    public static ReadOnlyZooKeepBook getSampleZooKeepBook() {
        ZooKeepBook sampleAb = new ZooKeepBook();
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

    /**
     * Returns a feedTime set containing the list of strings given.
     */
    public static Set<FeedTime> getFeedTimeSet(String... strings) {
        return Arrays.stream(strings)
                .map(FeedTime::new)
                .collect(Collectors.toSet());
    }

}
