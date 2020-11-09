package seedu.zookeep.model.animal;

import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.model.feedtime.FeedTime;


/**
 * Stores a comparator used for sorting animals according to the category provided.
 */
public class AnimalComparator {

    private static final Logger logger = LogsCenter.getLogger(AnimalComparator.class);
    private static final Comparator<Animal> ANIMAL_FEEDTIME_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            Optional<FeedTime> feedTime1 = o1.getEarliestFeedTime();
            Optional<FeedTime> feedTime2 = o2.getEarliestFeedTime();
            if (feedTime1.isEmpty() && feedTime2.isEmpty()) {
                return 0;
            } else if (feedTime1.isEmpty()) {
                return 1; // feedtime1 has no timeslot, push to the back
            } else if (feedTime2.isEmpty()) {
                return -1;
            } else {
                return Integer.compare(
                        Integer.parseInt(feedTime1.get().feedTime),
                        Integer.parseInt(feedTime2.get().feedTime));
            }
        }
    };

    private static final Comparator<Animal> ANIMAL_ID_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(
                    Integer.parseInt(o1.getId().value),
                    Integer.parseInt(o2.getId().value));
        }
    };

    private static final Comparator<Animal> ANIMAL_MEDICAL_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.getMedicalConditions().size() - o2.getMedicalConditions().size();
        }
    };

    private static final Comparator<Animal> ANIMAL_NAME_COMPARATOR = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            String animalName1 = o1.getName().fullName;
            String animalName2 = o2.getName().fullName;
            return animalName1.toLowerCase().compareTo(animalName2.toLowerCase());
        }
    };

    private static final String FEEDTIME_CATEGORY = "feedtime";
    private static final String ID_CATEGORY = "id";
    private static final String MEDICAL_CATEGORY = "medical";
    private static final String NAME_CATEGORY = "name";

    private final String category;
    private final Comparator<Animal> animalComparator;

    private AnimalComparator(Comparator<Animal> animalComparator, String category) {
        this.animalComparator = animalComparator;
        this.category = category;
    }

    /**
     * Creates an animal comparator to sort all animals by earliest feed time in chronological order.
     * @return An animal feed time comparator.
     */
    public static AnimalComparator createAnimalFeedTimeComparator() {
        return new AnimalComparator(ANIMAL_FEEDTIME_COMPARATOR, FEEDTIME_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by id in ascending order.
     * @return An animal id comparator.
     */
    public static AnimalComparator createAnimalIdComparator() {
        return new AnimalComparator(ANIMAL_ID_COMPARATOR, ID_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by their number of medical conditions in ascending order.
     * @return An animal medical comparator.
     */
    public static AnimalComparator createAnimalMedicalComparator() {
        return new AnimalComparator(ANIMAL_MEDICAL_COMPARATOR, MEDICAL_CATEGORY);
    }

    /**
     * Creates an animal comparator to sort all animals by name in alphabetical order.
     * @return An animal name comparator.
     */
    public static AnimalComparator createAnimalNameComparator() {
        return new AnimalComparator(ANIMAL_NAME_COMPARATOR, NAME_CATEGORY);
    }

    /**
     * Returns the category for sorting the animals.
     * @return Category for sorting in string representation.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Returns the static animal comparator for sorting the animals.
     * @return Static Animal Comparator for sorting.
     */
    public Comparator<Animal> getAnimalComparator() {
        logger.info("Sorting with the " + this);
        return this.animalComparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnimalComparator // instanceof handles nulls
                && category.equals(((AnimalComparator) other).category)); // state check
    }

    @Override
    public String toString() {
        return "animal " + this.category + " comparator";
    }
}
